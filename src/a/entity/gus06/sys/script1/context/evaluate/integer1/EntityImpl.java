package a.entity.gus06.sys.script1.context.evaluate.integer1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150902";}


	private Service evaluate;
	
	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.script1.context.evaluate");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object eval = evaluate.t(obj);
		return toInteger(eval);
	}
	
	private Integer toInteger(Object r)
	{
		if(r==null) return null;
		if(r instanceof Integer) return (Integer) r;
		return Integer.valueOf(r.toString());
	}
}
