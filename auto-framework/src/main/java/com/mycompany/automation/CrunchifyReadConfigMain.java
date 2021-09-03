package com.mycompany.automation;

import java.io.IOException;

/**
 * @author Crunchify.com
 *
 */

public class CrunchifyReadConfigMain {

    public static void main(String[] args) throws IOException {
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
        properties.getPropValues();
    }
}