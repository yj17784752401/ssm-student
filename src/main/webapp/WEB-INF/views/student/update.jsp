<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/6/15
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="zh-cn">
    <title>学生角色-修改学生信息页面</title>
    <style>
        .gj-datepicker-md [role="right-icon"]{
            top:8px !important;
        }
        .img-show{
            max-width: 100px;
            min-width: 100px;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../top.jsp"/>
    <div class="container">
        <section class="col-sm-8 offset-sm-2">
            <form id="updateForm">
<%--                第一行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>系统标识：</label>
                        <input type="text" class="form-control" name="id"
                               value="${requestScope.dto.id}" readonly/>
                    </div>
                    <div class="form-group col-md-6">
                        <label>学号:</label>
                        <input type="text" class="form-control" name="no" required
                               value="${dto.no}"/>
                        <div class="invalid-feedback">学号不能为空</div>
                    </div>
                </div>
<%--                第二行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>姓名:</label>
                        <input type="text" class="form-control" name="realName"
                                value="${dto.realName}"
                                required/>
                        <div class="invalid-feedback">姓名不能为空</div>
                    </div>
                    <div class="form-group col-md-6">
                        <label>生日:</label>
                        <input type="text" class="form-control" name="birthDay"
                               id="birthDay" readonly
                               value="${dto.birthDay}"
                               required/>
                        <div class="invalid-feedback">生日不能为空</div>
                    </div>
                </div>
<%--                第三行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>手机:</label>
                        <input type="text" class="form-control" name="phone" autocomplete="off"
                               value="${dto.phone}"
                               required/>
                        <div class="invalid-feedback">手机不能为空</div>
                    </div>
                    <div class="form-group col-md-6">
                        <label>邮箱:</label>
                        <input type="email" class="form-control" name="email"
                               value="${dto.email}"
                               required/>
                        <div class="invalid-feedback">邮箱不能为空</div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="d-block">免冠照:</label>
                    <div class="img-show">
                        <%--使用图片点击弹出文件选择框，代替默认的type=file不好看的样式--%>
                        <img class="img-fluid"
                             onclick="openFileSelectDia()"
                             src="${dto.addressablePhotoPath}" id="photoImg">

                        <input type="file" onchange="fileSelected()" class="d-none" name="file"
                               id="fileInput"/>
                        <input type="hidden" name="addressablePhotoPath" id="addressablePhotoPath"/>
                    </div>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="button" onclick="updateStudent()" class="btn btn-primary" value="修改"/>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/toLogin">返回</a>
                </div>
            </form>
        </section>

    </div>
    <jsp:include page="../bottom.jsp"/>
</div>
<script>
    //更新个人信息
    function updateStudent(){
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/student/update',
        data: formDataObj("#updateForm"),
        success(result){
            if (result.success){
                $("#tipCont").text(result.data);
                $("#tipModal").modal("show");
            }else {
                $("#tipCont").text(result.errMsg);
                $("#tipModal").modal("show");
            }
        }
    });
    }


function openFileSelectDia() {
    document.getElementById("fileInput").click();
}
/*
* 当文件被选择的时候会调用type=file的input的change事件指定的函数
* */
function fileSelected(){
//    js里面获取上传的文件对象数组
    var fileObj =  document.getElementById("fileInput").files[0];
    if (typeof fileObj == "undefined" || fileObj.size <= 0){
        $("#tipCont").text("请选择上传的照片");
        $("#tipModal").modal("show");
    }
    //构造formData
    var formData = new FormData();
    formData.append("photoImgFile",fileObj);
    //使用jQuery来发送文件上传请求（也可以使用xhr对象来发送
    $.ajax({
        type:'post',
        url:'<%=request.getContextPath()%>/student/upload',
        data:formData,
        //文件上传需要设置为false
        contentType:false,
        //用于对data的值进行转为查询字符串
        processData: false,
        //避免浏览器缓存导致文件上传请求不发送
        cache:false,
        success:function (result){
            if (result.success){
                //完成上传图片的回显
                $("photoImg").attr("src",result.data);
                //异步上传
                $("#addressablePhotoPath").val(result.data);
            }else {
                $("#tipCont").text(result.errMsg);
                $("#tipModal").modal("show");
            }
        }
    })
}
</script>
</body>
</html>
