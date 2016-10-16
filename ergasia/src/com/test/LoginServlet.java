package com.test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class LoginServlet extends HttpServlet {
	public static SecretKey aesKey = createKey();
	private Map<String,User> userToPassword = createUsernameToUser();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		request.getRequestDispatcher("link.html").include(request, response);
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		try {
			String userCookie = login(name, password);
			
			String user = getUsername(userCookie);
			Cookie ck=new Cookie("name",userCookie);
			out.print(userCookie);
			out.print("<br>"+ck+"<br>");
			out.print("<br>"+"aeskey:"+aesKey+"<br>");
			
			
			if(userCookie.equals("error")){
				out.print("sorry, username or password error!");  
		         request.getRequestDispatcher("login.html").include(request, response);
		         response.addCookie(ck);
			}else{
				
				response.addCookie(ck);
			if(user.equals("admin")){
				out.print("You are successfully logged in as admin!");
				out.print("<br>Welcome, "+user);
				
				
				response.addCookie(ck);
			}else if(user.equals("karavo")){
				out.print("You are successfully logged in as guest!");
				out.print("<br>Welcome, "+user);
				
				
				response.addCookie(ck);
				
			}else if(user.equals("winch")){
				
				out.print("You are successfully logged in as normal user!");
				out.print("<br>Welcome, "+user);
				
				
				response.addCookie(ck);
				
			}
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		out.close();
	}
	public String login(String username, String password) throws GeneralSecurityException {
		User user = userToPassword.get(username);
		System.out.println("plainText: ");
		if(user != null && !password.equals(user.getPassword())) {
			System.out.println("error"); 
			return "error";
		}
		System.out.println(username);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);

		String originalText = "username=" + username + "&firstName="+user.getFirstName()+"&lastName="+user.getLastName();

		byte[] encrypted = cipher.doFinal(originalText.getBytes());
		byte[] iv = cipher.getIV();	
		System.out.println("IV is: "+iv); 
		return Utils.createEncryptedCookie(iv, encrypted);
	}
	
	private Map<String,User> createUsernameToUser() {
		Map<String, User> usernameToUser = new HashMap<>();
		usernameToUser.put("winch", new User("winch", "secret", "Rob", "Winch"));
		usernameToUser.put("karavo", new User("karavo", "kar", "Andrew", "Karavokiris"));
		usernameToUser.put("admin", new User("admin", "topsecret", "The", "Admin"));
		usernameToUser.put("a", new User("a", "a", "a", "a"));
		return usernameToUser;
	}
	
	private static SecretKey createKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			return keyGen.generateKey();
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public static String getUsername(String encryptedCookie) throws GeneralSecurityException {
		byte[] iv = Utils.extractIv(encryptedCookie);
		byte[] encryptedMessageBytes = Utils.extractMessage(encryptedCookie);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
		String decryptedCookie = new String(cipher.doFinal(encryptedMessageBytes));

		System.out.println("plainText: "+ decryptedCookie);

		int startIndex = decryptedCookie.indexOf("username=") + 9;
		int endIndex = decryptedCookie.indexOf("&", startIndex);
		return decryptedCookie.substring(startIndex, endIndex);
	}
}


