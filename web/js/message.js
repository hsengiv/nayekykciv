var Message = {};

Message.sendMessageOnEnter = function(event){
	if(event !== undefined && event.keyCode !== undefined && event.keyCode == '13'){
			var messageJson = {};
			var topicid = $('#chatContainer').attr("topicid");
			if(topicid !== undefined){
				messageJson.topicid = topicid;
				messageJson.type = "topicmessage";
				messageJson.message = $('#messageinputbox').val();
				Socket.connectObject.send(JSON.stringify(messageJson));
			}
			$('#messageinputbox').val("");
		}
}

Message.constructUIForMessage = function(messageJson){
	var currentTopicId = $('#chatContainer').attr("topicid");
	if(messageJson.topicid != currentTopicId){
		Topic.openTopic(messageJson.topicid)
	}
		var message = messageJson.message;
		var liElement = document.createElement("li");
		$(liElement).text(messageJson.message);
		if(User.userId != messageJson.senderid){
			message = messageJson.senderusername + " : " + message;
		}else{
			message = "You" + " : " + message;
			$(liElement).addClass("messageSentByCurrentUser");
		}
		$(liElement).text(message);
		$('#messagesList').append(liElement);
	
}