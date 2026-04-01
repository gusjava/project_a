package a.entity.gus06.app.init.jar;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140925";}
	
	public static final String KEY_JARDIR_DELETE_OBSOLETE = "app.jardir.delete.obsolete";
	

	private Service extractor;
	private Service delete;
	private Service propBoolDT;

	private File dir;
	private Map infos;
	

	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"gus06.app.init.jar.extrator");
		delete = Outside.service(this,"gus06.dirfile.op.delete");
		propBoolDT = Outside.service(this,"propbool_dt");
		
		dir = (File) Outside.resource(this,"path#path.jardir");
		infos = (Map) Outside.resource(this,"inside#prop.jar_location");
		
		if(infos==null) return;
		
		if(propBoolDT.f(KEY_JARDIR_DELETE_OBSOLETE)) 
			deleteObsoleteJar();
		
		Iterator it = infos.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) infos.get(key);
			
			File file = new File(dir,key);
			if(!file.exists()) initFile(file,value);
		}
	}
	
	
	private void deleteObsoleteJar() throws Exception
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			String key = f.getName();
			if(!infos.containsKey(key)) delete.p(f);
		}
	}
	
	
	
	private void initFile(File file, String value)
	{
		try{extractor.v(value,file);}
		catch(Exception e)
		{Outside.err(this,"initFile(File,String)",e);}
	}
}