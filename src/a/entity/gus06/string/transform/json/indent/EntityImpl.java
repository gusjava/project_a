package a.entity.gus06.string.transform.json.indent;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}


	private Service evaluate;
	private Service display;


	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.jsonparser1.evaluate");
		display = Outside.service(this,"gus06.file.convert.json.generator.indented");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Object data = evaluate.t(s);
		return display.t(data);
	}
}
