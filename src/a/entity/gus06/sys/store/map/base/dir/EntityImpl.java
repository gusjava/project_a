package a.entity.gus06.sys.store.map.base.dir;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140929";}


	private Service readFile;
	private File dir;



	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		dir = (File) Outside.resource(this,"path#path.storedir");
	}
	
	
	public Object r(String key) throws Exception
	{return map(key);}
	
	
	public Object t(Object obj) throws Exception
	{return map((String) obj);}
	
	
	
	private Map map(String id) throws Exception
	{
		if(dir==null || !dir.isDirectory()) return null;
		File f = new File(dir,id);
		if(!f.isFile()) return null;
		return (Map) readFile.t(f);
	}
}
