package a.entity.gus06.file.lnk.remove.insidedir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20180309";}


	private Service listing;
	private Service checkTarget;
	private Service performDelete;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.lnk");
		checkTarget = Outside.service(this,"gus06.file.lnk.check.target");
		performDelete = Outside.service(this,"gus06.file.op.delete");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = o[0];
		File target = o[1];
		
		File[] lnks = (File[]) listing.t(dir);
		boolean removed = false;
		
		for(File lnk : lnks)
		if(checkTarget.f(new File[]{lnk,target}))
		{
			performDelete.p(lnk);
			removed = true;
		}
		return removed;
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
}
