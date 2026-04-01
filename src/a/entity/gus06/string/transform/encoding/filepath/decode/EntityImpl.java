package a.entity.gus06.string.transform.encoding.filepath.decode;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220524";}

	private Service perform;
	private String rule;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.encoding.forbiddenchar.decode");
		rule = "&~:'"+File.separator;
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(new String[]{(String) obj, rule});
	}
}