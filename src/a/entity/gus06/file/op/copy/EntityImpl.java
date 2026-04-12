package a.entity.gus06.file.op.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140706";}

	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isFile()) throw new Exception("Invalid input file: "+in);
		
		if(in.equals(out)) return;
		if(out.exists()) throw new Exception("Output already exists: "+out);
		
		File parent = out.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try
		{
			fis = new FileInputStream(in);
			fos = new FileOutputStream(out);

			FileChannel inputChannel = fis.getChannel();
			FileChannel outputChannel = fos.getChannel();

			outputChannel.transferFrom(inputChannel,0,in.length());
		}
		finally
		{
			if(fis!=null) fis.close();
			if(fos!=null) fos.close();
		}
	}
}
