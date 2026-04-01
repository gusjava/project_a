package a.entity.gus06.jdbc.loaddriver;

import a.framework.*;
import java.sql.Driver;
import java.sql.DriverManager;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141006";}
	
	
	public void p(Object obj) throws Exception
	{
		String path = (String) obj;
		
		Class driverClass = Class.forName(path);
		Driver driver = (Driver) driverClass.newInstance();
		DriverManager.registerDriver(driver);
	}
}
