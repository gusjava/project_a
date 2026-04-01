package a.entity.gus06.app.persister1.data.set;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20160428";}


	private Service persister;


	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	public Object r(String key) throws Exception
	{return stringToSet((String) persister.r(key));}
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,setToString((Set) obj));}
	
	
	
	
	
	private Set stringToSet(String s)
	{
		Set l = new HashSet();
		if(s==null) return l;
		if(s.equals("")) return l;
		
		String[] n = s.trim().split("\n");
		for(int i=0;i<n.length;i++)
		l.add(n[i]);
		
		return l;
	}
	
	
	private String setToString(Set set)
	{
		List list = new ArrayList(set);
		Collections.sort(list);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		b.append(list.get(i)+"\n");
		
		return b.toString();
	}
}
