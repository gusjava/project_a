package a.entity.gus06.file.runtask.string.content.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190730";}


	private Service toClipboard;
	private Service writeFile;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		String content = (String) toClipboard.g();
		writeFile.p(new Object[]{file,content});
		if(progress!=null) ((E)progress).e();
	}
}
