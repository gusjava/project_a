package a.entity.gus06.file.read.raw.v1;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160526";}

	public static final int BUFFER_SIZE = 4096;
	public static final int EOF = -1;
	
	
	public Object t(Object obj) throws Exception
	{return read((File) obj);}
	
	
	
	private byte[] read(File file) throws Exception
	{
		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try
		{
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			
			int read = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while((read = ios.read(buffer)) != EOF)
			ous.write(buffer,0,read);
		}
		finally
		{
			try{if(ous != null) ous.close();}
			catch(IOException e) {}

			try{if(ios != null) ios.close();}
			catch(IOException e) {}
		}
		return ous.toByteArray();
	}
}
