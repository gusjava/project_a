package a.entity.gus06.map.string.stringtomap.parser.semicolon;

import java.util.HashMap;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}

	
	private Service parseSequence;
	
	public EntityImpl() throws Exception
	{parseSequence = Outside.service(this,"gus06.data.transform.string.sequence.parser.semicolon");}

	
	private List parse(String s) throws Exception
	{return (List) parseSequence.t(s);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule==null || rule.equals("")) return new HashMap();
		
		List seq = parse(rule);
		HashMap map = new HashMap();
		for(int i=0;i<seq.size();i++)
		{
			String el = (String) seq.get(i);
			if(!el.contains("=")) throw new Exception("Invalid rule: "+rule);
			String[] nn = el.split("=",2);
			map.put(nn[0],nn[1]);
		}
		return map;
	}
}
