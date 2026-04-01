package a.entity.gus06.appli.gusclient1.tool.manager;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P {

	public String creationDate() {return "20140814";}

	public static final String KEY = "tool";
	public static final String KEY_DEFAULT = "tool.default";


	private Service save;

	private String id;
	
	
	public EntityImpl() throws Exception
	{
		save = Outside.service(this,"gus06.command.prop2.set");
	}
	
	
	public Object g() throws Exception
	{
		if(id==null) init();
		return id;
	}
	
	
	public void p(Object obj) throws Exception
	{
		String newId = (String) obj;
		
		if(newId==null) return;
		if(g().equals(newId)) return;
		
		setId(newId);
		toolChanged();
	}
	
	
	
	private void init() throws Exception
	{setId(initValue());}
	
	
	private String initValue() throws Exception
	{
		String v1 = (String) save.r(KEY);
		if(v1!=null) return v1;
	
		String v2 = (String) save.r(KEY_DEFAULT);
		if(v2!=null) return v2;
	
		throw new Exception("Initial tool id not found");
	}
	
	
	
	
	private void setId(String id) throws Exception
	{
		this.id = id;
		save.v(KEY,id);
	}
	
	
	private void toolChanged()
	{send(this,"toolChanged()");}
}
