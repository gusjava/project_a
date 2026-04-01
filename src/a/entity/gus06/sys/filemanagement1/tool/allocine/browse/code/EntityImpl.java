package a.entity.gus06.sys.filemanagement1.tool.allocine.browse.code;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210306";}


	private Service findMap;
	private Service perform;

	public EntityImpl() throws Exception
	{
		findMap = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.prop.find.map");
		perform = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.browse.map");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) findMap.t(obj);
		perform.p(prop);
	}
}
