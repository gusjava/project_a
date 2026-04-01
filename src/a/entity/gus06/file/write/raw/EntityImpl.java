package a.entity.gus06.file.write.raw;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170205";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		byte[] data = (byte[]) o[1];
		
		write(file, data);
	}
	
	
	
	private void write(File file, byte[] data) throws Exception
	{
		FileOutputStream fos = null;
		try
		{
			File parent = file.getParentFile();
			if(!parent.exists()) parent.mkdirs();
		
			fos = new FileOutputStream(file);
			fos.write(data);
		}
		finally {if(fos!=null) fos.close();}
	}
}
