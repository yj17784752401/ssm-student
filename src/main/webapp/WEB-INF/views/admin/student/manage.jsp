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
        .photo-size{
            width: 30px;

        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../../top.jsp"/>
    <div class="row">
<%--        布局管理员toolbar顶部工具栏页面--%>
        <section class="col-sm-12 mb-2">
            <div class="d-flex justify-content-start align-content-center">
                <a href="<%=request.getContextPath()%>/admin/toStudentAdd" class="btn btn-primary mr-3">
                    新增
                </a>
                <a href="#" class="btn btn-primary mr-3" onclick="doDelete()">
                    删除
                </a>
                <form class="form-inline mb-0" id="searchForm" >
                    <input type="text" class="form-control" name="realName" placeholder="请输入用户名"/>
                    <input type="hidden"  name="pageNow" id="pageNow" value="<%=request.getAttribute("pageNow")%>"/>
                    <input type="button" class="btn btn-primary ml-3" onclick="doSearch()" value="查询"/>
                </form>
            </div>
        </section>
<%--        查询出的学生表格--%>
        <section class="col-sm-12">
            <table class="table table-bordered table-hover table-striped">
               <thead>
                    <tr>
                    <th>选择</th>
                    <th>系统标识</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>出生日期</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>免冠照片</th>
                    <th>操作</th>
                    </tr>
               </thead>
                <tbody id="tbody"></tbody>
            </table>
        </section>
<%--        分页--%>
        <section class="col-sm-12" >
            <ul class="pagination">
            </ul>
        </section>
    </div>
    <jsp:include page="../../bottom.jsp"/>
</div>
<script>
    // 加载可分页的表格
    function loadPageableTable(){
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/admin/listStudentByPage',
            data:formDataObj("#searchForm"),
            success:function (result){
                if(result.success){
                    var tableResult=result.date;
                    showTbodyHtml(tableResult);
                    showPageHtml(tableResult);
                }else {
                    $("#tipCont").text(result.errMsg);
                    $("#tipModal").modal("show");
                }
            }
        });
    }
    /*
    * 组装tbody里面的html
    * */
    function showTbodyHtml(tableResult){
        var rows = tableResult.rows;
        var len = rows.length;
        var tbodyHtml='';
        for (var i =0;i<len;i++){
            var oneStudent = rows[i];
            tbodyHtml += '     <tr>\n' +
            '    <td>\n'+
            '    <div class="form-check">\n'+
            '    <input type="checkbox" class="form-check-input" name="idsToDelete" value="'+oneStudent["id"]+'" />\n'+
            '    </div>\n'+
            '        </td>\n'+
            '        <td>'+oneStudent["id"]+'</td>\n'+
            '        <td>'+oneStudent["no"]+'</td>\n'+
            '        <td>'+oneStudent["realName"]+'</td>\n'+
            '        <td>'+oneStudent["birthDay"]+'</td>\n'+
            '        <td>'+oneStudent["phone"]+'</td>\n'+
            '        <td>'+oneStudent["email"]+'</td>\n'+
            '        <td class="text-center">\n'+
            '        <img class="photo-size" src="'+oneStudent["addressablePhotoPath"]+'"/>\n'+
            '        </td>\n'+
            '        <td>\n'+
            '        <a href="<%=request.getContextPath()%>/admin/toUpdateStudent?id='+oneStudent["id"]+'&pageNow='+$("#pageNow").val()+'">更新</a>\n'+
            '        </td>\n'+
            '        </tr>';
        }
        $("#tbody").html(tbodyHtml);
    };
    function showPageHtml(tableResult){
        var pageHtml = '';
        var pageNow = parseInt($("#pageNow").val());
        var pageCount = tableResult["pageCount"];
        if (pageNow !== 1){
            pageHtml += '<li class="page-item"><a class="page-link" href="#" onclick="goFirst()">首页</a></li>\n'+
                            ' <li class="page-item"><a class="page-link" href="#" onclick="goPre()">上一页</a></li>';
        }if (pageNow !== pageCount && pageCount !== 0){
            pageHtml += '<li class="page-item"><a class="page-link" href="#" onclick="goNext()">下一页</a></li>\n'+
                            '<li class="page-item"><a class="page-link" href="#" onclick="goLast('+pageCount+')">尾页</a></li>';
        }
        pageHtml += '<li class="page-item"><span class="page-link">共'+pageCount+'页</span></li>\n' +
                        '<li class="page-item"><span class="page-link">共'+tableResult["totalCount"]+'条</span></li>\n'+
                        '<li class="page-item"><span class="page-link">当前是第'+pageNow+'页</span></li>';
        $(".pagination").html(pageHtml);
    }
    // 查询按钮点击事件
    function doSearch() {
        $("#pageNow").val(1);
        loadPageableTable();

    }
    // 首页
    function goFirst(){
        $("#pageNow").val(1);
        loadPageableTable();
    }
    // 上一页
    function goPre(){
        var crtPageNow =  $("#pageNow").val();
        var prePage = parseInt(crtPageNow) - 1;
        $("#pageNow").val(prePage);
        loadPageableTable();
    }
    // 下一页
    function goNext(){
        var crtPageNow =  $("#pageNow").val();
        var nextPage = parseInt(crtPageNow) + 1;
        $("#pageNow").val(nextPage);
        loadPageableTable();
    }
    // 尾页
    function goLast(pageCount){
        $("#pageNow").val(pageCount);
        loadPageableTable();
    }
    //删除
    function doDelete(){
        //判断选择几行
        var checkedInputs = $("input[name=idsToDelete]:checked");
        if (!checkedInputs || checkedInputs.length ===0){
            $("#tipCont").text("请选择要删除的行")
            $("#tipModal").modal("show");
            return ;
        }
        //获取到选择到行的id
        var idsToDelete =[];
        $.each(checkedInputs,function (i,ele){
            idsToDelete.push($(ele).val());
        });
        //向后台发出删除请求
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/admin/deleteStudentByIds',
            contentType:"application/json",
            data:JSON.stringify({"idsToDelete":idsToDelete}),
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

    // 执行
    loadPageableTable();
</script>
</body>
</html>
