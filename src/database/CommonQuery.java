package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;

public class CommonQuery {
	private Connection connection = null;
	private PreparedStatement preparedstmnt = null;
	private ResultSet resultSet = null;
	private static String driverClass = "com.mysql.jdbc.Driver";
	
	public CommonQuery(String database){
		try{
			Class.forName(driverClass);
			String dbHost = CommonUtil.getCommonProperties("mysql_host", "localhost");
			String uname = CommonUtil.getCommonProperties("mysql_uname", "root");
			String pass = CommonUtil.getCommonProperties("mysql_password", "");
			StringBuilder url=new StringBuilder();
			url.append("jdbc:mysql://").append(dbHost).append("/").append(database).append("?user=").append(uname).append("&password=").append(pass);
			this.connection = DriverManager.getConnection(url.toString());
		}catch(Exception e){
			
		}
	}
	public void constructQuery(String query,ArrayList<String> values){
		try{
			preparedstmnt = connection.prepareStatement(query);
			int length = values.size();
			for(int i=0;i<length;i++){
				preparedstmnt.setString(i+1, values.get(i).toString());
			}
		}catch(Exception e){
			
		}
	}
	public ResultSet executeSelect(String query,ArrayList<String> values){
		try{
			constructQuery(query, values);
			resultSet = preparedstmnt.executeQuery();
			resultSet.beforeFirst();
			return resultSet;
		}catch(Exception e){
			
		}
		return null;
	}
	
	public int executeUpdate(String query,ArrayList<String> values){
		try{
			constructQuery(query, values);
			int affectedRowCount =  preparedstmnt.executeUpdate();
			return affectedRowCount;
		}catch(Exception e){
			
		}
		return -1;
	}
	public void close() {
        try {
        		if(resultSet != null){
        			resultSet.close();
        		}
                if (preparedstmnt != null) {
                        preparedstmnt.close();
                }
                if (connection != null) {
                        connection.close();
                }
        } catch (Exception e) {

        }
}
}
