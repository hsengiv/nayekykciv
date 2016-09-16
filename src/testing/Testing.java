package testing;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class Testing extends ActionSupport implements ServletRequestAware, ServletResponseAware{
		public HttpServletResponse response;
		public HttpServletRequest request;
		
		
		public void setServletResponse(HttpServletResponse response) {
                this.response = response;
        }
        public void setServletRequest(HttpServletRequest request) {
                this.request = request;
        }
        public String execute()throws Exception{
        	String name = request.getParameter("name");
        	if("vignesh".equals(name)){
        		return "success";
        	}
        	return "failure";
        }
}