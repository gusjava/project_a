package a.entity.gus06.dir.runtask.rm.empty;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190519";}


	private Service listing;
	private Service remove;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		remove = Outside.service(this,"gus06.file.op.delete");
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
		
		int deletedNb = 0;
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(f.length()==0)
			{
				deletedNb++;
				remove.p(f);
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		showMessage.p("Deleted file nb: "+deletedNb);
	}
}
