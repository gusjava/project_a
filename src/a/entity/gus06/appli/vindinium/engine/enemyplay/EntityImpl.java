package a.entity.gus06.appli.vindinium.engine.enemyplay;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service parseJson;
	private Service formatData;
	private Service isFinished;
	
	public EntityImpl() throws Exception
	{
		parseJson = Outside.service(this,"gus.x.json.parse1");
		formatData = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format");
		isFinished = Outside.service(this,"gus06.appli.vindinium.data.game.isfinished");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		String json = (String) t[0];
		T enemy = (T) t[1];
		
		Map data = (Map) parseJson.t(json);
		formatData.p(data);
		
		if(isFinished.f(data)) return null;
		return enemy.t(data);
	}
}
