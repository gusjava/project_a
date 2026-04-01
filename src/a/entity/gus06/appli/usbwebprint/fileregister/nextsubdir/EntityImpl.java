package a.entity.gus06.appli.usbwebprint.fileregister.nextsubdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140916";}


	public static final int COUNT_LENGTH = 8;

	private Service counter;


	private File queueDir;

	public EntityImpl() throws Exception
	{
		counter = Outside.service(this,"gus06.app.persister1.counter");
		queueDir = (File) Outside.resource(this,"path#path.app.queuedir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String name = name((String) obj);
		File newDir = new File(queueDir,name);
    	
		if(newDir.exists()) throw new Exception("New sub dir already exists inside queue dir: "+name);
		newDir.mkdirs();
		return newDir;
	}
	
	
	
	
	private String name(String examId) throws Exception
	{
		String count = (String) counter.r(getClass().getName()+"_count");
		return formatCount(count)+"_"+examId;
	}
	
	
	
	
	private String formatCount(String s)
	{
		while(s.length()<COUNT_LENGTH) s = "0"+s;
		return s;
	}
}
