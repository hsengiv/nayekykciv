package action.topic;

import action.ActionClassParent;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import topic.TopicUtil;
import common.CommonUtil;
import common.Constants;

public class CreateTopic extends ActionClassParent {
	@Override
	public String execute() throws Exception {
		String status = "failure";
		String message = "";
		JSONObject responseData = new JSONObject();
		HttpSession currentSession = request.getSession(false);
		String title = null;
		String userId = null;
		if (currentSession != null
				&& currentSession.getAttribute(Constants.USERNAME_FOR_SESSION) != null) {
			userId = currentSession
					.getAttribute(Constants.USERNAME_FOR_SESSION).toString();
		}
		if (request.getParameter("title") != null) {
			title = request.getParameter("title");
		}
		String topicId = TopicUtil.createTopic(title, userId);
		if(topicId != null){
			status = "success";
			responseData.put("topicId", topicId);
		}
		responseData.put("status", status);
		CommonUtil.writeResponse(response, responseData.toString());
		return null;
	}
}
