package a.entity.gus06.file.tool.waituntil.written;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230207";}

	public static final long SLEEP = 300;
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		long size = file.length();
		sleep();
		
		while(size==0 || file.length()>size)
		{
			size = file.length();
			sleep();
		}
	}
	
	private void sleep()
	{
		try{Thread.sleep(SLEEP);}
		catch(InterruptedException e) {}
	}
}