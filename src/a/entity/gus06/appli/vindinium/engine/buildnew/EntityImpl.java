package a.entity.gus06.appli.vindinium.engine.buildnew;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	public static final String ENTITY = "gus.appli.vindinium.engine";


	private Service newEntity;
	private Service random;

	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		random = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
	}


	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String engineId = (String) random.g();
		String playUrl = "engine\\"+engineId;
		
		Object engine = newEntity.t(ENTITY);
		((V)engine).v("playUrl",playUrl);
		map.put(playUrl,engine);
		
		return engine;
	}
}
