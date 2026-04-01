package a.entity.gus06.appli.vindinium.bot.builder;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20170923";}

	public static final String BOT_ENTITYSTART = "gus.appli.vindinium.bot.";
	
	public static final String[] BOTS = new String[]{
		"bot0.random",
		"bot0.test1",
		"bot0.test2",
		"bot0.test3",
		"bot0.test4"
	};
	
	
	private Service newEntity;
	private Map name_id;
	
	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		name_id = new HashMap();
		
		for(int i=0;i<BOTS.length;i++)
		{
			String id = BOTS[i];
			Object bot = createBot(id);
			String name = findBotName(bot);
			
			name_id.put(name,id);
		}
	}

	public Object g() throws Exception
	{return name_id.keySet();}


	public Object t(Object obj) throws Exception
	{
		String id = nameToId((String) obj);
		return createBot(id);
	}
	
	private Object createBot(String id) throws Exception
	{
		String entityName = BOT_ENTITYSTART+id;
		return newEntity.t(entityName);
	}
	
	
	private String findBotName(Object bot) throws Exception
	{return (String) ((R) bot).r("botname");}
	
	private String nameToId(String name)
	{return name_id.containsKey(name)? (String) name_id.get(name):null;}
}
