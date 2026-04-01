package a.entity.gus06.file.runtask.edit.as.txt;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191031";}


	private Service edit;

	public EntityImpl() throws Exception
	{
		edit = Outside.service(this,"gus06.file.editor.show.inframe.witheditor.txt");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		edit.p(file);
		if(progress!=null) ((E)progress).e();
	}
}