package a.entity.gus06.sys.jwpce1.engine.buildquery;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250726";}

	private Service buildEq;
	private Service buildSt;
	private Service buildEn;
	private Service buildCo;

	public EntityImpl() throws Exception
	{
		buildEq = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery.eq");
		buildEn = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery.en");
		buildSt = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery.st");
		buildCo = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery.co");
	}
	
	public Object t(Object obj) throws Exception
	{
		String value = (String) obj;
		if(value.startsWith("=")) return buildEq.t(value.substring(1));
		if(value.startsWith("eq:")) return buildEq.t(value.substring(3));
		if(value.startsWith("st:")) return buildSt.t(value.substring(3));
		if(value.startsWith("en:")) return buildEn.t(value.substring(3));
		if(value.startsWith("co:")) return buildCo.t(value.substring(3));
		if(value.startsWith("&")) return value.substring(1);
		
		return buildCo.t(value);
	}
}