package a.entity.gus06.appli.vindinium.bot.tool.tile1finder.tmine;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	private Service tile1Finder;

	public EntityImpl() throws Exception
	{
		tile1Finder = Outside.service(this,"gus06.appli.vindinium.bot.tool.tile1finder");
	}


	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		List tmines = (List) data.get(DATA_._MINE_TARGET);
		boolean[][] maze = (boolean[][]) data.get(DATA_._MAZE);
		
		return tile1Finder.t(new Object[]{maze,tmines});
	}
}
