<html>
<head>
<script src="/js/thirdparties/jquery-3.1.0.min.js"></script>
<script src="/js/Common.js"></script>
<script src="/js/User.js"></script>
<script src="/js/Socket.js"></script>
</head>
<body>
<script>
<%
if(session.getAttribute("username") != null){
	%> User.userName = '<%=session.getAttribute("username")%>';
<%}
%>
Socket.connect();
</script>
<%=session.getAttribute("username")%><input type="button" onclick="User.logOut()" value="Logout" />
<br>
Enter message : <input type="text" id="messagetextbox" onkeyup="Socket.sendMessage(event)"></input>
	<div id="messagecontainer">
		
	</div>
</body>
</html>