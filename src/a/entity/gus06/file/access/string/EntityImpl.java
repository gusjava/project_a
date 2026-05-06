package a.entity.gus06.file.access.string;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T{

	public String creationDate() {return "20191121";}
	
	
	private Service readString;
	private Service writeString;
	
	public EntityImpl() throws Exception
	{
		readString = Outside.service(this,"gus.x.file.string.read.v1");
		writeString = Outside.service(this,"gus.x.file.string.write");
	}
	

	public Object t(Object obj) throws Exception
	{return new FileHolder((File)obj);}

	
	
	private class FileHolder implements P, G
	{	
		private File file;
		public FileHolder(File file){this.file = file;}
		
		public void p(Object obj) throws Exception
		{writeString.p(new Object[]{file,obj});}
		
		public Object g() throws Exception
		{return readString.t(file);}
	}

}
