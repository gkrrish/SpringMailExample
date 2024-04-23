package com.abc.utils;

public class EmailUtils {

	public static String getEmailVerificataionMessage(String name) {

		return "Hello " + name
				+ ", \n\nYour new account has been created. Soon you will get the link to verify your account.";
				
	}
}
