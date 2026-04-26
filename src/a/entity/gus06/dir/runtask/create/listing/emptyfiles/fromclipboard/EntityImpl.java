package a.entity.gus06.dir.runtask.create.listing.emptyfiles.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190804";}


	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String s = (String) clipboard.g();
		if(s==null || s.trim().equals("")) return;
		
		String[] nn = s.split("\n");
		if(progress!=null) ((V)progress).v("size",""+nn.length);
		
		for(String n:nn)
		{
			File file = new File(dir,n.trim());
			if(!file.exists())
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
