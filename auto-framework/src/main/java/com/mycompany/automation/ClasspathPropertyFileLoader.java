package com.mycompany.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClasspathPropertyFileLoader extends Properties {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Properties loadProperties(String propertyFile) throws IOException {
        Properties properties = new Properties();
        InputStream stream = null;
        try {
         
        // this is loading null values for properties	
           stream = this.getClass().getResourceAsStream( propertyFile);
            File dir1 = new File(".");
          /*  if (stream == null) {
                stream = new FileInputStream(dir1.getCanonicalFile() + "/src/main/resources/" + propertyFile);
            }*/
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return properties;

    }
}
