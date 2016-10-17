function Common(){}

Common.doAjax = function(data){
	if(data !== undefined){
		if(data.type === "POST"){
			data.processData =  false;
			data.contentType = false;
		}
		$.ajax(data);
	}
}

Common.showMessage = function(message){
	alert(message);
}