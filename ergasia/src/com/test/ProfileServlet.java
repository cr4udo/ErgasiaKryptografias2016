package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ProfileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		request.getRequestDispatcher("link.html").include(request, response);
		
		Cookie ck[]=request.getCookies();
		if(ck!=null){
		 String userCookie=ck[0].getValue();
		 String name;
		
			try {
				name = LoginServlet.getUsername(userCookie);
				if(!name.equals("")||name!=null){
					if(name.equals("admin")){
						out.print("You are successfully logged in as admin!");
						out.print("<br>Welcome, "+name);

					}else if(name.equals("karavo")){
						out.print("You are successfully logged in as guest!");
						out.print("<br>Welcome, "+name);

					}else if(name.equals("winch")){
						out.print("You are successfully logged in as normal account!");
						out.print("<br>Welcome, "+name);

					}
				}
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		}else{
			out.print("Please login first");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}



}
