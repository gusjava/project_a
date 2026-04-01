package a.entity.gus06.appli.vindinium.bot.tool.searchpath.nearest.tmine;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service nearest;

	public EntityImpl() throws Exception
	{
		nearest = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest");
	}

	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		List tmines = (List) data.get(DATA_._MINE_TARGET);
		
		return nearest.t(new Object[]{data,tmines});
	}
}
