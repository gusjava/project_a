package a.entity.gus06.sys.script1.context.evaluate.boolean1;

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
		return toBoolean(eval);
	}
	
	private Boolean toBoolean(Object r)
	{
		if(r==null) return null;
		if(r instanceof Boolean) return (Boolean) r;
		return Boolean.valueOf(r.toString());
	}
}
