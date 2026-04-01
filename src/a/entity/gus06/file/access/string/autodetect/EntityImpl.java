package a.entity.gus06.file.access.string.autodetect;

import java.io.File;
import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T{

	public String creationDate() {return "20221108";}
	
	
	private Service findCharset;
	private Service readString;
	private Service writeString;
	
	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
		readString = Outside.service(this,"gus06.file.read.string.cs");
		writeString = Outside.service(this,"gus06.file.write.string.cs");
	}
	

	public Object t(Object obj) throws Exception
	{return new FileHolder((File)obj);}

	
	
	private class FileHolder implements P, G
	{	
		private File file;
		private Charset charset;
		
		public FileHolder(File file) throws Exception
		{
			this.file = file;
			this.charset = charset(file);
		}
		
		public void p(Object obj) throws Exception
		{writeString.p(new Object[]{file,charset,obj});}
		
		public Object g() throws Exception
		{return readString.t(new Object[]{file,charset});}
	}
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}

}