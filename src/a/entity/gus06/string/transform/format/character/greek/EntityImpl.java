package a.entity.gus06.string.transform.format.character.greek;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20231104";}
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("A","\u0391"); //alpha
		put("B","\u0392"); //beta
		put("G","\u0393"); //gamma
		put("D","\u0394"); //delta
		put("E","\u0395"); //epsilon
		put("Z","\u0396"); //zeta
		put("\u00ca","\u0397"); //eta
		put("TH","\u0398"); //theta
		put("I","\u0399"); //iota
		put("K","\u039A"); //kappa
		put("L","\u039B"); //lampda
		put("M","\u039C"); //mu
		put("N","\u039D"); //nu
		put("X","\u039E"); //xi
		put("O","\u039F"); //omicron
		put("P","\u03A0"); //pi
		put("R","\u03A1"); //rho
		put("S","\u03A3"); //sigma
		put("T","\u03A4"); //tau
		put("U","\u03A5"); //upsilon
		put("PH","\u03A6"); //phi
		put("C","\u03A7"); //chi
		put("PS","\u03A8"); //psi
		put("W","\u03A9"); //omega
		
	}
	
	
	private void put(String key, String value)
	{map.put(key,value);}
	
	
	public Object g() throws Exception
	{return map;}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			s = s.replace(key, value);
		}
		return s;
	}
}