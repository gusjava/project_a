package a.entity.gus06.data.physics.constants;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20231109";}

	public static final double GRAVITY_CONST = 6.67430*Math.pow(10,-11);
	public static final long LIGHTSPEED_CONST = 299792458;
	public static final String AVOGADRO_CONST = "602200000000000000000000"; //� revoir...

	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		map.put("GRAVITY_CONST",GRAVITY_CONST);
		map.put("LIGHTSPEED_CONST",LIGHTSPEED_CONST);
		map.put("AVOGADRO_CONST",AVOGADRO_CONST);
	}
	
	public Object g() throws Exception
	{return map;}
	
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key)) return map.get(key);
		throw new Exception("Unknown key: "+key);
	}
}