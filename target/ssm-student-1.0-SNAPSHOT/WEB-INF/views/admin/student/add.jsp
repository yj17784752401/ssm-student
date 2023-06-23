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
    <title>学生添加页面</title>
    <style>
        .gj-datepicker-md [role="right-icon"]{
            top:8px !important;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../../top.jsp"/>
    <div class="row">
        <section class="col-sm-4 offset-sm-4">
            <form id="addForm" enctype="application/x-www-form-urlencoded" novalidate>
<%--                表单项--%>
                <div class="form-group">
                    <label for="no">
                        学号:
                    </label>
                    <input type="text" class="form-control" name="no" id="no" required/>
                    <div class="invalid-feedback">学号不能为空</div>
                </div>
                <div class="form-group">
                    <label for="realName">
                         姓名:
                    </label>
                    <input type="text" class="form-control" name="realName" id="realName" required>
                    <div class="invalid-feedback">姓名不能为空</div>
                </div>
                <div class="form-group">
                    <label for="birthDay">
                        生日:
                    </label>
                    <input type="text" readonly class="form-control" name="birthDay" id="birthDay" required>
                    <div class="invalid-feedback">生日不能为空</div>
                </div>
                <div class="form-group">
                    <label for="phone">
                        手机:
                    </label>
                    <input type="text" class="form-control" name="phone" id="phone" autocomplete="off" required>
                    <div class="invalid-feedback">手机号不能为空</div>
                </div>
                <div class="form-group">
                    <label for="email">
                        邮箱:
                    </label>
                    <input type="email" class="form-control" name="email" id="email" required>
                    <div class="invalid-feedback">请输入合法邮箱</div>
                </div>
                <div class="d-flex justify-content-around">
                    <input type="button" onclick="doAddStudent()" class="btn btn-primary" value="新增" />
                    <a class="btn btn-primary" href="#" onclick="history.back()">返回</a>
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
    function doAddStudent(){
        /*1.form表单上加上novalidate
        2.在form表单项上写上校验规则以及对应的提示信息
        3.在提交表单时进行校验并加上相应的css类
        */
        // checkValidity不是jequery的方法，是js原生的
        var validateResult= document.getElementById("addForm").checkValidity();
        $("#addForm").addClass("was-validated");
        if (!validateResult){
            return;
        }
        // 提交数据到后台
        // 封装通用的获取表单数据的方法
        /*
        *(5) [{…}, {…}, {…}, {…}, {…}]
            0:{name: 'no', value: '123'}
            1: {name: 'realName', value: '余江'}
            2: {name: 'birthDay', value: '2023-06-01'}
            3: {name: 'phone', value: '17784752401'}
            4: {name: 'email', value: '1306879592@qq.com'}
            length:5
        *
        * */
        console.log(formDataObj("#addForm"))
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/admin/addStudent',
            data:formDataObj("#addForm"),
            success:function (result) {
                if(result.success){
                   location.href="<%=request.getContextPath()%>/admin/toStudentManage"
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
