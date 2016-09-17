package action.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import common.Constants;

import action.ActionClassParent;

public class RegisterAction extends ActionClassParent{
	public String execute()throws Exception{
		HttpSession curSession = request.getSession(true);
		String userName = null;
		if(request.getParameter("username") != null){
			userName = request.getParameter("username").toString();
		}
		if(userName != null){
			curSession.setAttribute(Constants.USERNAME_FOR_SESSION, userName);
			return "success";
		}
		RequestDispatcher rd = request.getRequestDispatcher("/error.knv");
		rd.forward(request, response);
		return null;
	}
}
