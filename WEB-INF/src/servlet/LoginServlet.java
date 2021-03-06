package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import control.LoginManager;


	public class LoginServlet extends HttpServlet{

		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doPost(request, response);
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{

			request.setCharacterEncoding("UTF-8");

			String user_id = request.getParameter("user_id");
			String user_password = request.getParameter("user_password");


			LoginManager manager = new LoginManager();

			User user=new User();

			user = manager.certifyUser(user_id,user_password);

			if(null==user){
				request.setAttribute("error","IDとパスワードをもう一度確認してください");
				getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request,response);
			}
			else{
				HttpSession session=request.getSession();
				session.setAttribute("user",user);
				if(user.getUser_role()==0){
					response.sendRedirect(response.encodeRedirectURL("./UserTop.jsp"));
				}
				else{
					response.sendRedirect(response.encodeRedirectURL("./admintop.jsp"));
				}
			}
		}
	}


