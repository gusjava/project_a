package a.entity.gus06.map.string.formatter1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	private Service split;
	private Service mimic;


	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.words1");
		mimic = Outside.service(this,"gus06.string.case1.mimiccase");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String rule = (String) o[1];
		
		String[] words = (String[]) split.t(rule);
		StringBuffer b = new StringBuffer();
		
		for(String word:words)
		{
			int index = rule.indexOf(word);
			b.append(rule.substring(0,index));
			rule = rule.substring(index+word.length());
			
			String word0 = word.toLowerCase();
			if(map.containsKey(word0))
			{
				Object value = map.get(word0);
				String value1 = formatValue(word,""+value);
				
				b.append(value1);
			}
			else
			{
				b.append(word);
			}
		}
		b.append(rule);
		
		return b.toString();
	}
	
	
	private String formatValue(String model, String value) throws Exception
	{return (String) mimic.t(new String[]{model,value});}
}