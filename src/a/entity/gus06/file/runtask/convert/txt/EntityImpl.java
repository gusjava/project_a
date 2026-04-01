package a.entity.gus06.file.runtask.convert.txt;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Properties;
import java.util.ArrayList;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210607";}


	private Service generate;

	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.file.perform.generate.txt");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File file1 = new File(file.getAbsolutePath()+".txt");
		
		if(progress!=null) ((V)progress).v("size","1");
		generate.p(new File[]{file,file1});
		if(progress!=null) ((E)progress).e();
	}
}