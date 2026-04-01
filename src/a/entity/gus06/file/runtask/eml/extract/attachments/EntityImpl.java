package a.entity.gus06.file.runtask.eml.extract.attachments;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240320";}


	private Service extract;
	private Service getName;

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.file.eml.perform.extract.attachments");
		getName = Outside.service(this,"gus06.file.getname0");
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
		
		extract.p(new Object[]{file,dir,progress,interrupt});
	}
}