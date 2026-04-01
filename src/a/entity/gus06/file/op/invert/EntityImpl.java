package a.entity.gus06.file.op.invert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220513";}


	private Service buildTmpFile;

	public EntityImpl() throws Exception
	{
		buildTmpFile = Outside.service(this,"gus06.file.tmpfile");
	}

	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f1 = o[0];
		File f2 = o[1];
		
		if(!f1.isFile()) throw new Exception("Invalid file: "+f1);
		if(!f2.isFile()) throw new Exception("Invalid file: "+f2);
		
		if(f1.equals(f2)) return;
		
		File f1Temp = (File) buildTmpFile.g();
		
		copy(f1,f1Temp);
		copy(f2,f1);
		copy(f1Temp,f2);
	}
	
	
	
	private void copy(File in, File out) throws Exception
	{	
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