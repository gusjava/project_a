package a.entity.gus06.command.entity.rename;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	private Service renameSrc;
	private Service removeClass;
	private Service checkOwn;
	
	
	public EntityImpl() throws Exception
	{
		renameSrc = Outside.service(this,"gus06.entitydev.refactor.rename1");
		removeClass = Outside.service(this,"gus06.entitydev.refactor.bin.remove1");
		checkOwn = Outside.service(this,"gus06.entitydev.entityname.check.own");
	}


	public void p(Object obj) throws Exception
	{
		String[] n = toArray(obj);
		if(n.length!=2) throw new Exception("Invalid rule: "+obj);
		
		String name1 = (String) checkOwn.t(n[0]);
		String name2 = (String) checkOwn.t(n[1]);
		
		renameSrc.p(new String[]{name1,name2});
		removeClass.p(name1);
	}
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(" ");
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
