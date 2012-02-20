package com.ddetyuk;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.dao.UserDao;


/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class AdduserServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String userId = req.getParameter("userId");
		String name   = req.getParameter("name");
		String avatar = req.getParameter("avatar");
		String page   = req.getParameter("page");
		String lang   = req.getParameter("lang");
		String sig    = req.getParameter("sig");
		
		User user = new User(userId,avatar,page,name,lang,sig);

		UserDao userdao = new UserDao();
		userdao.create(user);
		
		resp.setContentType("text/javascript");
		resp.getWriter().println("true");
	}
}
