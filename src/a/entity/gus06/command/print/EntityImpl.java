package a.entity.gus06.command.print;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140701";}

	
	private Service ruleToObject;
	private Service printObject;
	
	
	public EntityImpl() throws Exception
	{
		ruleToObject = Outside.service(this,"gus06.command.ruletoobj");
		printObject = Outside.service(this,"gus06.print.object");
	}


	public void p(Object obj) throws Exception
	{
		String rule = (String) obj;
		Object result = ruleToObject.t(rule);
		printObject.p(result);
	}	
}