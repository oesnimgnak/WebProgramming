<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = request.getParameter("name");
String id = request.getParameter("id");
String password = request.getParameter("password");
String gender = request.getParameter("gender");
String inotice = request.getParameter("inotice");
String cnotice = request.getParameter("cnotice");
String dnotice = request.getParameter("dnotice");
String job = request.getParameter("job");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
<h1>회원 가입 결과</h1>
<p>이름 :<%=name %></p>
<p>아이디 :<%=id %> </p>
<p>비밀번호 :<%=password %></p>
<p>성별 :<%= (gender.equals("male")? "남성" : "여성") %> </p>
<p>직업 :<%=job %></p>
<p>위의 내용으로 회원 가입이 완료되었습니다!</p>

</body>
</html>