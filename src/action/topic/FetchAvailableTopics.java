package action.topic;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import action.ActionClassParent;
import topic.TopicUtil;
import common.CommonUtil;
import common.Constants;

public class FetchAvailableTopics extends ActionClassParent  {
	public String execute() throws Exception {
		String status = "failure";
		String message = "";
		JSONObject responseData = new JSONObject();
		HttpSession currentSession = request.getSession(false);
		String userId = null;
		if (currentSession != null
				&& currentSession.getAttribute(Constants.USERNAME_FOR_SESSION) != null) {
			userId = currentSession
					.getAttribute(Constants.USERNAME_FOR_SESSION).toString();
		}
		JSONObject resultList = TopicUtil.getAllAvailableTopicsByUser(userId);
		if(resultList != null){
			status = "success";
		}
		responseData.put("status", status);
		responseData.put("response", resultList);
		CommonUtil.writeResponse(response, responseData.toString());
		return null;
	}
}
