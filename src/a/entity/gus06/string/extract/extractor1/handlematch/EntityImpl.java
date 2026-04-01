package a.entity.gus06.string.extract.extractor1.handlematch;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Matcher m = (Matcher) o[0];
		String index = (String) o[1];
		
		if(index.equals("0")) return m.group();
		if(index.equals("a")) return all(m);
		
		int count = m.groupCount();
		if(count==0) return null;
		
		if(index.equals("l")) return m.group(count);
		
		int v = Integer.parseInt(index);
		if(count<v) return null;
		
		return m.group(v);
	}
	
	
	
	private List all(Matcher m)
	{
		List list = new ArrayList();
		
		int n = m.groupCount();
		for(int i=1;i<=n;i++)
		list.add(m.group(i));
		
		return list;
	}
}
