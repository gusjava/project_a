package a.entity.gus06.appli.vindinium.bot.mainbot;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T, E {

	public String creationDate() {return "20170923";}
	
	private Service isFinished;
	private Service dataAnalyze;
	private Service botProvider;
	private Service botRandom;
	
	private long max_dt;
	private T bot;
	
	public EntityImpl() throws Exception
	{
		isFinished = Outside.service(this,"gus06.appli.vindinium.data.game.isfinished");
		dataAnalyze = Outside.service(this,"gus06.appli.vindinium.data.analyze");
		botProvider = Outside.service(this,"gus06.appli.vindinium.bot.provider");
		botRandom = Outside.service(this,"gus06.appli.vindinium.bot.bot0.random");
	}
	
	public void e() throws Exception
	{
		bot = (T) botProvider.g();
		
		max_dt = 0;
		dataAnalyze.e();
	}


	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		dataAnalyze.p(data);
		
		if(isFinished.f(data)) return null;
		return performBot(data);
	}
	
	private String performBot(Map data) throws Exception
	{
		T bot = bot();
		
		long t1 = System.nanoTime();
		String direction = (String) bot.t(data);
		long dt = System.nanoTime() - t1;
		if(dt>max_dt) max_dt = dt;
		
		data.put(DATA_BOT_._BOT_DURATION,""+dt);
		data.put(DATA_BOT_._BOT_MAXDURATION,""+max_dt);
		data.put(DATA_BOT_._BOT_DIRECTION,direction);
		data.put(DATA_BOT_._BOT_NAME,name(bot));
		
		while(System.nanoTime() < t1 + 200_000_000)
		{Thread.sleep(20);}
		
		return direction;
	}
	
	private T bot()
	{return bot!=null?bot:botRandom;}
	
	private String name(Object bot) throws Exception
	{return (String) ((R) bot).r("botname");}
}
