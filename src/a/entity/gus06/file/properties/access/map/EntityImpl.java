package a.entity.gus06.file.properties.access.map;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200315";}


	private Service readProp;
	private Service writeProp;
	private Service getter;
	private Service setter;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		getter = Outside.service(this,"gus06.file.properties.perform.field.get");
		setter = Outside.service(this,"gus06.file.properties.perform.field.put");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new FileHolder((File)obj);}

	
	
	private class FileHolder implements P, G, V, R
	{	
		private File file;
		public FileHolder(File file){this.file = file;}
		
		public void v(String key, Object obj) throws Exception
		{setter.p(new Object[]{file,key,obj});}
		
		public Object r(String key) throws Exception
		{return getter.t(new Object[]{file,key});}
		
		public void p(Object obj) throws Exception
		{writeProp.p(new Object[]{file,obj});}
		
		public Object g() throws Exception
		{return readProp.t(file);}
	}
}
