<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery-2.1.3.js"></script>
    <%--  <script src="https://cdn.bootcss.com/jquery/2.2.0/jquery.min.js"></script>--%>
</head>

<body>
<h2>Hello World!</h2>

<form id=stu>
    姓名：<input type="text" name="stu.name" id="name">
    年龄：<input type="text" name="stu.age" id="age">
    班级：<input type="text" name="stu.classesId" id="classesId" value="4028b88162e19fc20162e1a082790002">
    <button id="te" type="button">提交</button>
</form>


<script>
    $("#te").click(function () {
        var name = $("#name").val();
        var age = $("#age").val();
        var classesId = $("#classesId").val();
        var params = {
            "stu.name": name,
            "stu.age": age,
            "stu.classesId":classesId
        }

        console.log(params);
        $.post("add", params, function (data) {
            console.log(data);
            alert(data.msg);
        })
    })

</script>
</body>
</html>
