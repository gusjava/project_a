package a.entity.gus06.appli.vindinium.data.game.isfinished;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170923";}


	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return true;
		
		Map data = (Map) obj;
		Map game = (Map) data.get(DATA.K_GAME);
		Boolean finished = (Boolean) game.get(DATA.G_FINISHED);
		
		return finished.booleanValue();
	}
}
