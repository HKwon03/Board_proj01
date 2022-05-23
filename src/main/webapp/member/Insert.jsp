<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function validateForm (form) {
		if (form.name.value == "" || form.name.value.length == 0) {
			alert ("작성자를 입력하세요");
			form.name.focus();
			return false;
		}
		if (form.title.value == "" || form.title.value.length == 0) {
			alert ("제목을 입력하세요");
			form.title.focus();
			return false;
		}
		if (form.content.value == "" || form.content.value.length == 0) {
			alert ("내용을 입력하세요");
			form.content.focus();
			return false;
		}
		if (form.pass.value == "" || form.pass.value.length == 0) {
			alert ("암호를 입력하세요");
			form.pass.focus();
			return false;
		}
		
	}
 
 </script>

</head>
<body>
<h2> 게시판 - Insert.jsp</h2>

<form name = "insertFrm" method = "post" action = "../member/insert.do" 
		onsubmit = "return validateForm(this)">
	<table border = "1" width = "90%">
		<tr>
			<td> ID </td>
			<td> <input type = "text" name = "id" style = "width:200px;"></td>
		</tr>
		<tr>
			<td> PWD </td>
			<td> <input type = "password" name = "pwd" style = "width:40%;"></td>
		</tr>
		<tr>
			<td> NAME </td>
			<td> <input type = "text" name = "name" style = "width:40%;"></td>
		</tr>
		<tr>
			<td> EMAIL </td>
			<td> <input type = "text" name = "email" style = "width:40%;"></td>
		</tr>
		<tr>
			<td> JOINDATE </td>
			<td> <input type = "text" name = "joindate" style = "width:40%;"></td>
		</tr>
		
		<tr>
			<td colspan = "2" align = "center">
				<button type = "submit"> 작성 완료</button>
				<button type = "reset"> RESET </button>
				<button type = "button" onclick = "location.href = '../member/list.do';">
					목록 바로가기
				</button>
				
			</td>
			
		</tr>
	
	</table>
	
</form>	

</body>
</html>