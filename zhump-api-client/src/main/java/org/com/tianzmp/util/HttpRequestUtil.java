package org.com.tianzmp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * http请求帮助类
 */
public class HttpRequestUtil {
	private static final Log log = LogFactory.getLog(HttpRequestUtil.class);
    
	private static HttpRequestUtil instance = null;
	
	private CloseableHttpClient httpClient=null;
	
	private Object lock=new Object();
	
	private HttpRequestUtil() {
		getHttpClient();
	}
	public static HttpRequestUtil getInstance() {
		if (instance == null) {
			synchronized (HttpRequestUtil.class) {
				if (instance == null) {
					instance = new HttpRequestUtil();
				}
			}
		}
		return instance;
	}
	public CloseableHttpClient getHttpClient(){
		if (httpClient == null) {
			synchronized (lock) {
				if (httpClient != null) {
			        return httpClient;
				}

				ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		        Registry<ConnectionSocketFactory> registry = RegistryBuilder
															        		.<ConnectionSocketFactory> create()
															        		.register("http", plainsf)
															                .register("https", sslsf).build();

		        SocketConfig socketConfig = SocketConfig.custom()
																.setTcpNoDelay(true)     //是否立即发送数据，设置为true会关闭Socket缓冲，默认为false
																.setSoReuseAddress(true) //是否可以在一个进程关闭Socket后，即使它还没有释放端口，其它进程还可以立即重用端口
																.setSoTimeout(500)       //接收数据的等待超时时间，单位ms
																.setSoLinger(60)         //关闭Socket时，要么发送完所有数据，要么等待60s后，就关闭连接，此时socket.close()是阻塞的
													            .setSoKeepAlive(true)    //开启监视TCP连接是否有效
													            .build();

		        RequestConfig defaultRequestConfig = RequestConfig.custom()
																		.setConnectTimeout(30000)         //连接超时时间
														                .setSocketTimeout(30000)          //读超时时间（等待数据超时时间）
														                .setConnectionRequestTimeout(30000)    //从池中获取连接超时时间
														                .build();


		        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		        cm.setMaxTotal(200);  // 将最大连接数添加
		        cm.setDefaultMaxPerRoute(20); // 将每一个路由基础的连接添加
		        cm.setDefaultSocketConfig(socketConfig);

		        // 请求重试处理
		        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
		        	public boolean retryRequest(IOException exception,int executionCount, HttpContext context) {
		                if (executionCount >= 5) {// 假设已经重试了5次，就放弃
		                    return false;
		                }
		                if (exception instanceof NoHttpResponseException) {// 假设server丢掉了连接。那么就重试
		                    return true;
		                }
		                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
		                    return false;
		                }
		                if (exception instanceof InterruptedIOException) {// 超时
		                    return false;
		                }
		                if (exception instanceof UnknownHostException) {// 目标server不可达
		                    return false;
		                }
		                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
		                    return false;
		                }
		                if (exception instanceof SSLException) {// SSL握手异常
		                    return false;
		                }
		                HttpClientContext clientContext = HttpClientContext.adapt(context);
		                HttpRequest request = clientContext.getRequest();
		                // 假设请求是幂等的，就再次尝试
		                if (!(request instanceof HttpEntityEnclosingRequest)) {
		                    return true;
		                }
		                return false;
		            }
		        };
		        httpClient = HttpClients.custom()
								                .setConnectionManager(cm)
								                .setRetryHandler(httpRequestRetryHandler)
								                .setDefaultRequestConfig(defaultRequestConfig)
								                .build();


		        new IdleConnectionMonitorThread(cm).start();

