package a.entity.gus06.command.entity.duplicate;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	private Service duplicate;
	private Service checkOwn;
	private Service checkValid;
	
	
	public EntityImpl() throws Exception
	{
		duplicate = Outside.service(this,"gus06.entitydev.duplicate.srccode1");
		checkOwn = Outside.service(this,"gus06.entitydev.entityname.check.own");
		checkValid = Outside.service(this,"gus06.entitydev.entityname.check.valid");
	}


	public void p(Object obj) throws Exception
	{
		String[] n = toArray(obj);
		if(n.length!=2) throw new Exception("Invalid rule: "+obj);
		
		String name1 = (String) checkValid.t(n[0]);
		String name2 = (String) checkOwn.t(n[1]);
		
		duplicate.p(new String[]{name1,name2});
	}
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(" ");
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
