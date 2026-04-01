package a.entity.gus06.java.compiler.errorholder;

import a.framework.*;
import java.util.*;
import java.io.File;


public class EntityImpl extends S1 implements Entity, G, P, E, T {

	public String creationDate() {return "20140726";}

	private List errors;

	public EntityImpl() throws Exception
	{errors = new ArrayList();}
	

	
	public void e() throws Exception
	{
		if(errors.isEmpty()) return;
		errors.clear();
		changed();
	}
	
	
	public Object g() throws Exception
	{return errors;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null object");
		errors.add(obj);
		changed();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.exists()) return null;
		
		List l = new ArrayList();
		for(Object o:errors)
		{
			Map m = (Map) o;
			File f = (File) m.get("file");
			if(f.equals(file)) l.add(m);
		}
		return l;
	}


	private void changed()
	{send(this,"changed()");}
}