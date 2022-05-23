<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>a{text-decoration:none;}</style>
</head>
<body>
	<h2> 게시판 - 목록 보기(List)</h2>
	    <table border="1" width="90%">
        <tr>
            <th width="10%">ID</th>
            <th width="*">NAME</th>
            <th width="15%">EMAIL</th>
            <th width="10%">JOINDATE</th>
            <th width="10%">DELETE</th>
        </tr>
<c:choose>    
    <c:when test="${ empty boardLists }">  <!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when>
    <c:otherwise>  <!-- 게시물이 있을 때 -->
        <c:forEach items="${ boardLists }" var="row" varStatus="loop">    
        <tr align="center">
            <td>${ row.id }</td> 
            <td>${ row.name }</td> 
            <td>${ row.email }</td> 
            <td>${ row.joindate }</td>  
            <td><a href="../member/Delete.jsp">[DELETE]</a></td>
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>
    
        <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%">
        <tr align="right">
            <td width="100"><button type="button"
                onclick="location.href='../member/insert.do';">글쓰기</button></td>
        </tr>
    </table>


</body>
</html>