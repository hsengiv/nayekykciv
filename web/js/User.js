function User(){}

User.signUp = function(){
	if(!User.validateSignUpForm()){
		return;
	}
	var userName = $('#usernametextfield').val();
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
					Common.showMessage(data.message);
					$('#usernametextfield').val("");
				}else{
					Common.showMessage("Sorry! Cannot register now");
					$('#usernametextfield').val("");
				}
			},
			error : function(){
				Common.showMessage("Sorry! Cannot register now");
				$('#usernametextfield').val("");
			}
	};
	Common.doAjax(ajaxData);
}

User.validateSignUpForm = function(type){
	if(type === undefined){
		type = "all";
	}
	var canSignup = true;
	var userName = $('#usernametextfield').val();
	var conPass = $('#confirmpasswordfield').val();
	var priPass = $('#primarypasswordfield').val();
	if(type === "all" || type === "username"){
		if(userName === undefined || $.trim(userName) === ""){
			$("#usernamelabel").text("*username cannot be empty");
			canSignup =  false;
		}else{
			$("#usernamelabel").text('');
		}
	}
	if(type === "all" || type === "password"){
		if(priPass === undefined || $.trim(priPass) === ""){
			$("#primarypasswordlabel").text("*password cannot be empty");
			canSignup =  false;
		}else if(priPass.length < 8 || priPass.length > 15){
			$("#primarypasswordlabel").text("*length should be in 8-15 range");
			canSignup =  false;
		}else{
			$("#primarypasswordlabel").text('');
		}
	}
	if(type === "all" || type === "confirmpassword"){
		if(conPass !== priPass){
			$("#confirmpasswordlabel").text("*password did not match");
			canSignup =  false;
		}else{
			$("#confirmpasswordlabel").text('');
		}
	}
	return canSignup;
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
					Common.showMessage("Sorry! Cannot log out now");
				}
			},
			error : function(){
				Common.showMessage("Sorry! Cannot log out now");
			}
	};
	Common.doAjax(ajaxData);
	
}