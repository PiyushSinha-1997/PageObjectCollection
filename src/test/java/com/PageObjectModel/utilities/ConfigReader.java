package com.PageObjectModel.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	public static Properties prop;

	public static Properties init_prop() {
		{
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config.properties");
			try {
				FileInputStream ip = new FileInputStream(src);
				prop = new Properties();
				prop.load(ip);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return prop;
		}
	}

	public static String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}

	public static String getusername() {
		String usrname = prop.getProperty("username");
		return usrname;
	}

	public static String getpassword() {
		String psw = prop.getProperty("password");
		return psw;
	}
}
