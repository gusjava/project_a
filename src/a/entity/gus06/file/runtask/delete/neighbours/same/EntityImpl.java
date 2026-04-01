package a.entity.gus06.file.runtask.delete.neighbours.same;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151017";}


	private Service find;
	private Service delete;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.file.neighbours.findfiles.same");
		delete = Outside.service(this,"gus06.dirfile.op.delete");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List listing = (List) find.t(file);
		int number = listing.size();
		
		if(progress!=null) ((V)progress).v("size",""+number);
		
		for(int i=0;i<number;i++)
		{
			File f = (File) listing.get(i);
			delete.p(f);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
