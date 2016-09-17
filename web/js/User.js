function User(){}

User.signUp = function(){
	var userName = $('#usernametextfield').val();
	if(userName === undefined || $.trim(userName) === ""){
		alert("Username is empty");
		return;
	}
	var fd = new FormData();
	fd.append("username",userName);
	var ajaxData = {
			type : "POST",
			url : "/register.knv",
			dataType : "json",
			data : fd,
			success : function(data){
				if(data.status !== undefined && data.status == "success"){
					window.open("/index.knv","_self");
				}else if(data.message !== undefined && $.trim(data.message) !== ""){
					alert(data.message);
					$('#usernametextfield').val("");
				}else{
					alert("Sorry! Cannot register now");
					$('#usernametextfield').val("");
				}
			},
			error : function(){
				alert("Sorry! Cannot register now");
				$('#usernametextfield').val("");
			}
	};
	Common.doAjax(ajaxData);
}

User.logOut = function(){
	var ajaxData = {
			type : "GET",
			url : "/logout.knv",
			dataType : "text",
			success : function(data){
				if(data == "success"){
					window.open("/index.knv","_self");
				}else{
					alert("Sorry! Cannot log out now");
				}
			},
			error : function(){
				alert("Sorry! Cannot log out now");
			}
	};
	Common.doAjax(ajaxData);
	
}