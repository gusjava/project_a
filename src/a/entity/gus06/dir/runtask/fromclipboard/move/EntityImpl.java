package a.entity.gus06.dir.runtask.fromclipboard.move;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150607";}


	private Service fromClipboard;
	private Service moveFile;
	
	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus.y.clipboard1.files");
		moveFile = Outside.service(this,"gus06.dir.perform.movefile");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l = (List) fromClipboard.g();
		if(l==null || l.isEmpty()) return;
		
		if(progress!=null) ((V)progress).v("size",""+l.size());
		for(int i=0;i<l.size();i++)
		{
			File file = (File) l.get(i);
			moveFile.p(new File[]{file,dir});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
