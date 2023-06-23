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
    <title>修改学生信息页面</title>
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
    <jsp:include page="../../top.jsp"/>
    <div class="container">
        <section class="col-sm-8 offset-sm-2">
            <form id="updateForm">
<%--                第一行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>系统标识：</label>
                        <input type="text" class="form-control" name="id"
                               value="${requestScope.studentToUpdate.id}" readonly/>
                    </div>
                    <div class="form-group col-md-6">
                        <label>学号:</label>
                        <input type="text" class="form-control" name="no" required
                               value="${requestScope.studentToUpdate.no}"/>
                        <div class="invalid-feedback">学号不能为空</div>
                    </div>
                </div>
<%--                第二行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>姓名:</label>
                        <input type="text" class="form-control" name="realName"
                                value="${requestScope.studentToUpdate.realName}"
                                required/>
                        <div class="invalid-feedback">姓名不能为空</div>
                    </div>
                    <div class="form-group col-md-6">
                        <label>生日:</label>
                        <input type="text" class="form-control" name="birthDay"
                               id="birthDay" readonly
                               value="${requestScope.studentToUpdate.birthDay}"
                               required/>
                        <div class="invalid-feedback">生日不能为空</div>
                    </div>
                </div>
<%--                第三行--%>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>手机:</label>
                        <input type="text" class="form-control" name="phone" autocomplete="off"
                               value="${requestScope.studentToUpdate.phone}"
                               required/>
                        <div class="invalid-feedback">手机不能为空</div>
                    </div>
                    <div class="form-group col-md-6">
                        <label>邮箱:</label>
                        <input type="email" class="form-control" name="email"
                               value="${requestScope.studentToUpdate.email}"
                               required/>
                        <div class="invalid-feedback">生日不能为空</div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="d-block">免冠照:</label>
                    <div class="img-show">
                        <img class="img-fluid" src="${requestScope.studentToUpdate.addressablePhotoPath}">
                    </div>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="button" onclick="updateStudent()" class="btn btn-primary" value="修改"/>
                    <a class="btn btn-primary" href="#" onclick="history.go(-1)">返回</a>
                </div>
            </form>
        </section>

    </div>
    <jsp:include page="../../bottom.jsp"/>
</div>
<script>
    $("#birthDay").datepicker({
        locale:'zh-cn',
        format:'yyyy-mm-dd',
        weekStartDay: 1
    });
    function updateStudent(){
        var validateResult= document.getElementById("updateForm").checkValidity();
        $("#addForm").addClass("was-validated");
        if (!validateResult){
            return;
        }
        //admin修改学生信息
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/admin/updateStudent',
            data:formDataObj("#updateForm"),
            success:function (result) {
                if(result.success){
                   location.href="<%=request.getContextPath()%>/admin/toStudentManage?pageNow=<%=request.getAttribute("pageNow")%>"
                }else {
                    $("#tipCont").text(result.errMsg);
                    $("#tipModal").modal("show");
                }

            }
        });

    }
</script>
</body>
</html>
