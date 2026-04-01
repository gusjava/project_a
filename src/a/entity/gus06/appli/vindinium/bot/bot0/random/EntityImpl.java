package a.entity.gus06.appli.vindinium.bot.bot0.random;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20170923";}

	public static final String BOTNAME = "random";
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("botname")) return BOTNAME;
		if(key.equals("keys")) return new String[]{"botname"};
		throw new Exception("Unknown key: "+key);
	}


	public Object t(Object obj) throws Exception
	{return random();}

	
	private String random()
	{
		int n = (int) (Math.random()*4);
		switch(n) {
		case 0:return DIRECTION.NORTH;
		case 1:return DIRECTION.SOUTH;
		case 2:return DIRECTION.WEST;
		case 3:return DIRECTION.EAST;
		default:return DIRECTION.STAY;
		}
	}
}
