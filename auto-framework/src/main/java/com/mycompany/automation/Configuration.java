package com.mycompany.automation;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	private static Properties FramwWorkProperties;
	public static String sProjectLocation;

	static {
		try {
			File oFile = new File(".project");
			sProjectLocation = oFile.getAbsolutePath();
			System.out.println("sProjectLocation"+sProjectLocation);

			sProjectLocation = sProjectLocation.substring(0,
					sProjectLocation.lastIndexOf("/"));
			FramwWorkProperties = new ClasspathPropertyFileLoader()
					.loadProperties("framework.properties");
			System.out.println(FramwWorkProperties.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String REMOTE_EXECUTE = FramwWorkProperties
			.getProperty("remote.execution");

	public static final String HUB_URL = FramwWorkProperties
			.getProperty("hubUrl");

	// URL against which the test is going to run.
	public static final String APP_URL = FramwWorkProperties
			.getProperty("app.url");

	public static final String BROWSER = FramwWorkProperties
			.getProperty("browser");
	public static final String BROWSER_VERSION = FramwWorkProperties
			.getProperty("browserVersion");


	public static final String PLATFORM = FramwWorkProperties
			.getProperty("platform");
	
	public static final int PAGE_LOAD_TIMEOUT = Integer
			.parseInt(FramwWorkProperties.getProperty("pageLoadTimeout"));

	public static final int SCRIPT_TIMEOUT = Integer
			.parseInt(FramwWorkProperties.getProperty("scriptTimeout"));

	public static final int IMPLICITLY_WAIT = Integer
			.parseInt(FramwWorkProperties.getProperty("implicitlyWait"));

	public static final String SCREENSHOT_PATH = FramwWorkProperties
			.getProperty("screenshotpath");

	public static final String INCLUDE_GROUP = FramwWorkProperties
			.getProperty("test.groups");

	public static final String EXCLUDE_GROUP = FramwWorkProperties
			.getProperty("test.excludeGroups");


	// MySQL related data
	public static String MYSQL_SERVER_IP = FramwWorkProperties
			.getProperty("mysql.server.ip");
	public static String MYSQL_USER = FramwWorkProperties
			.getProperty("mysql.user");
	public static String MYSQL_PASSWORD = FramwWorkProperties
			.getProperty("mysql.password");
	public static String MYSQL_DATABASE = FramwWorkProperties
			.getProperty("mysql.database");

	
	public static final String USER_ID = FramwWorkProperties
			.getProperty("user.id");

	public static final String USER_PWD = FramwWorkProperties
			.getProperty("user.password");

}
