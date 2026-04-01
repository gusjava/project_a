package a.entity.gus06.java.srccode.extract.entity.imports1;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}
	

	private Service srcToImports;
	
	public EntityImpl() throws Exception
	{
		srcToImports = Outside.service(this,"gus06.java.srccode.extract.imports1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Set set = (Set) srcToImports.t(obj);
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String import1 = (String) it.next();
			if(isFramework(import1)) it.remove();
			else if(import1.startsWith("gus"))
			throw new Exception("Invalid import found inside source code: "+import1);
		}
		
		return set;
	}
	
	
	
	private boolean isFramework(String import1)
	{return import1.startsWith("gus06.framework.");}
}
