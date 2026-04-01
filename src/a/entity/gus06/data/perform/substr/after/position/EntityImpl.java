package a.entity.gus06.data.perform.substr.after.position;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170204";}


	private Service ruleToIndex;


	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String cut = (String) o[1];
		Object pos = o[2];
		
		if(input instanceof String)
		return substr((String) input,cut,pos);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String substr(String s, String cut, Object pos) throws Exception
	{
		String[] n = s.split(Pattern.quote(cut),-1);
		int len = n.length;
		
		int v = findPos(n,pos);
		if(v==-1) return null;
		
		StringBuffer b = new StringBuffer();
		for(int i=v+1;i<len;i++)
		{
			b.append(n[i]);
			if(i<len-1) b.append(cut);
		}
		return b.toString();
	}
	
	
	private int findPos(String[] n, Object pos) throws Exception
	{
		Integer p = (Integer) ruleToIndex.t(new Object[]{n,pos});
		return p!=null ? p.intValue() : -1;
	}
}
