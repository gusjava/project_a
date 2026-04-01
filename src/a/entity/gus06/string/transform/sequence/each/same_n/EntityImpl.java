package a.entity.gus06.string.transform.sequence.each.same_n;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160622";}
	
	public static final String DELIM = ";";
	
	private Service normalize;
	
	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		if(nn.length==0) return "false";
		
		String n0 = format(nn[0]);
		for(String n:nn)
		{
			if(!format(n).equals(n0)) return "false";
		}
		return "true";
	}
	
	private String format(String s) throws Exception
	{return (String) normalize.t(s);}
}
