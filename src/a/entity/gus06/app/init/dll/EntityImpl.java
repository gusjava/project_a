package a.entity.gus06.app.init.dll;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity {

	public String creationDate() {return "20150607";}


	private Service extractor;
	private Service delete;

	private File dir;
	private Map infos;
	

	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"gus06.app.init.dll.extrator");
		delete = Outside.service(this,"gus06.dirfile.op.delete");
		
		dir = (File) Outside.resource(this,"path#path.dlldir");
		infos = (Map) Outside.resource(this,"inside#prop.dll_location");
		
		if(infos==null) return;
		
		
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			String key = f.getName();
			if(!infos.containsKey(key)) delete.p(f);
		}
		
		Iterator it = infos.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) infos.get(key);
			
			File file = new File(dir,key);
			if(!file.exists()) initFile(file,value);
		}
	}
	
	
	
	private void initFile(File file, String value)
	{
		try{extractor.v(value,file);}
		catch(Exception e)
		{Outside.err(this,"initFile(File,String)",e);}
	}
}
