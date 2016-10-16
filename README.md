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
 
