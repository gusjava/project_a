package a.entity.gus06.app.persister1.data.list;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20160420";}


	private Service persister;


	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	
	public Object r(String key) throws Exception
	{
		Object data = persister.r(key);
		List l = new ArrayList();
		
		if(data==null) return l;
		if(!(data instanceof String)) return l;
		
		String s = ((String) data).trim();
		if(s.equals("")) return l;
		
		String[] n = s.split("\n");
		for(int i=0;i<n.length;i++)
		l.add(n[i]);
		
		return l;
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,listToString((List) obj));}
	
	
	private String listToString(List l)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<l.size();i++)
		b.append(l.get(i)+"\n");
		
		return b.toString();
	}
}
