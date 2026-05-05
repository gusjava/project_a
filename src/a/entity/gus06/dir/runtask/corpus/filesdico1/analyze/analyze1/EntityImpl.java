package a.entity.gus06.dir.runtask.corpus.filesdico1.analyze.analyze1;

import a.framework.*;
import java.io.*;
import java.util.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150728";}


	private Service listing;
	private Service analyzer;
	private Service getName0;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.files");
		analyzer = Outside.service(this,"gus06.sys.filesdico1.analyze.filetoprop");
		getName0 = Outside.service(this,"gus.x.file.getname0");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_analyze");
		dir1.mkdirs();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			handleFile(f,dir1);
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
	}
	
	
	private void handleFile(File f, File dir1) throws Exception
	{
		String name0 = (String) getName0.t(f);
		File f1 = new File(dir1,name0+".properties");
		if(f1.exists()) return;
		
		Properties p = (Properties) analyzer.t(f);
		writeProp.p(new Object[]{f1,p});
	}
}
