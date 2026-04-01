package a.entity.gus06.file.runtask.video.convert.tomp3;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Properties;
import java.util.ArrayList;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260129";}

	private Service convert;

	public EntityImpl() throws Exception
	{
		convert = Outside.service(this,"gus06.file.video.perform.convert.tomp3");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File file1 = new File(file.getAbsolutePath()+".mp3");
		
		if(progress!=null) ((V)progress).v("size","1");
		convert.p(new File[]{file,file1});
		if(progress!=null) ((E)progress).e();
	}
}