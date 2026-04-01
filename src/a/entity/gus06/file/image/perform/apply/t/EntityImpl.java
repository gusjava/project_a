package a.entity.gus06.file.image.perform.apply.t;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150929";}


	private Service findAccess;
	
	public EntityImpl() throws Exception
	{
		findAccess = Outside.service(this,"gus06.file.access.image.all");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		T t = (T) o[1];
		
		Object access = findAccess.t(file);
		
		Object img = ((G)access).g();
		img = t.t(img);
		((P)access).p(img);
	}
}
