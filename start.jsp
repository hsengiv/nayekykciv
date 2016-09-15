<html>
<head>
	<script type="text/javascript">
		var socke = null;
		function connect(){
			if(socke === null){
			var name = document.getElementById("name").value;
			socke = new WebSocket("ws://192.168.0.3:8080/testing/chat/"+name);
			socke.onopen = onopen;
			socke.onerror = onerror;
			socke.onmessage = receivemessage;
		}
		}
		function onerror(event){
			alert("cannot connect now");
		}
		function onopen(event){
			document.getElementById("before").style.display = "none";
			document.getElementById("messageDiv").innerText = "";
			document.getElementById("after").style.display = "block";
		}
		function sendMessage(event){
			if(event !== undefined && event.keyCode !== undefined && event.keyCode == '13'){

			var message = document.getElementById("message").value;
			document.getElementById("message").value = "";
			socke.send(message);
		}
		}
		function receivemessage(event)
		{
			var x =document.getElementById("messageDiv");
			x.innerText = x.innerText + "\n" + event.data;
		}
		function leave(event){
			if(socke !== null){
				socke.close();
				socke = null;
				document.getElementById("before").style.display = "block";
				document.getElementById("after").style.display = "none";
			}
		}
		//window.addEventListener("load", connect, false);
		</script>
</head>
<body>
	<div id="before">Enter your name : <input type="text" id="name"> <button onclick="connect()">Connect</button><br></div>
	<div id="after" style="display:none">
	<button onclick="leave()">Leave</button><br>
	Enter message : <input type="text" id="message" onkeyup="sendMessage(event)"> 
	<div id="messageDiv">
		
	</div>
	</div>
</body>
</html>