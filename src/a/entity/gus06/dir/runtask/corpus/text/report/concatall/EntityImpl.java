package a.entity.gus06.dir.runtask.corpus.text.report.concatall;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260311";}
	
	private Service listing;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
		readFile = Outside.service(this,"gus06.file.read.string");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File reportFile = new File(dir.getAbsolutePath()+"_all.txt");
		PrintStream p = new PrintStream(reportFile);
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(File f:ff)
		{
			String s = (String) readFile.t(f);
			p.print(s);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
}
