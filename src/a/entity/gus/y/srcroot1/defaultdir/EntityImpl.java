package a.entity.gus.y.srcroot1.defaultdir;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231212";}
	
	public static final String DIRNAME = "src";
	
	private Service appLocation;

	public EntityImpl() throws Exception
	{
		appLocation = Outside.service(this, "gus.x.app.location");
	}
	
	public Object g() throws Exception
	{
		File location = (File) appLocation.g();
		File parentDir = location.getParentFile();
		File userDir = new File(".").getCanonicalFile();

		File srcDir1 = new File(userDir, DIRNAME);
		if (srcDir1.isDirectory()) return srcDir1;

		File srcDir2 = new File(parentDir, DIRNAME);
		if (srcDir2.isDirectory()) return srcDir2;

		File srcDir3 = new File(parentDir.getParentFile(), DIRNAME);
		if (srcDir3.isDirectory()) return srcDir3;
		
		return null;
	}
}
