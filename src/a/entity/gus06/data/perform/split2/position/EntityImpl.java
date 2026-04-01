package a.entity.gus06.data.perform.split2.position;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180513";}


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
		return split((String) input,cut,pos);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String[] split(String s, String cut, Object pos) throws Exception
	{
		String[] n = s.split(Pattern.quote(cut),-1);
		
		int v = findPos(n,pos);
		if(v==-1) return null;
		
		String part1 = firstPart(n,cut,v);
		String part2 = lastPart(n,cut,v);
		
		return new String[]{part1,part2};
	}
	
	
	
	private String firstPart(String[] n, String cut, int v)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<=v;i++)
		{
			b.append(n[i]);
			if(i<v) b.append(cut);
		}
		return b.toString();
	}
	
	
	private String lastPart(String[] n, String cut, int v)
	{
		int len = n.length;
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
