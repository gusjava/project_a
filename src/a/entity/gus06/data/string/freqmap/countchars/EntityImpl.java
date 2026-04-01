package a.entity.gus06.data.string.freqmap.countchars;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180112";}


	private Service append;
	
	public EntityImpl() throws Exception
	{
		append = Outside.service(this,"gus06.map.freqmap.append");
	}

	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		Map output = new HashMap();
		for(int i=0;i<input.length();i++)
		{
			String elem = String.valueOf(input.charAt(i));
			append.p(new Object[]{output,elem});
		}
		return output;
	}
}
