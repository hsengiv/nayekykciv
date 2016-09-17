package filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Constants;
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestUri = httpRequest.getRequestURI();
		
		/*
		 * For empty URI we should redirect to index pag
		 */
		
		if("/".equals(requestUri)){
			httpResponse.sendRedirect("/index.knv");
			return;
		}
		
		boolean isAuthenticated = false;
		String toRedirect = requestUri;
		HttpSession currentSession = httpRequest.getSession(false);
		
		/*
		 * We will consider current user as logged in user by the following conditions
		 * 1) username cookie in session
		 * 2) that username should be in database
		 */
		if(currentSession != null && currentSession.getAttribute(Constants.USERNAME_FOR_SESSION) != null){
			isAuthenticated = true;
		}
		/*
		 * if unauthenticated we should redirect to signin page except three links
		 *  Those two links are signup and signin users are not authenticated 
		 * 	For that below check
		 * 
		 * If user is authenticated then all these urls should go to index page (signup,signin,login,register)
		*/
		if(!isAuthenticated){
			if(!"/register.knv".equals(requestUri) && !"/login.knv".equals(requestUri) && !"/signup.knv".equals(requestUri)){
				toRedirect = "/signin.knv";
			}
		}else if(isAuthenticated && ("/register.knv".equals(requestUri) || "/login.knv".equals(requestUri) || "/signup.knv".equals(requestUri) || "/signin.knv".equals(requestUri))){
			toRedirect = "/index.knv";
		}
		RequestDispatcher rd = httpRequest.getRequestDispatcher(toRedirect);
		rd.forward(request, response);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
