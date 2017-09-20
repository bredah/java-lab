package core;

import java.io.File;

public class Helper
{
	/**
	 * Auxiliary Methods
	 */
	private Helper()
	{
		
	}
	
	/**
	 * Path of the main resources directory
	 * 
	 * @return Directory Full Path
	 */
	public static final String getMainResources() {
		return new File("src/main/resources").getAbsoluteFile() + File.separator;
	}
	
	/**
	 * Path of the test resources directory
	 * 
	 * @return Directory Full Path
	 */
	public static final String getTestResources() {
		return new File("src/test/resources").getAbsoluteFile() + File.separator;
	}
}