		        return httpClient;
			}
		}else{
			return httpClient;
		}


	}
	
	private static class IdleConnectionMonitorThread extends Thread {
	    private final HttpClientConnectionManager connMgr;
	    private volatile boolean shutdown;
	    
	    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
	        super();
	        this.connMgr = connMgr;
	    }
	    @Override
	    public void run() {
	        try {
	            while (!shutdown) {
	                synchronized (this) {
	                    wait(5000);
	                    // Close expired connections
	                    connMgr.closeExpiredConnections();
	                    // Optionally, close connections
	                    // that have been idle longer than 30 sec
	                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
	                }
	            }
	        } catch (InterruptedException ex) {
	            // terminate
	        }
	    }
	    public void shutdown() {
	        shutdown = true;
	        synchronized (this) {
	            notifyAll();
	        }
	    }
	    
	}


	/**
	 * get请求 
	 * @param requestUrl 请求的url
	 * @param map 请求参数 
	 * @param heads 额为请求头
	 * @return
	 */
	public String doGet(String requestUrl,Map<String, String> map,Map<String,String> heads){
		log.info("【第三方请求url】:"+requestUrl);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if(map!=null && map.size()!=0){
			for(Map.Entry<String,String> entry : map.entrySet()){
		        pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		    }
		}
	    CloseableHttpResponse response = null;
        try {
        	URIBuilder builder;
			builder = new URIBuilder(requestUrl);
    	    builder.setParameters(pairs);
    	    HttpGet get = new HttpGet(builder.build());
    	    get.setHeader("Content-Type","application/x-www-form-urlencoded;");
    	    if(heads!=null && heads.size()!=0){
				Iterator<Map.Entry<String, String>> it=heads.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<String, String> me=it.next();
					get.setHeader(me.getKey(), me.getValue());
				}
			}
            response = getHttpClient().execute(get, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
			log.info("【第三方请求返回数据】:"+result);
            return result;
        } catch (Exception e) {
        	log.error("api返回错误："+e);
            e.printStackTrace();
        } finally {
        	try {
                if (response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
	}
	
	/**
	 * post请求 
	 * @param requestUrl 请求的url
	 * @param map 请求参数
	 * @param heads 额为请求头
	 * @return
	 */
	public String doPost(String requestUrl,Map<String, String> map,Map<String,String> heads){
		log.info("【第三方请求url】:"+requestUrl);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if(map!=null && map.size()!=0){
			for(Map.Entry<String,String> entry : map.entrySet()){
		        pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		    }
		}
		
        CloseableHttpResponse response = null;
        try {
        	HttpPost post = new HttpPost(requestUrl);
        	post.setHeader("Content-Type","application/x-www-form-urlencoded;");
        	if(heads!=null && heads.size()!=0){
 				Iterator<Map.Entry<String, String>> it=heads.entrySet().iterator();
 				while(it.hasNext()){
 					Map.Entry<String, String> me=it.next();
 					post.setHeader(me.getKey(), me.getValue());
 				}
 			}
        	post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = getHttpClient().execute(post, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
			log.info("【第三方请求返回数据】:"+result);
            return result;
        } catch (Exception e) {
           log.error("api返回错误："+e);
           e.printStackTrace();
        } finally {
        	try {
                if (response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
	}
	/**
	 * post请求 
	 * @param requestUrl 请求的url
	 * @param map 请求参数
	 * @param heads 额为请求头
	 * @return
	 */
	public  String doPostByBody(String requestUrl,String param,Map<String,String> heads){
		log.info("【第三方请求url】:"+requestUrl);
		String paramStr="";
		if(param!=null){
			paramStr=param;
		}
		HttpPost post = new HttpPost(requestUrl);
		CloseableHttpResponse response = null;
        try {
        	if(heads!=null && heads.size()!=0){
 				Iterator<Map.Entry<String, String>> it=heads.entrySet().iterator();
 				while(it.hasNext()){
 					Map.Entry<String, String> me=it.next();
 					post.setHeader(me.getKey(), me.getValue());
 				}
 			}
        	post.setEntity(new ByteArrayEntity(paramStr.getBytes("UTF-8")));
            response = getHttpClient().execute(post, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
			log.info("【第三方请求返回数据】:"+result);
            return result;
        } catch (Exception e) {
           log.error("api返回错误："+e);
           e.printStackTrace();
        } finally {
            try {
                if (response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
	}
	
}
