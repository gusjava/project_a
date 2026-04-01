package a.entity.gus06.sys.jwpce1.engine.buildquery.prepare;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250726";}

	private Service buildKana;

	public EntityImpl() throws Exception
	{
		buildKana = Outside.service(this,"gus06.string.transform.japanese.kana.builder");
	}
	
	public Object t(Object obj) throws Exception
	{
		String value = (String) obj;
		if(value.startsWith("'")) return buildKana.t(value.substring(1));
		return value;
	}
}