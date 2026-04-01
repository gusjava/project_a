package a.entity.gus06.dir.accessbuilder2.properties;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160313";}
	
	public static final String EXTENSION = "properties";
	

	private Service readFile;
	private Service writeFile;
	private Service dirToSet;


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.properties");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		dirToSet = Outside.service(this,"gus06.dir.children.dirtoset.name0");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File[]) obj);}
	
	
	private Object readFile(File f) throws Exception
	{
		if(f==null || !f.exists()) return null;
		return readFile.t(f);
	}
	
	private Set dirsToSet(File[] dirs) throws Exception
	{
		Set set = new HashSet();
		for(File dir:dirs) set.addAll((Set) dirToSet.t(dir));
		return set;
	}
	
	
	
	
	private class Holder implements T, R, F, G
	{
		private File[] dirs;
		
		public Holder(File[] dirs)
		{
			this.dirs = dirs;
			for(File dir:dirs) dir.mkdirs();
		}
		
		public boolean f(Object obj) throws Exception
		{return file((String) obj)!=null;}
		
		public Object g() throws Exception
		{return dirsToSet(dirs);}
	
		public Object r(String key) throws Exception
		{return readFile(file(key));}
		
		public Object t(Object obj) throws Exception
		{return readFile(file((String) obj));}
		
		
		private File file(String key)
		{
			for(File dir:dirs)
			{
				File f = new File(dir,key+"."+EXTENSION);
				if(f.isFile()) return f;
			}
			return null;
		}
	}
}
