package a.entity.gus06.dir.runtask.corpus.file.extracttext;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260112";}
	


	private Service listing;
	private Service readText;
	private Service getName0;
	private Service writeText;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		readText = Outside.service(this,"gus06.file.read.string.generic");
		getName0 = Outside.service(this,"gus.x.file.getname0");
		writeText = Outside.service(this,"gus.x.file.string.write");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_text");
		dir1.mkdirs();
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String name0 = (String) getName0.t(f);
			File f1 = new File(dir1, name0+".txt");
			
			if(!f1.exists())
			{
				String text = (String) readText.t(f);
				writeText.p(new Object[]{f1,text});
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
