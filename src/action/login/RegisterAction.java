package action.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import util.UserUtil;
import common.CommonUtil;
import common.Constants;
import action.ActionClassParent;

public class RegisterAction extends ActionClassParent{
	public String execute()throws Exception{
		HttpSession curSession = request.getSession(true);
		String userName = null;
		String status = "failure";
		String message = "";
		JSONObject responseData = new JSONObject();
		if(request.getParameter("username") != null){
			userName = request.getParameter("username").toString();
		}
		if(userName != null){
			if(!UserUtil.isUserNameExist(userName)){
				curSession.setAttribute(Constants.USERNAME_FOR_SESSION, userName);
				status =  "success";
			}else{
				message = "Username already exists :(";
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/error.knv");
			rd.forward(request, response);
			return null;
		}
		responseData.put("status", status).put("message",message);
		CommonUtil.writeResponse(response, responseData.toString());
		return null;
	}
}
