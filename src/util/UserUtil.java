package util;

import java.sql.ResultSet;
import java.util.ArrayList;

import common.CommonUtil;
import common.Constants;
import database.CommonQuery;

public class UserUtil {
	
	public static boolean isUserNameExist(String userName){
		try{
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from ").append(Constants.USERACCOUNT_TABLE_NAME).append(" where ").append(Constants.USERACCOUNT_COLUMN_USERNAME).append("=?;");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			CommonQuery dbConn = new CommonQuery(CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			if(resultSet.next()){
				dbConn.close();
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
	public static boolean addNewUser(String userName,String password){
		try{
			StringBuilder query = new StringBuilder();
			query.append("insert into ").append(Constants.USERACCOUNT_TABLE_NAME).append(" values(?,?);");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			values.add(password);
			CommonQuery dbConn = new CommonQuery(CommonUtil.getCommonProperties("database_name", "USERINFO"));
			int status = dbConn.executeUpdate(query.toString(), values);
			dbConn.close();
			if(status == 1){
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
	public static boolean isValidUser(String userName, String password) {
		// TODO Auto-generated method stub
		try{
			StringBuilder query = new StringBuilder();
			ResultSet resultSet = null;
			query.append("select * from ").append(Constants.USERACCOUNT_TABLE_NAME).append(" where ").append(Constants.USERACCOUNT_COLUMN_USERNAME).append("=? and ").append(Constants.USERACCOUNT_COLUMN_PASSWORD).append("=?;");
			ArrayList<String> values = new ArrayList<String>();
			values.add(userName);
			values.add(password);
			CommonQuery dbConn = new CommonQuery(CommonUtil.getCommonProperties("database_name", "USERINFO"));
			resultSet = dbConn.executeSelect(query.toString(), values);
			if(resultSet.next()){
				dbConn.close();
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
}
