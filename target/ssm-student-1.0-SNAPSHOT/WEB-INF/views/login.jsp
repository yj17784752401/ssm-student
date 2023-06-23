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
    <title>学生管理页面</title>
    <style>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="top.jsp"/>
    <div class="row">
        <section class="col-sm-4 offset-sm-4">
            <form>
                <div class="form-group">
                    <label>
                        用户名:
                    </label>
                    <input type="text" name="loginName" class="form-control">
                </div>
                <div class="form-group">
                    <label>
                        密码:
                    </label>
                    <input type="password" name="secretCode" class="form-control">
                </div>
                <div class="form-group">
                    <label>
                        管理员登录:
                    </label>
                    <div class="form-check form-check-inline">
                        <input type="radio" name="loginType" class="form-check-input" checked id="admin" value="admin"/>
                        <label class="form-check-label" for="admin">管理员</label>
                    </div>
                </div>
                <div class="form-group">
                    <label>
                        学生登录:
                    </label>
                    <div class="form-check form-check-inline">
                        <input type="radio" name="loginType" class="form-check-input"
                               value="studentNo" id="studentNo"/>
                        <label class="form-check-label" for="studentNo">学号</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" name="loginType" class="form-check-input"
                               value="studentPhone" id="studentPhone"/>
                        <label class="form-check-label" for="studentPhone">手机</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" name="loginType" class="form-check-input"
                               value="studentEmail" id="studentEmail"/>
                        <label class="form-check-label" for="studentEmail">邮箱</label>
                    </div>
                </div>
                <div class="form-group">
                   <input id="trueLoginBtn" type="button" class="btn btn-primary btn-block" value="登录"/>
                </div>
            </form>
        </section>

    </div>
    <jsp:include page="bottom.jsp"/>
</div>
<script>
<%--    执行登录的方法--%>
    $("#trueLoginBtn").click(function (){
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/login2',
            contentType:"application/json",
            data:JSON.stringify(formDataObj("form")),
            success:function (result) {
                console.log("获取到登录的数据：",JSON.stringify(formDataObj("form")));
                if(result.success){
                //    如果是管理员就跳转到管理员登录页面
                    var loginTypeChecked = $(":radio:checked").val();
                    if ("admin" ===loginTypeChecked){
                        location.href="<%=request.getContextPath()%>/admin/toStudentManage";
                    }else{
                        location.href="<%=request.getContextPath()%>/student/toUpdate?id="+result.data;
                    }
                }else {
                    $("#tipCont").text(result.errMsg);
                    $("#tipModal").modal("show");
                }

            }
        });
    });
</script>
</body>
</html>
