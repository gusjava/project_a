package a.entity.gus06.appli.quartogame.engine;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, E, R, G, F {

	public String creationDate() {return "20191115";}
	
	public static final String PLAYER1 = "P1";
	public static final String PLAYER2 = "P2";
	
	public static final String STATE_SELECT = "select";
	public static final String STATE_PUT = "put";
	public static final String STATE_OVER = "over";


	private Service newData;
	
	private int[] data;
	private boolean isP1;
	
	
	public EntityImpl() throws Exception
	{
		newData = Outside.service(this,"gus06.appli.quartogame.data.newdata");
		data = (int[]) newData.g();
	}
	
	
	
	public void e() throws Exception
	{
		data = (int[]) newData.g();
		isP1 =  true;
	}
	
	
	
	
	public Object g() throws Exception
	{
		Map map = new HashMap();
		map.put("data",data);
		map.put("player",player());
		map.put("state",state());
		
		return map;
	}
	
	
	
	
	public boolean f(Object obj) throws Exception
	{
		data = (int[]) obj;
		if(data[16]>0) isP1 = !isP1;
		return true;
	}
	
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("data")) return data;
		if(key.equals("player")) return player();
		if(key.equals("state")) return state();
		
		if(key.equals("keys")) return new String[]{"data","player","state"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private String player()
	{return isP1?PLAYER1:PLAYER2;}
	
	
	private String state()
	{
		//TODO check game over ...
		return data[16]==0 ? STATE_SELECT : STATE_PUT;
	}
}
