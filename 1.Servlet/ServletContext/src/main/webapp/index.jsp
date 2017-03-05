<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.css" rel="stylesheet">
<title>文件上传实例</title>
</head>

<body>
<h2>Hello World!</h2>
<form class="form-control" enctype="multipart/form-data" action="upload" method="post">
			<div class="form-group-lg">
				<label>文件输入</label>
				<input  type="file" name="file1" />
			</div>
			<button type="submit">提交</button>
		</form>
</body>
</html>
