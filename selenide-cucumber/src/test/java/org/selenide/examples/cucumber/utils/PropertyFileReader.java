package org.selenide.examples.cucumber.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

	
	public String getPropertyValue(File propertyFile,String key) {
		
		String propertyValue = null;
		try {
			InputStream input = new FileInputStream(propertyFile);
            Properties prop = new Properties();
         // load a properties file
            prop.load(input);
			return prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return propertyValue;
	}
	
}
