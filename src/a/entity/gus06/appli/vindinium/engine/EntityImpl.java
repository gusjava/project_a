package a.entity.gus06.appli.vindinium.engine;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20170923";}

	public static final long SLEEP = 300;

	private Service getInitial;
	private Service enemyProvider;
	private Service generateJson;
	private Service enemyPlay;
	private Service handleTurn;
	
	private Map data;
	
	private T[] enemy;
	private int[] id_enemy;
	
	private String playUrl;
	private String viewUrl;
	private int maxTurns;
	private int turn;
	private boolean over;
	
	private int id_me;

	private String[] direction;
	
	
	public EntityImpl() throws Exception
	{
		getInitial = Outside.service(this,"gus06.appli.vindinium.engine.getinitial");
		enemyProvider = Outside.service(this,"gus06.appli.vindinium.engine.enemyprovider");
		generateJson = Outside.service(this,"gus06.file.convert.json.generator");
		enemyPlay = Outside.service(this,"gus06.appli.vindinium.engine.enemyplay");
		handleTurn = Outside.service(this,"gus06.appli.vindinium.engine.handleturn");
		
		data = (Map) getInitial.g();
		direction = new String[4];
		id_me = 1;
		
		enemy = (T[]) enemyProvider.g();
		id_enemy = id_enemy();
		
		for(int i=0;i<enemy.length;i++)
		{
			String name = getEnemyName(i);
			int id = id_enemy[i];
			hero(id-1).put(DATA.H_NAME,name);
		}
		
		maxTurns = i_(game().get(DATA.G_MAXTURNS));
		turn = 0;
		viewUrl = "";
		over = false;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("playUrl")) {playUrl = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	


	public Object t(Object obj) throws Exception
	{
		if(over) return null;
		
		handleParams((Map) obj);
		updateData();
		Thread.sleep(SLEEP);
		
		
		for(int i=0;i<id_enemy.length;i++)
		{
			int id = id_enemy[i];
			T t = enemy[i];
			
			String json = adaptAndGenerateJson(id);
			direction[id-1] = enemyPlay(json,t);
		}
		
		return adaptAndGenerateJson(id_me);
	}
	
	
	private void updateData()
	{
		data_put(DATA.K_PLAYURL,playUrl);
		data_put(DATA.K_VIEWURL,viewUrl);
		
		game_put(DATA.G_MAXTURNS,""+maxTurns);
		game_put(DATA.G_TURN,""+turn);
		game_put(DATA.G_FINISHED, Boolean.valueOf(over));
	}
	
	
	private void handleParams(Map params) throws Exception
	{
		if(params.containsKey("turns"))
			maxTurns = i_(params.get("turns"))*4;
			
		if(params.containsKey("dir"))
			handleTurn((String) params.get("dir"));
	}
	
	
	
	private void handleTurn(String dir) throws Exception
	{
		direction[id_me-1] = dir;
		handleTurn.p(new Object[]{game(),direction});
		
		turn += 4;
		over = turn>=maxTurns;
	}
	
	
	private int[] id_enemy()
	{
		switch(id_me){
		case 1:return new int[]{2,3,4};
		case 2:return new int[]{1,3,4};
		case 3:return new int[]{1,2,4};
		default:return new int[]{1,2,3};
		}
	}
	
	
	private String getEnemyName(int index) throws Exception
	{
		R ret = (R) enemy[index];
		return (String) ret.r("botname");
	}
	
	
	private String adaptAndGenerateJson(int id) throws Exception
	{
		data_put(DATA.K_HERO,hero(id-1));
		return (String) generateJson.t(data);
	}
	
	
	private String enemyPlay(String json, T t) throws Exception
	{return (String) enemyPlay.t(new Object[]{json,t});}
	
	
	private void data_put(String key, Object value)
	{data.put(key,value);}
	
	
	private void game_put(String key, Object value)
	{game().put(key,value);}
	
	
	private Map game()
	{return (Map) data.get(DATA.K_GAME);}
	
	
	private List heroes()
	{return (List) game().get(DATA.G_HEROES);}
	
	
	private Map hero(int index)
	{return (Map) heroes().get(index);}
	
	
	private int i_(Object s)
	{return Integer.parseInt((String) s);}
}