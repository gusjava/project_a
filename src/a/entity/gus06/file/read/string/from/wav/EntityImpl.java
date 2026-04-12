package a.entity.gus06.file.read.string.from.wav;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250918";}

	public static final String PROPKEY = "script.convert.wavtostring";
	
	private Map prop;
	private Service buildT;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"props");
		buildT = Outside.service(this,"gus06.sys.script1.build2.t");
	}

	public Object t(Object obj) throws Exception
	{
		if(!prop.containsKey(PROPKEY))
		throw new Exception("Unsupported operation: reading string from wav");
		String script = (String) prop.get(PROPKEY);
		
		T t = (T) buildT.t(new Object[]{script, null});
		return t.t(obj);
	}
}
