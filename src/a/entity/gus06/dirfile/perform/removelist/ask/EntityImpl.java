package a.entity.gus06.dirfile.perform.removelist.ask;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20151018";}


	private Service askDirFile;
	private Service rmDirFile;
	private Service input;


	public EntityImpl() throws Exception
	{
		askDirFile = Outside.service(this,"gus06.dirfile.perform.remove.ask");
		rmDirFile = Outside.service(this,"gus06.dirfile.perform.remove");
		input = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		List list = (List) obj;
		
		if(list.isEmpty()) return false;
		if(list.size()==1) return askDirFile.f(list.get(0));
		
		boolean r = input.f("Are you sure to delete this selection ?");
		if(!r) return false;
		
		for(int i=0;i<list.size();i++)
		{
			File f = (File) list.get(i);
			rmDirFile.p(f);
		}
		return true;
	}
}
