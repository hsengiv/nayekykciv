package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import common.CommonUtil;

public class CommonListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {

		} catch (Exception e) {

		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			CommonUtil.loadCommonProperties();
		} catch (Exception e) {

		}
	}

}
