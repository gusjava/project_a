package a.entity.gus06.file.audio.play.generic;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20250521";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.file.audio.play.generic.findplayer");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile())
			throw new Exception("Invalid file: "+file);
		
		try
		{
			P builder = (P) find.t(file);
			builder.p(file);
		}
		catch(Exception e)
		{
			String message = "Failed to play audio from file: "+file.getAbsolutePath();
			Outside.err(this,"t(Object)",new Exception(message,e));
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile())
			throw new Exception("Invalid file: "+file);
		
		try
		{
			T builder = (T) find.t(file);
			return builder.t(file);
		}
		catch(Exception e)
		{
			String message = "Failed to build audio player from file: "+file.getAbsolutePath();
			Outside.err(this,"t(Object)",new Exception(message,e));
			return "ERR:"+e;
		}
	}
}