package a.entity.gus06.file.runtask.jar.entries.toclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190305";}


	private Service findEntries;
	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		findEntries = Outside.service(this,"gus06.file.jar.findentries.asstring");
		toClipboard = Outside.service(this,"gus06.clipboard.access");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		String entries = (String) findEntries.t(file);
		toClipboard.p(entries);
		
		if(progress!=null) ((E)progress).e();
	}
}
