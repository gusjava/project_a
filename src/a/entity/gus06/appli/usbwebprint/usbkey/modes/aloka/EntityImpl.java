package a.entity.gus06.appli.usbwebprint.usbkey.modes.aloka;

import a.framework.*;
import java.io.File;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140910";}


	public static final String ROOT1 = "ALOKA";
	public static final String ROOT2 = "ALOKA_US";


	public Object t(Object obj) throws Exception
	{return findRoot((File) obj);}

	
	
	private File findRoot(File drive) throws Exception
	{
		File root1 = new File(drive,ROOT1);
		if(root1.isDirectory()) return root1;
		
		File root2 = new File(drive,ROOT2);
		if(root2.isDirectory()) return root2;
		
		return null;
	}
}
