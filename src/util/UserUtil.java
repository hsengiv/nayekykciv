
package util;

import java.sql.ResultSet;
import java.util.ArrayList;

import user.User;
import common.CommonUtil;
import common.Constants;
import database.CommonQuery;
import database.DBUtil;

public class UserUtil {

	public static boolean isUserNameExist(String userName) {
		try {
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from ")
					.append(Constants.USERACCOUNT_TABLE_NAME).append(" where ")
					.append(Constants.USERACCOUNT_COLUMN_USERNAME)
					.append("=?;");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			if (resultSet.next()) {
				dbConn.close();
				return true; // sd
			}
		} catch (Exception e) {

		}
		return false;
	}

	public static User addNewUser(String userName, String password) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("insert into ")
					.append(Constants.USERACCOUNT_TABLE_NAME)
					.append("(USERNAME,PASSWORD)").append(" values(?,?);");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			values.add(password);
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			ResultSet resultSet = dbConn
					.executeUpdate(values, query.toString());
			dbConn.close();
			if (resultSet.first()) {
				int uid = DBUtil.getAutoGeneratedKeyFromResultSet(resultSet);
				return getUserObjectUID(String.valueOf(uid));
			}
		} catch (Exception e) {

		}
		return null;
	}

	public static User getUserObjectUID(String uid) {
		try{
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from ")
					.append(Constants.USERACCOUNT_TABLE_NAME).append(" where ")
					.append("USER_ID")
					.append("=?;");
			ArrayList<String> values = new ArrayList<String>();
			values.add(String.valueOf(uid));
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			if (resultSet.next()) {
				dbConn.close();
				return getUserObjectFromResultSet(resultSet);
			}
		}catch(Exception e){
			
		}
		return null;
	}

	public static boolean isValidUser(String userName, String password) {
		// TODO Auto-generated method stub
		try {
			ResultSet resultSet = null;
			resultSet = getUserRow(userName, password);
			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public static ResultSet getUserRow(String userName, String password) {
		try {
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from ")
					.append(Constants.USERACCOUNT_TABLE_NAME).append(" where ")
					.append(Constants.USERACCOUNT_COLUMN_USERNAME)
					.append("=? and ")
					.append(Constants.USERACCOUNT_COLUMN_PASSWORD)
					.append("=?;");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			values.add(password);
			CommonQuery dbConn = new CommonQuery(
					CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			if (resultSet.next()) {
				dbConn.close();
				return resultSet;
			}
		} catch (Exception e) {

		}
		return null;
	}

	public static User getUserObjectByCredentials(String userName,
			String password) {
		try {
			ResultSet resultSet = null;
			resultSet = getUserRow(userName, password);
			if (resultSet != null) {
				return getUserObjectFromResultSet(resultSet);
			}
		} catch (Exception e) {

		}
		return null;
	}

	public static User getUserObjectFromResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				return new User(resultSet.getString("USER_ID"),
						resultSet.getString("NAME"),
						resultSet.getString("USERNAME"));
			}
		} catch (Exception e) {

		}
		return null;
	}
}
