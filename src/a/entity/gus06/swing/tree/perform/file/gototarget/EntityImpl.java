package a.entity.gus06.swing.tree.perform.file.gototarget;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20200114";}


	
	public void p(Object obj) throws Exception
	{f(obj);}
	
		
	public boolean f(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		Map search = (Map) ((R)tree).r("search");
		if(search.isEmpty()) return false;
		
		String goToPath = findTarget(search);
		if(goToPath==null) return false;
		
		File goToFile = new File(goToPath);
		((V) tree).v("select",goToFile);
		return true;
	}
	
	
	
	private String findTarget(Map search) throws Exception
	{
		String found = null;
		Iterator it = search.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String state = (String) search.get(key);
			if(state.endsWith("*"))
			{
				if(found!=null) return null;
				found = key;
			}
		}
		return found;
	}
}
