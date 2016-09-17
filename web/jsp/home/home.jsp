<%
if(session.getAttribute("username") != null){
	%> Welcome home <%=session.getAttribute("username")%>
<%}
%>

<a href="/logout.knv">Logout</a>