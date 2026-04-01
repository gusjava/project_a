package a.entity.gus06.file.video.generic.infomap;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}


	private Service useDsj;
	private Service useJCodec;
	private Service useMatroska1;
	private Service getMime;
	private Service inspectService;

	public EntityImpl() throws Exception
	{
		useDsj = Outside.service(this,"gus06.file.video.dsj.infomap");
		useJCodec = Outside.service(this,"gus06.file.video.jcodec.infomap");
		useMatroska1 = Outside.service(this,"gus06.file.video.matroska.extract.infomap");
		getMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
		inspectService = Outside.service(this,"gus06.service.inspector");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return handleFile((File) obj);
	}
	
	
	private Map handleFile(File file) throws Exception
	{
		try
		{
			String mime = (String) getMime.t(file);
			
			if(mime.equals("video/x-matroska"))
			return useMatroska1(file);
			
			Map m = null;
			
//			m = useDsj(file);
//			if(m!=null) return m;
		
			m = useJCodec(file);
			if(m!=null) return m;
			
			throw new Exception("Null infoMap returned by useJCodec");
		}
		catch(Exception e)
		{
			String message = "Failed to extract infoMap for file: "+file;
			throw new Exception(message,e);
		}
	}
	
	
	
	//FOR DEBUG ...
	
	private Map useDsj(File file) throws Exception
	{
		Object infoMap = null;
		try{infoMap = useDsj.t(file);}
		catch(Exception e){}
		if(infoMap==null) return null;
			
		if(!(infoMap instanceof Map))
		{
			inspectService.p(useDsj);
			throw new Exception("useDsj service returned infoMap of type "+infoMap.getClass()+" for file "+file+" [infoMap="+infoMap+"]");
		}
		return (Map) infoMap;
	}
	
	
	
	private Map useJCodec(File file) throws Exception
	{
		Object infoMap = useJCodec.t(file);
		if(!(infoMap instanceof Map))
		{
			inspectService.p(useJCodec);
			throw new Exception("useJCodec service returned infoMap of type "+infoMap.getClass()+" for file "+file);
		}
		return (Map) infoMap;
	}
	
	
	
	private Map useMatroska1(File file) throws Exception
	{
		Object infoMap = useMatroska1.t(file);
		if(!(infoMap instanceof Map))
		{
			inspectService.p(useMatroska1);
			throw new Exception("useMatroska1 service returned infoMap of type "+infoMap.getClass()+" for file "+file);
		}
		return (Map) infoMap;
	}
}