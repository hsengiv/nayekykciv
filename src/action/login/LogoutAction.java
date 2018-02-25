package action.login;

import javax.servlet.http.HttpSession;

import common.CommonUtil;

import action.ActionClassParent;

public class LogoutAction extends ActionClassParent {
	@Override
	public String execute() throws Exception {
		HttpSession curSession = request.getSession(false);
		if (curSession != null) {
			curSession.invalidate();
		}
		CommonUtil.writeResponse(response, "success");
		return null;
	}
}
