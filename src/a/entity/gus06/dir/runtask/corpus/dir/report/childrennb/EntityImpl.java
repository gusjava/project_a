package a.entity.gus06.dir.runtask.corpus.dir.report.childrennb;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180613";}


	private Service listing;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.dirs");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File outputFile = new File(dir.getAbsolutePath()+"_dirsizes.txt");
		PrintStream p = new PrintStream(outputFile);
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(File f:ff)
		{
			File[] hh = f.listFiles();
			int nb = hh!=null ? hh.length : 0;
			
			p.println(nb+"\t"+f.getName());
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		p.close();
	}
}
