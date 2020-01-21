<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>변경 URI</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
var test;
$(function(){
	if($("#dupYn").val() == "Y"){
		alert("이미 입력된 URL 입니다.");
	}
})
function fn_save(){
	if($("#oriUrl").val() == ""){
		alert("url을 입력해주시기 바랍니다.");
		return;
	}
	var form = document.insertForm;
    form.submit();
}
function fn_ajax(){
	$.ajax({
		url:"/exSubUrl/getData",
		type:"GET", 
 		contentType:"application/json;charset=utf-8",
		dataType:"json",
		data:{
			sendData:"sendValue"
		},
		error:function(xhr,status, msg){
			alert(xhr+" : "+status+" : "+msg);
		},	
		success:function(data){
			console.log(data);
			alert(data.result);
		}
	});
}
function test(){
	alert("done");
}

function fn_xmlMarshalling(){
	$.ajax({
		url:"/exSubUrl/getDatatoXml",
		type:"GET", 
 		contentType:"application/xml;charset=utf-8",
		dataType:"xml",
		error:function(xhr,status, msg){
			alert(msg);
		},	
		success:function(data){
			alert("success done");
		}
	});
}

function fn_downloadtest(){
	var form = document.createElement("form");
	form.action = "/exSubUrl/downloadtest_real.do";
	var inputFirst = document.createElement("input");
	var inputSecond = document.createElement("input");
	inputFirst.setAttribute("type","hidden");
	inputFirst.setAttribute("name","inputFirst");
	inputFirst.setAttribute("value","inputFirstValue");
	
	inputSecond.setAttribute("type","hidden");
	inputSecond.setAttribute("name","inputSecond");
	inputSecond.setAttribute("value","inputSecondValue");
	form.appendChild(inputFirst);
	form.appendChild(inputSecond);
	document.body.appendChild(form);
	form.submit();
}

function fn_redirectAttribute(){
	var form = document.createElement("form");
	form.action = "/exSubUrl/callRedirectTest.do";
	var inputFirst = document.createElement("input");
	var inputSecond = document.createElement("input");
	inputFirst.setAttribute("type","hidden");
	inputFirst.setAttribute("name","inputFirst");
	inputFirst.setAttribute("value","inputFirstValue");
	
	inputSecond.setAttribute("type","hidden");
	inputSecond.setAttribute("name","inputSecond");
	inputSecond.setAttribute("value","inputSecondValue");
	form.appendChild(inputFirst);
	form.appendChild(inputSecond);
	document.body.appendChild(form);
	form.submit();
}
</script>
<body>
<div class="jb-responsive-table">
    <form action="/exSubUrl/insertData.do" method="post" name="insertForm">
        <input type="hidden" id="dupYn" name="dupYn" value='${dupYn}'/>
        <input type="text" id="changeString" name="changeString" value='${changeString}'/>
		<table>
			<div style="width:50%;">
				<tr>
					<td><input type="text" id="oriUrl" name="oriUrl" style="width: 315px;"/></td>
					<td><input type="button" value="Save" onclick="fn_save()"/></td>
					<td><input type="button" value="Download test" onclick="fn_downloadtest()"/></td>
					<td><input type="button" value="Ajax test" onclick="fn_ajax()"/></td>
					<td><input type="button" value="Xml Marshalling Test" onclick="fn_xmlMarshalling()"/></td>
					<td><input type="button" value="redirectAttribute Test" onclick="fn_redirectAttribute()"/></td>
				</tr>
	        </div>
        </table>
	</form>
	<table class="txc-table" width="50%" cellspacing="0" cellpadding="0" border="0" style="border: none; border-collapse: collapse; width: 723px;" 맑은="" 고딕",="" sans-serif;font-size:13px"="">
		<tbody>
			<tr>
				<td style="width: 250px; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204); background-color: rgb(152, 0, 0);">
					<p style="text-align: center;">
						<span style="color: rgb(255, 255, 255);">ORIGIN URL</span>
					</p>
				</td>
				<td style="width: 250px; border-bottom: 1px solid rgb(204, 204, 204); border-right: 1px solid rgb(204, 204, 204); border-top: 1px solid rgb(204, 204, 204); background-color: rgb(152, 0, 0);">
					<p style="text-align: center;">
					    <span style="color: rgb(255, 255, 255);">SUB URL</span>
					</p>
				</td>
			</tr>
			<c:forEach items="${list}" var="asList">
			    <tr>
					<td style="width: 250px; border-bottom: 1px solid rgb(204, 204, 204); border-right: 1px solid rgb(204, 204, 204); border-left: 1px solid rgb(204, 204, 204);">
						<p style="text-align: left;">${asList.oriUrl}</p>
					</td>
					<td style="width: 250px; border-bottom: 1px solid rgb(204, 204, 204); border-right: 1px solid rgb(204, 204, 204);">
						<p style="text-align: left;">${asList.subUrl}</p>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>