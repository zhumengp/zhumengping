package org.com.tianzmp.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.com.tianzmp.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.util.StringUtil;



@Component("RedisUtils")
public class RedisUtils {
	
	private final Logger log = LoggerFactory.getLogger(RedisUtils.class);
	
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 缓存数据
     * @param key
     * @param value
     */
    public void setRedis(String key,String value) {
    	try {
    		stringRedisTemplate.opsForValue().set(key, value);
    	}catch (Exception e) {
    		log.error("redis存储数据失败",e);
		}
    }
    
    /**
     * 缓存数据
     * @param key
     * @param value
     */
    public void setRedisTime(String key,String value,long time) {
    	try {
    		stringRedisTemplate.opsForValue().set(key, value,time,TimeUnit.MINUTES);
    	}catch (Exception e) {
    		log.error("redis存储数据失败",e);
		}
    }
    
    /**
     * 缓存集合
     * @param key
     * @param value
     */
    public void setRedis_List(String key,List list) {
    	try {
    		stringRedisTemplate.opsForValue().set(key, JsonMapper.getInstance().toJson(list));
    	}catch (Exception e) {
    		log.error("redis存储数据失败",e);
		}
    }
    
    public String getRedis_List(String key) {
    	try {
    		String result = stringRedisTemplate.opsForValue().get(key);
    		if(StringUtil.isEmpty(result)) {
    			return null;
    		}
    		return result;
    	}catch (Exception e) {
    		log.error("【获取数据失败】",e);
		}
    	return null;
    }
    
    /**
     * 根据key，获取数据
     * @param key
     * @return
     */
    public String getRedis(String key) {
    	try {
    		String result = stringRedisTemplate.opsForValue().get(key);
    		if(StringUtil.isEmpty(result)) {
    			return null;
    		}
    		return result;
    	}catch (Exception e) {
    		log.error("redis存储数据失败",e);
		}
    	return null;
    }
    
    /**
        * 删除
     */
    public void delRedis(String key) {
    	try {
    		if(StringUtils.isBlank(key)) {
    			log.error("【redis:key不能为空】");
    		}
    		stringRedisTemplate.delete(key);
    	}catch (Exception e) {
    		log.error("redis删除数据失败",e);
		}
    }
    
    public void hSetRedis(String key,String hashKey,List list) {
    	try {
    		if(StringUtils.isBlank(key)) {
    			log.error("【redis:key不能为空】");
    		}
    		stringRedisTemplate.opsForHash().put(key, hashKey, JsonMapper.getInstance().toJson(list));
    	}catch (Exception e) {
    		log.error("redis删除数据失败",e);
		}
    }
    
    public String hGetRedis(String key,String hashKey) {
    	try {
    		if(StringUtils.isBlank(key)) {
    			log.error("【redis:key不能为空】");
    		}
    		Object object = stringRedisTemplate.opsForHash().get(key, hashKey);
    		return object.toString();
    	}catch (Exception e) {
    		log.error("redis删除数据失败",e);
		}
    	return null;
    }
  
}
