package a.entity.gus06.file.runtask.html.clean.toclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190730";}


	private Service toClipboard;
	private Service readFile;
	private Service cleanHtml;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		cleanHtml = Outside.service(this,"gus06.string.transform.format.html.clean");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		String content = (String) cleanHtml.t(readFile.t(file));
		toClipboard.p(content);
		if(progress!=null) ((E)progress).e();
	}
}
