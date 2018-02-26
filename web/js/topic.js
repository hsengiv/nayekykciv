var Topic = {};
Topic.create = function(){
	var title = $('#createTopicTextBox').val().trim();
	if(title !== undefined && title !== ""){
		var fd = new FormData();
		fd.append("title",title);
		var ajaxData = {
				type : "POST",
				url : "/createtopic.knv",
				dataType : "json",
				data : fd,
				success : function(data){
					window.location.reload();//temp
				},
				error : function(){
					Common.showMessage("Sorry! Cannot register now");
					$('#usernametextfield').val("");
					$('#primarypasswordfield').val("");
					$('#confirmpasswordfield').val("");
				}
		};
		Common.doAjax(ajaxData);
	}else{
		Common.showMessage("Enter topic");
	}
}

Topic.fetchConnectedTopics = function(){
	var ajaxData = {
			type : "GET",
			url : "/fetchconnectedtopics.knv",
			dataType : "json",
			success : function(data){
				if(data != undefined && data.status !== undefined && data.status === "success"){
					var list = data.response;
					if(list != undefined){
						list = list.list;
						for(var i=0;i<list.length;i++){
							var topicJson = list[i];
							var liElement = document.createElement("li");
							var divElement = document.createElement("div")
							$(liElement).append(divElement);
							var spanElement = document.createElement("span");
							$(divElement).append(spanElement);
							$(divElement).addClass("topicLIElement");
							$(spanElement).text(topicJson.title);
							if(User.userId == topicJson.creatorid){
								$('#owntopiclist').append(liElement);
							}else{
								$('#joinedtopiclist').append(liElement);
							}
						}
					}
				}
			},
			error : function(){
				Common.showMessage("Sorry! Cannot log out now");
			}
	};
	Common.doAjax(ajaxData);
}

Topic.fetchAvailableTopics = function(){
	var ajaxData = {
			type : "GET",
			url : "/fetchavailabletopics.knv",
			dataType : "json",
			success : function(data){
				if(data != undefined && data.status !== undefined && data.status === "success"){
					var list = data.response;
					if(list != undefined){
						list = list.list;
						for(var i=0;i<list.length;i++){
							var topicJson = list[i];
							var liElement = document.createElement("li");
							var divElement = document.createElement("div")
							$(liElement).append(divElement);
							var spanElement = document.createElement("span");
							$(divElement).append(spanElement);
							$(divElement).addClass("topicLIElement");
							$(spanElement).addClass("fL");
							var joinDiv = document.createElement("div");
							$(divElement).append(joinDiv);
							$(joinDiv).addClass("availableTopicJoinButton");
							$(joinDiv).text("Join");
							$(joinDiv).attr("onclick","Topic.joinTopic("+topicJson.topicid+")");
							$(spanElement).text(topicJson.title);
							$("#availabletopiclist").append(liElement);
						}
					}
				}
			},
			error : function(){
				Common.showMessage("Sorry! Cannot log out now");
			}
	};
	Common.doAjax(ajaxData);
}

Topic.joinTopic = function(topicId){
	var fd = new FormData();
	fd.append("topicId",topicId);
	var ajaxData = {
				type : "POST",
				url : "/jointopic.knv",
				dataType : "json",
				data : fd,
				success : function(data){
					window.location.reload();//temp
				},
				error : function(){
					Common.showMessage("Sorry! Cannot register now");
					
				}
		};
		Common.doAjax(ajaxData);
}