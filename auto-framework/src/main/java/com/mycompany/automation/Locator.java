package com.mycompany.automation;

public class Locator {

	private LocatorType locatorType;
	private String locatorValue;
	private String locatorDoc;

	public Locator(LocatorType locatorType,String locatorValue)
	{
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
	}
	public Locator(LocatorType locatorType,String locatorValue,String locatorDoc)
	{
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
		this.locatorDoc = locatorDoc;
	}

	public LocatorType getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}
	public String getLocatorDoc() {
		return locatorDoc;
	}
	public void setLocatorDoc(String locatorDoc) {
		this.locatorDoc = locatorDoc;
	}
	
}
