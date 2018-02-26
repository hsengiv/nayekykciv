
<%
String userId = "";
String userName = "";
if(session.getAttribute("username") != null){
	userName = 	(String)session.getAttribute("username");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Relax Hub</title>
<script src="/js/thirdparties/jquery-3.1.0.min.js"></script>
<script src="/js/Common.js"></script>
<script src="/js/User.js"></script>
<script src="/js/Socket.js"></script>
<script src="/js/topic.js"></script>
<script src="/js/message.js"></script>

<!-- styles -->
<link href="/styles/common.css" rel="stylesheet" type="text/css" />
<link href="/styles/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript">
		User.userId = "<%=userName%>";
		User.init();
	</script>
	<div class="topbar">
		<div class="fL pL10 pT12">Relax Hub</div>
		<div class="topicInputParent">
			<input class="topicTextBox" type="text" id="createTopicTextBox">
			<div class="fR" style="padding-left: 30px; padding-top: 5px;"
				onclick="Topic.create();">Start Topic</div>
		</div>
		<div class="fR">
			<div class="fL pT12 pR30"><%=userName %></div>
			<div onclick="User.logOut()" class="fL pT12 pR5">Logout</div>
		</div>
	</div>

	<div class="mainDiv">
		<div class="fL mytopiclistParentDiv">
			<div class="mytopiclistDiv">
				<ul id="owntopiclist">
					<li class="topicheader">
						Created by you
					</li>
				</ul>
			</div>

			<div class="mytopiclistDiv" style="border-top: black;border-style: solid;border-left: none;border-bottom: none; border-right: none;">
				<ul id="joinedtopiclist">
					<li class="topicheader">
						Your participation
					</li>
				</ul>
			</div>
		</div>
		<div class="fL middleContainer" id="chatContainer">
			
			<div class="middleContainerTopicTitle" id="currentChatTopicTitle"></div>

			<div style="float: left;width: 100%;height: 85%;">


				<div style="float: left; height: 80%;width: 100%;">
					<ul id="messagesList">
					</ul>
				</div>


				<div style="float: left;bottom: 0px;width: 100%;height: 20%;">
					<input onkeyup="Message.sendMessageOnEnter(event)" id="messageinputbox" type="text" style="width: 100%;height: 100%;">

				</div>
			</div>
			
		</div>
		<div class="fL availableTopicContainer">
    		<div><ul id="availabletopiclist"><li class="topicheader">Available Topics</li></ul></div>
</div>
	</div>
</body>
</html>