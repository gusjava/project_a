package a.entity.gus06.dir.accessbuilder.properties;

import a.framework.*;
import java.io.File;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150329";}
	
	public static final String EXTENSION = "properties";

	private Service readFile;
	private Service writeFile;
	private Service dirToSet;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		dirToSet = Outside.service(this,"gus06.dir.children.dirtoset.name0");
	}
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	private Object readFile(File f) throws Exception
	{
		if(f==null || !f.exists()) return null;
		return readFile.t(f);
	}
	
	private class Holder implements T, R, V, F, G
	{
		private File dir;
		
		public Holder(File dir)
		{
			this.dir = dir;
			dir.mkdirs();
		}
		
		public boolean f(Object obj) throws Exception
		{return file((String) obj).exists();}
		
		public Object g() throws Exception
		{return dirToSet.t(dir);}
	
		public Object r(String key) throws Exception
		{return readFile(file(key));}
		
		public Object t(Object obj) throws Exception
		{return readFile(file((String) obj));}
	
		public void v(String key, Object obj) throws Exception
		{
			File f = file(key);
			if(obj==null) delete(f);
			else write(f,obj);
		}
		
		private File file(String key)
		{
			if(!dir.exists()) dir.mkdirs();
			return new File(dir,key+"."+EXTENSION);
		}
		
		private void delete(File f) throws Exception
		{if(!f.delete()) throw new Exception("Could not delete file: "+f);}
		
		private void write(File f, Object obj) throws Exception
		{writeFile.p(new Object[]{f,obj});}
	}
}
