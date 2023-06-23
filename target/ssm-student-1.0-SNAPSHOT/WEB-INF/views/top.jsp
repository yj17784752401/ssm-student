<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="zh-cn">
    <title>top页面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/gijgo/css/gijgo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bs4.6.2/css/bootstrap.css">
    <script  src="<%=request.getContextPath()%>/resources/jquery/jquery.min.js"></script>
<%--    bundle包含了bootstrap.js以及bootstrap本身依赖的第三方库，比如proper--%>
    <script src="<%=request.getContextPath()%>/resources/bs4.6.2/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/gijgo/js/gijgo.js"></script>
    <script src="<%=request.getContextPath()%>/resources/gijgo/js/messages/messages.zh-cn.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
</head>
<body>
<div class="row my-4">
<%--    布局logo图片--%>
    <div class="col-sm-10 pr-0">
        <img src="<%= request.getContextPath()%>/resources/imgs/logo.png" class="img-fluid">
    </div>
    <div class="col-sm-2 d-flex justify-content-center align-items-center" style="background-color: #0062cc;">
        <c:if test="${sessionScope.admin != null || sessionScope.student != null}">
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-warning border-0">退出</a>
        </c:if>
    </div>
<%--    提示框开始--%>
    <div class="modal fade"  data-backdrop="static"  tabindex="-1" id="tipModal">
        <div class="modal-dialog modal-dialog-centered modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" >提示信息</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="tipCont"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
<%--        提示框结束--%>
</div>
</body>
</html>
