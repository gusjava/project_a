package a.entity.gus06.data.physics.units.prefix;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20231109";}
	
	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("da","deca",1);
		put("h","hecto",2);
		put("k","kilo",3);
		put("M","mega",6);
		put("G","giga",9);
		put("T","tera",12);
		put("P","peta",15);
		put("E","exa",18);
		put("Z","zetta",21);
		put("Y","yotta",24);
		
		put("da","deci",-1);
		put("h","centi",-2);
		put("k","milli",-3);
		put("\u03bc","micro",-6);
		put("G","nano",-9);
		put("p","pico",-12);
		put("f","femto",-15);
		put("a","atto",-18);
		put("z","zepto",-21);
		put("y","yocto",-24);
		
	}
	
	private void put(String prefix, String name, int power)
	{
		Map m = new HashMap();
		map.put(prefix,m);
		
		m.put("PREFIX",prefix);
		m.put("NAME",name);
		m.put("POWER",power);
	}
	
	public Object g() throws Exception
	{return map;}
}