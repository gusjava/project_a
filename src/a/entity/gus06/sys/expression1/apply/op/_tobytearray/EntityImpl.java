package a.entity.gus06.sys.expression1.apply.op._tobytearray;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160303";}
	
	private Service fileToBinary;
	private Service imageToBinary;
	
	public EntityImpl() throws Exception
	{
		fileToBinary = Outside.service(this,"gus06.file.read.raw");
		imageToBinary = Outside.service(this,"gus06.awt.bufferedimage.topng.raw");
	}
	
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof byte[]) return obj;
		if(obj instanceof String) return ((String) obj).getBytes("UTF8");
		
		if(obj instanceof File)
		{
			File file = (File) obj;
			return file.isFile()?fileToBinary.t(file):null;
		}
		if(obj instanceof BufferedImage)
		{
			return imageToBinary.t(obj);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
