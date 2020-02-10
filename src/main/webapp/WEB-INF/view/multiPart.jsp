<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MultiPart</title>
</head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){
	if('${message}' != ""){
		alert('${message}');
	}
})
</script>
<form action="/exSubUrl/fileUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="서버전달"/>
</form>

</body>
</html>