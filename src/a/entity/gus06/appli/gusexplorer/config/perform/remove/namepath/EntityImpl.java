package a.entity.gus06.appli.gusexplorer.config.perform.remove.namepath;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250904";}


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String name = (String) o[0];
		File file = toFile(o[1]);
		
		List list = (List) manager.r(name);
		if(!list.contains(file)) return false;
		
		list.remove(file);
		manager.v("persistList", new Object[]{name,list});
		return true;
	}
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}