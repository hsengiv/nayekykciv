package topic;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import common.CommonUtil;
import common.Constants;
import database.CommonQuery;
import database.DBUtil;

public class TopicUtil {

	public static String createTopic(String title, String userId) {

		try {

			StringBuilder query = new StringBuilder();
			query.append("insert into ").append("TOPIC_DETAILS")
					.append("(TOPIC_TITLE,CREATED_BY)").append(" values(?,?);");
			ArrayList<String> values = new ArrayList<String>();
			values.add(title);
			values.add(userId);
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));

			ResultSet resultSet = dbConn
					.executeUpdate(values, query.toString());
			int topicId = DBUtil.getAutoGeneratedKeyFromResultSet(resultSet);
			TopicUtil.addUserToTopic(topicId,userId);
			dbConn.close();
			return String.valueOf(topicId);

		} catch (Exception e) {

		}
		return null;
	}

	private static boolean addUserToTopic(int topicId, String userId) {
		// TODO Auto-generated method stub
		try {

			StringBuilder query = new StringBuilder();
			query.append("insert into ").append(" TOPIC_USER_MAPPING")
					.append("(TOPIC_ID,USER_ID)").append(" values(?,?);");
			ArrayList<String> values = new ArrayList<String>();
			values.add(String.valueOf(topicId));
			values.add(userId);
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));

			int  status = dbConn
					.executeUpdate(query.toString(),values);
			if(status >=1){
				return true;
			}
			dbConn.close();

		} catch (Exception e) {

		}
		return false;
	}

	public static JSONObject getAllConnectedTopicsByUser(String userId) {
		/*this query will retrieve password also. need to work again */
		try{
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from TOPIC_USER_MAPPING as a INNER JOIN TOPIC_DETAILS as b on a.TOPIC_ID = b.TOPIC_ID INNER JOIN USERACCOUNT as c on a.USER_ID = c.USER_ID where a.USER_ID = ?");
			ArrayList<String> values = new ArrayList<String>();
			values.add(String.valueOf(userId));
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			JSONObject listJson = new JSONObject();
			JSONArray list = new JSONArray();
			while(resultSet.next()){
				JSONObject topicJson = new JSONObject();
				topicJson.put("topicid", resultSet.getString("TOPIC_ID"));
				topicJson.put("title", resultSet.getString("TOPIC_TITLE"));
				topicJson.put("creatorname", resultSet.getString("USERNAME"));
				topicJson.put("creatorid", resultSet.getString("USER_ID"));
				list.put(topicJson);
			}
			listJson.put("list", list);
			return listJson;
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static JSONObject getOwnConnectedTopicsByUser(String userId){
		return null;
	}
	
	public static JSONObject getJoinedConnectedTopicsByUser(String userId){
		return null;
	}

	public static JSONObject getAllAvailableTopicsByUser(String userId) {
		/*this query will retrieve password also. need to work again */
		try{
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from (select * from TOPIC_USER_MAPPING where USER_ID != ?) as a INNER JOIN TOPIC_DETAILS as b on a.TOPIC_ID = b.TOPIC_ID INNER JOIN USERACCOUNT as c on a.USER_ID = c.USER_ID");
			ArrayList<String> values = new ArrayList<String>();
			values.add(String.valueOf(userId));
			values.add(String.valueOf(userId));
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			JSONObject listJson = new JSONObject();
			JSONArray list = new JSONArray();
			while(resultSet.next()){
				JSONObject topicJson = new JSONObject();
				topicJson.put("topicid", resultSet.getString("TOPIC_ID"));
				topicJson.put("title", resultSet.getString("TOPIC_TITLE"));
				topicJson.put("creatorname", resultSet.getString("USERNAME"));
				topicJson.put("creatorid", resultSet.getString("USER_ID"));
				list.put(topicJson);
			}
			listJson.put("list", list);
			return listJson;
		}catch(Exception e){
			
		}
		return null;
	}

}
