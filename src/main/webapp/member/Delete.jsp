<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
	function validateForm(form) {
		if (form.pass.value == "") {
			alert("비밀번호를 입력하세요");
			form.pass.focus();
			return false;
		}
	}


</script>

<title>지우기</title>
</head>
<body>

<form name = "deleteFrm" method = "post" action = "../member/delete.do" onsubmit = "return validateForm(this);">
	<input type = "hidden" name = "id" value = "${param.id }" />
	
	<table border = "1" width = "90%">
		<tr>
			<td>비밀번호 : </td>
			<td> 
				<input type = "password" name = "pass" style = "width:100px" />
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "center">
				<button type = "submit"> 지우기 </button>
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