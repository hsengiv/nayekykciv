<html>
<head>
<script src="/js/thirdparties/jquery-3.1.0.min.js"></script>
<script src="/js/Common.js"></script>
<script src="/js/User.js"></script>
</head>
<body>
<%
if(session.getAttribute("username") != null){
	%> Welcome home <%=session.getAttribute("username")%>
<%}
%>
<input type="button" onclick="User.logOut()" value="Logout" />
</body>
</html>