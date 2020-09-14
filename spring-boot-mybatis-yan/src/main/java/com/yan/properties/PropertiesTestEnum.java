package com.yan.properties;

/**
 * @ConfigurationProperties子属性测试enum
 * @author master-yan
 *
 */
public enum PropertiesTestEnum {

	ONE("一一一", 111), 
	TWO("二二二", 222);
	
	private String enumTestString;
	
	private int enumTestInt;
	
	private PropertiesTestEnum(String enumTestString, int enumTestInt) {
		this.enumTestString = enumTestString;
		this.enumTestInt = enumTestInt;
	}

	public String getEnumTestString() {
		return enumTestString;
	}

	public void setEnumTestString(String enumTestString) {
		this.enumTestString = enumTestString;
	}

	public int getEnumTestInt() {
		return enumTestInt;
	}

	public void setEnumTestInt(int enumTestInt) {
		this.enumTestInt = enumTestInt;
	}

}