package a.entity.gus06.dir.perform.each;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160412";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		P p = (P) o[1];
		
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff) p.p(f);
	}
}
