package a.entity.gus06.dir.runtask.name.checkmd5;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150612";}


	private Service checkMd5;
	
	public EntityImpl() throws Exception
	{checkMd5 = Outside.service(this,"gus06.file.filter.checkname.md5");}

	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File[] ff = dir.listFiles();
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		File output = new File(dir.getParentFile(),"results.txt");
		PrintStream p = new PrintStream(output);
		
		for(File f:ff)
		{
			boolean ok = checkMd5.f(f);
			if(!ok) p.println(f.getName());
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
}
