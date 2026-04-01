package a.entity.gus06.file.access.properties;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T{

	public String creationDate() {return "20191121";}
	
	
	private Service readProp;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	

	public Object t(Object obj) throws Exception
	{return new FileHolder((File)obj);}

	
	
	private class FileHolder implements P, G
	{	
		private File file;
		public FileHolder(File file){this.file = file;}
		
		public void p(Object obj) throws Exception
		{writeProp.p(new Object[]{file,obj});}
		
		public Object g() throws Exception
		{return readProp.t(file);}
	}

}
