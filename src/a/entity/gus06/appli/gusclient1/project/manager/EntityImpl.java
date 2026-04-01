package a.entity.gus06.appli.gusclient1.project.manager;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20140830";}

	public static final String KEY = "project";
	
	
	private Service save;
	
	private String id;
	
	public EntityImpl() throws Exception
	{
		save = Outside.service(this,"gus06.command.prop2.set");
		
		String id0 = (String) save.r(KEY);
		if(id0!=null) id = id0;
	}
	
	
	public Object g() throws Exception
	{return id;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		String newId = (String) obj;
		if(equals(id,newId)) return;
		
		id = newId;
		save.v(KEY,id);
		
		projectChanged();
	}
	
	
	
	
	
	private void projectChanged()
	{send(this,"projectChanged()");}
	
	
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}
