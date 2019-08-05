<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Demo1.jsp' starting page</title>

      <script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
      <script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/path.js"></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajaxfileupload.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>这是goods添加页</h1>
    <div >
        
            <button id="add" style="height: 10%; width: 20%">登录</button>
    </div>

  <script type="text/javascript">
    $(function(){
    	
    	$("#add").click(function () {
    		alert("1111111111");
            $.ajax({
                url:path+'/weChat/getAccreditUrl.action',
                dataType:'json',
                type:'GET',
                success:function(data){
                    console.info(data.code);
                    if (data.code == "000001"){
                        alert("添加成功");
                        window.location.href=data.data;
                    }else{
                        alert("添加失败");
                    }

                }
            })
    	})
    })

  </script>
  </body>
</html>
