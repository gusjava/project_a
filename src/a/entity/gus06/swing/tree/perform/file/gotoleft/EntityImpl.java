package a.entity.gus06.swing.tree.perform.file.gotoleft;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20191228";}


	private Service sort;

	public EntityImpl() throws Exception
	{
		sort = Outside.service(this,"gus06.collection.comparator.tostring_i.sort");
	}

	
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
		
	public boolean f(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		Map search = (Map) ((R)tree).r("search");
		if(search.isEmpty()) return false;
		
		File file = (File) tree.getLastSelectedPathComponent();
		String path = file.getAbsolutePath();
		
		String goToPath = findPrevious(search,path);
		if(goToPath==null) return false;
		
		File goToFile = new File(goToPath);
		((V)tree).v("select",goToFile);
		return true;
	}
	
	
	private String findPrevious(Map search, String path) throws Exception
	{
		List keys = new ArrayList(search.keySet());
		sort.p(keys);
		
		int nb = keys.size();
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(nb-i-1);
			String state = (String) search.get(key);
			
			if(state.endsWith("*"))
			{
				int c = key.toLowerCase().compareTo(path.toLowerCase());
				if(c<0) return key;
				if(c==0 && key.compareTo(path)<0) return key;
			}
		}
		return null;
	}
}
