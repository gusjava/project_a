package a.entity.gus06.sys.expression1.apply.op._doc_symbols;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170525";}

	public static final String T = "constant";
	
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		// OPERATEURS UNAIRES
		
		map.put("!","negation");
		map.put("-","opposition/soustraction");
		map.put("&","interpretation");
		map.put("�","interpreter");
		map.put("@","unique instantiation");
		map.put("�","multiple instantiation");

		// OPERATEURS BINAIRES
		
		map.put("::","inclusion");
		map.put("!:","exclusion");
		map.put("/","real division");
		map.put("//","euclidean division");
		map.put("%","modulo");
		map.put("==","equality");
		map.put("!=","difference");
		map.put("<=","inferior or equals");
		map.put("<","strictly inferior");
		map.put(">=","superior or equals");
		map.put(">","strictly superior");
		map.put("^","power/intersection/exlusive OR");
		map.put("!^","extrasection");

		// OPERATEURS TERNAIRES
		
		map.put("?:","conditional evaluation");

		// OPERATEURS N-AIRES
		
		map.put("+","addition");
		map.put("*","multiplication");
		map.put("&&","AND");
		map.put("||","inclusive OR");
		map.put("|","composition");
		map.put("#","application");
		map.put(".","connexion");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return map;
	}
}
