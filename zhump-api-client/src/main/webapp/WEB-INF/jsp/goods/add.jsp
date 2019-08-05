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
        <div>
            <label>商品名:<input id="name" type="text"></label>
        </div>
        <div>
            <label>图片上传</label>
            <input type="file" name="file" id="file" onchange="uploadImg()">
        </div>
        <div>
            <img src="" alt="" id="avatarShow" width="100px" height="100px">
        </div>
        <div>
            <label>商品单价:</label>
            <input id="price"type="text">
        </div>
        <div>
            <label>商品数量</label>
            <input id="inventory"type="text">
        </div>
            <button id="add">提交</button>
    </div>

  <script type="text/javascript">
    function uploadImg() {
        if($("#file").val() != "") {
            $.ajaxFileUpload({
                type: "POST",
                url:"${pageContext.request.contextPath}/file/upload.action",
                dataType: "json",
                fileElementId:"file",  // 文件的id
                success: function(d){
                    console.info(d.data);
                    if(d.code == 1) {
                        alert("上传成功");
                       // $("#avatar").attr("value",d.data);
                        $("#avatarShow").attr("src",d.data);
                        $("#add").click(function () {
                            var name = $("#name").val();
                            var price = $("#price").val();
                            var inventory = $("#inventory").val();
                            var picture = d.data;
                            $.ajax({
                                url:path+'/goods/add.action',
                                data:{
                                    name:name,
                                    inventory:inventory,
                                    price:price,
                                    picture:picture
                                },
                                dataType:'json',
                                type:'POST',
                                success:function(data){
                                    console.info(data.code);
                                    if (data.code == "000002"){
                                        alert("添加成功");
                                    }else{
                                        alert("添加失败");
                                    }

                                }
                            })

                        })
                    }
                },
                error: function () {
                    alert("上传失败");
                },
            });
        } else {
            alert("请先选择文件");
        }
    }

  </script>
  </body>
</html>
