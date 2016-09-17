package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtil {
	private static Properties commonProperties = null;
	
	public static void loadCommonProperties(){
        FileInputStream fis = null;
        if (commonProperties == null) {
            try {
                commonProperties = new Properties();
                fis = new FileInputStream("/WEB-INF/conf/common.properties");
                commonProperties.load(fis);
            } catch (Exception ex) {
               
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        
                    }
                }
            }
        }
    
	}
	public static String getCommonProperties(String key,String defaultValue){
		return commonProperties == null ? defaultValue : commonProperties.getProperty(key, defaultValue);
	}
}
