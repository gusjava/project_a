package a.entity.gus06.file.lnk.check.target;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180309";}


	private Service extractPath;

	public EntityImpl() throws Exception
	{
		extractPath = Outside.service(this,"gus06.file.lnk.extract.path");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File lnk = o[0];
		File target = o[1];
		
		File target1 = (File) extractPath.t(lnk);
		return target.equals(target1);
	}
}
