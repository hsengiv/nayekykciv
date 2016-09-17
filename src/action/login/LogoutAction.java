package action.login;

import javax.servlet.http.HttpSession;

import action.ActionClassParent;

public class LogoutAction extends ActionClassParent{
	public String execute()throws Exception{
		HttpSession curSession = request.getSession(false);
		if(curSession != null){
			curSession.invalidate();
		}
		return "success";
	}
}
