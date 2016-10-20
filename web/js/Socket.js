var Socket = {
	connectObject : undefined,
	onOpen : function(event){
		$('#messagecontainer').text("");
	},
	onError : function(event){
		$('#messagecontainer').append("Could not connect to server :|");
	},
	onMessage : function(event){
		$('#messagecontainer').append("<br>"+event.data);
	},
	connect : function(){
		if(this.connectObject === undefined){
			var url = "ws://"+window.location.host+"/chat/connect/"+User.userName;
			this.connectObject = new WebSocket(url);
			this.connectObject.onopen = this.onOpen;
			this.connectObject.onerror = this.onError;
			this.connectObject.onmessage = this.onMessage;
		}
	},
	sendMessage : function(event){
		if(event !== undefined && event.keyCode !== undefined && event.keyCode == '13'){
			this.connectObject.send($('#messagetextbox').val());
			$('#messagetextbox').val("");
		}
	}
};

