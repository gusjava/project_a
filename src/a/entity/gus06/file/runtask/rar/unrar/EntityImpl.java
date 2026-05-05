package a.entity.gus06.file.runtask.rar.unrar;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150630";}


	private Service perform;
	private Service getName;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.rar.perform.unrar");
		getName = Outside.service(this,"gus.x.file.getname0");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String name = (String) getName.t(file);
		File dir = new File(file.getParentFile(),name);
		dir.mkdirs();
		
		if(progress!=null) ((V)progress).v("size","1");
		perform.p(new File[]{file,dir});
		if(progress!=null) ((E)progress).e();
	}
}