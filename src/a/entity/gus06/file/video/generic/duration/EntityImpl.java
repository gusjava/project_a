package a.entity.gus06.file.video.generic.duration;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}


	private Service useDsj;
	private Service useJCodec;
	private Service matroska1;
	private Service getMime;

	public EntityImpl() throws Exception
	{
		useDsj = Outside.service(this,"gus06.file.video.dsj.duration");
		useJCodec = Outside.service(this,"gus06.file.video.jcodec.duration");
		matroska1 = Outside.service(this,"gus06.file.video.matroska.extract.duration");
		getMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return handleFile((File) obj);
	}
	
	private Long handleFile(File file) throws Exception
	{
		try
		{
			String mime = (String) getMime.t(file);
			if(mime.equals("video/x-matroska")) return (Long) matroska1.t(file);
			
			Long duration = (Long) useDsj(file);
			if(duration!=null) return duration;
			
			duration = (Long) useJCodec.t(file);
			if(duration!=null) return duration;
			
			throw new Exception("Duration not found for file: "+file);
		}
		catch(Exception e)
		{
			String message = "Failed to extract duration for file: "+file;
			throw new Exception(message,e);
		}
	}
	
	
	
	private Object useDsj(File file)
	{
		try{return useDsj.t(file);}
		catch(Exception e){}return null;
	}
}