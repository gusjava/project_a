package a.entity.gus06.sys.cmd1.builder.object;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}


	private Service stringToObject;
	private Service entityUnique;


	public EntityImpl() throws Exception
	{
		stringToObject = Outside.service(this,"gus06.convert.stringtoobject.convertor1");
		entityUnique = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s.startsWith("%"))
			s = s.substring(1).replace("%"," ");
		
		if(s.contains("#"))
			return stringToObject.t(s);
			
		return entityUnique.t(s);
	}
}
