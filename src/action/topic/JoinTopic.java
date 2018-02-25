package action.topic;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import action.ActionClassParent;
import topic.TopicUtil;
import common.CommonUtil;
import common.Constants;

public class JoinTopic extends ActionClassParent  {
	public String execute() throws Exception {
		String status = "failure";
		String message = "";
		JSONObject responseData = new JSONObject();
		HttpSession currentSession = request.getSession(false);
		String userId = null;
		String topicId = null;
		if (currentSession != null
				&& currentSession.getAttribute(Constants.USERNAME_FOR_SESSION) != null) {
			userId = currentSession
					.getAttribute(Constants.USERNAME_FOR_SESSION).toString();
		}
		if (request.getParameter("topicId") != null) {
			topicId = request.getParameter("topicId");
		}
		boolean isJoined = false;
		isJoined = TopicUtil.addUserToTopic(topicId,userId);
		if(isJoined){
			status = "success";
		}
		responseData.put("status", status);
		CommonUtil.writeResponse(response, responseData.toString());
		return null;
	}
}
