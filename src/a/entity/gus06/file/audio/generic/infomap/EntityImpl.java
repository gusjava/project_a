package a.entity.gus06.file.audio.generic.infomap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231007";}


	private Service useDsj;
	private Service getMime;
	private Service inspectService;

	public EntityImpl() throws Exception
	{
		useDsj = Outside.service(this,"gus06.file.audio.dsj.infomap");
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
			
			// pour l'instant rien ne marche... il faudra trouver un remplacement � dsj
			return new HashMap();
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
}
