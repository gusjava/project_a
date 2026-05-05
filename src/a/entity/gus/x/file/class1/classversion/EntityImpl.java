package a.entity.gus.x.file.class1.classversion;

import a.framework.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140807";}

	public static final int JAVA_MAGIC = 0xCAFEBABE;

	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = null;
		DataInputStream in = null;
    	
		int minorVersion = -1;
		int majorVersion = -1;

		try
		{
			is = toInputStream(obj);
			in = new DataInputStream(is);

			int magic = in.readInt();
			if(magic!=JAVA_MAGIC) throw new Exception("Invalid magic number: "+magic);

			minorVersion = in.readUnsignedShort();
			majorVersion = in.readUnsignedShort();
		}
		finally
		{
			if(in!=null) in.close();
			if(is!=null) is.close();
		}
		return new int[]{majorVersion,minorVersion};
	}
	
	
	private InputStream toInputStream(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return (InputStream) obj;
		if(obj instanceof File) return new FileInputStream((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
