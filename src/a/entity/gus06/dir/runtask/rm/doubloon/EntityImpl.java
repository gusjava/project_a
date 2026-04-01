package a.entity.gus06.dir.runtask.rm.doubloon;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}


	private Service listing;
	private Service remove;
	private Service buildMd5;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		remove = Outside.service(this,"gus06.file.op.delete");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		Set found = new HashSet();
		
		int deletedNb = 0;
		long deletedSize = 0;
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String md5 = (String) buildMd5.t(f);
			
			if(found.contains(md5))
			{
				deletedSize += f.length();
				deletedNb++;
				remove.p(f);
			}
			else found.add(md5);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		showMessage.p("Deleted file nb: "+deletedNb+"\nTotal size: "+deletedSize);
	}
}
