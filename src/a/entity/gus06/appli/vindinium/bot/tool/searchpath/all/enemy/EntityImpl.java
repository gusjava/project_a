package a.entity.gus06.appli.vindinium.bot.tool.searchpath.all.enemy;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service all;

	public EntityImpl() throws Exception
	{
		all = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.all");
	}

	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		List enemies = (List) data.get(DATA_._ENEMY);
		
		return all.t(new Object[]{data,enemies});
	}
}
