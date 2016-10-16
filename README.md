# ErgasiaKryptografias2016
##Exploiting encrypted cookies for fun and profit

 based on this article https://spring.io/blog/2014/01/20/exploiting-encrypted-cookies-for-fun-and-profit,
 I created a Java Dynamic Web Project named "ergasia" , which illustrates a web site that stores the users credentials in the browser's 
 cookies with the  AES encryption with CBC mode. Then using a chrome plugin named "EditThisCookie" I copy the cookie's string value ,
 only to use it later to my java application "CookieDecrypt" in order to create an other cookie that will help me impersonate 
 another user (in this case I want to gain admin access).Then I copy the new cookie , and edit the already existing in my browser
 in order to gain admin access.
 
Video Demonstration
https://www.dropbox.com/sh/867rz4ucwqx06hp/AAA4ODw-i6yZ0KMI8su0bQv9a?dl=0
 
 In the dropbox link I have 2 demonstration videos where I login as a norml user(karavo) and as guest user(winch) , and with the help 
 of the cookie exploit I gain admin access 
 
There is a zip file that has the external libraries you will need in to import into both projects so you can run Base64() function.

There are 2 folders for this project the folder "ergasia"  and "cookieDecrypt".
In the foledr "ergasia" is the Java Dynamic Web Project , I used apache tomcat v.8.0.x. in order to run it .This is just a website with login , logout and profile options (there 3 users right now karavo,winch and admin) .In the login option once you have given the correct credentials it shows you encrypted cookie sring and the appropriate welcome message .In the profile option once you have logged in you can 
see the appropriate welcome message. From there if we change the cookie value , with an admin impersonating cookie we can see if we succeeded in gaining admin access or not .

In the folder "cookieDecrypt" there is our main class in which we paste the encrypted cookie string and the name of the user that we used to enter the site , so it can provide us with a new encrypted cookie in order to gain  admin privelege in the "site".(see demonstration2 video from dropbox where I login as Karavo and then change the cookie t login as admin).
