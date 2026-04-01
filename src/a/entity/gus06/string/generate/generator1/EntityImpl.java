package a.entity.gus06.string.generate.generator1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String rule = (String) o[1];
		String options = (String) o[2];
		
		if(data instanceof String)
			return generateFromString((String) data,options,rule);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private String generateFromString(String data, String options, String rule)
	{
		// il faut am�liorer tout �a !!
		
		return rule.replace("*",data);
	}
}