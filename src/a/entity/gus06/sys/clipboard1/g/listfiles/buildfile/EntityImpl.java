package a.entity.gus06.sys.clipboard1.g.listfiles.buildfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20151006";}


	private Service now;
	private File storeDir;
	

	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
		File defaultDir = (File) Outside.resource(this,"defaultdir");
		
		storeDir = new File(defaultDir,"temp");
		storeDir.mkdirs();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[])
			return buildCustom((Object[]) obj);
		if(obj instanceof String)
			return buildDefault((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private File buildCustom(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return build((File) o[0], (String) o[1]);
	}
	
	
	private File buildDefault(String s) throws Exception
	{
		return build(storeDir,s);
	}
	
	
	private File build(File dir, String ext) throws Exception
	{
		return new File(dir,name(ext));
	}
	
	
	private String name(String ext) throws Exception
	{
		StringBuffer b = new StringBuffer();
		b.append((String) now.g());
		if(ext!=null && !ext.equals("")) b.append("."+ext);
		return b.toString();
	}
	
	
	
	public Object g() throws Exception
	{return storeDir;}
}
