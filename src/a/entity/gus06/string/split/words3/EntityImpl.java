package a.entity.gus06.string.split.words3;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200306";}
	

	private Service quote;
	private Service delim;
	
	private String regex;
	
	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
		delim = Outside.service(this,"gus06.string.split.words3.delim");
		
		regex = "["+quote.t(delim.g())+"]+";
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.split(regex);
	}
}
