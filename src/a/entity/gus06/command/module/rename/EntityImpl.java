package a.entity.gus06.command.module.rename;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140926";}


	private Service rename;
	private Service updateBuild;
	
	
	public EntityImpl() throws Exception
	{
		rename = Outside.service(this,"gus06.moduledev.refactor.rename");
		updateBuild = Outside.service(this,"gus06.appdev.gyem.updatebuild");
	}


	public void p(Object obj) throws Exception
	{
		String[] n = toArray(obj);
		if(n.length!=2) throw new Exception("Invalid rule: "+obj);
		
		rename.p(n);
		updateBuild.e();
	}
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(" ");
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
