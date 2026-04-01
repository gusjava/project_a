package a.entity.gus06.data.perform.split;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	private Service quote;
	
	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String cut = (String) o[1];
		
		if(input instanceof String)
		return split((String) input,cut);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String[] split(String s, String cut) throws Exception
	{
		return s.split((String) quote.t(cut),-1);
	}
}
