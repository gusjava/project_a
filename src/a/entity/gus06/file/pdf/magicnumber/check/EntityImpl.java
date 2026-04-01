package a.entity.gus06.file.pdf.magicnumber.check;

import a.framework.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150813";}

	public static final String PDF_MAGIC = "%PDF-";

	
	
	public boolean f(Object obj) throws Exception
	{
		InputStream is = null;
		DataInputStream in = null;
    	
		int minorVersion = -1;
		int majorVersion = -1;

		try
		{
			is = toInputStream(obj);
			in = new DataInputStream(is);
			
			int length = PDF_MAGIC.length();
			for(int i=0;i<length;i++)
			if(PDF_MAGIC.charAt(i) != in.readChar()) return false;
		}
		finally
		{
			if(in!=null) in.close();
			if(is!=null) is.close();
		}
		return true;
	}
	
	
	
	
	
	private InputStream toInputStream(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return (InputStream) obj;
		if(obj instanceof File) return new FileInputStream((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
