package a.entity.gus06.data.physics.units;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20231108";}
	
	public static final String[] DIM = new String[]{"T","L","M","N","J","u0398","I"};

	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		
		put("TIME","T",
			new int[]{1,0,0,0,0,0,0},
			"d","Second"
		);
		put("LENGTH","L",
			new int[]{0,1,0,0,0,0,0},
			"m","Meter"
		);
		put("MASS","M",
			new int[]{0,0,1,0,0,0,0},
			"kg","Kilogram"
		);
		put("SUBSTANCE_AMOUNT","N",
			new int[]{0,0,0,1,0,0,0},
			"mol","Mole"
		);
		put("LUMINOUS_INTENSITY","J",
			new int[]{0,0,0,0,1,0,0},
			"cd","Candela"
		);
		put("TEMPERATURE","\u0398",
			new int[]{0,0,0,0,0,1,0},
			"K","Kelvin"
		);
		put("ELECTRIC_CURRENT","I",
			new int[]{0,0,0,0,0,0,1},
			"A","Ampere"
		);
		
		put("FREQUENCY",null,
			new int[]{-1,0,0,0,0,0,0},
			"Hz","Hertz" //Hz = s-1
		);
		put("PRESSURE",null,
			new int[]{-2,-1,1,0,0,0,0},
			"Pa","Pascal" //Pa = kg m-1 s-2
		);
		put("FORCE",null,
			new int[]{-2,1,1,0,0,0,0},
			"N","Newton" //N = kg m s-2
		);
		put("ENERGY",null,
			new int[]{-2,2,1,0,0,0,0},
			"J","Joule" //J = kg m2 s-2
		);
		put("POWER",null,
			new int[]{-3,2,1,0,0,0,0},
			"W","Watt" //W = kg m2 s-3
		);
		put("ELECTRICAL_VOLTAGE",null,
			new int[]{-3,2,1,0,0,0,-1},
			"V","Volt" //V = kg m2 s-3 A-1
		);
		put("ELECTRICAL_RESISTANCE",null,
			new int[]{-3,2,1,0,0,0,-2},
			"\u03a9","Ohm" //OHM = kg m2 s-3 A-2
		);
		put("ELECTRICAL_CHARGE",null,
			new int[]{1,0,0,0,0,0,1},
			"C","Coulomb" //C = A s
		);
		put("SURFACE",null,
			new int[]{0,2,0,0,0,0,0},
			null,null //s = m2
		);
		put("VOLUME",null,
			new int[]{0,3,0,0,0,0,0},
			null,null //v = m3
		);
		put("MASS_DENSITY",null,
			new int[]{0,-3,1,0,0,0,0},
			null,null //md = kg m-3
		);
		put("SPEED",null,
			new int[]{-1,1,0,0,0,0,0},
			null,null //v = m s-1
		);
		put("ACCELERATION",null,
			new int[]{-2,1,0,0,0,0,0},
			null,null //a = m s-2
		);
		put("GRAVITY_CONSTANT",null,
			new int[]{-2,3,-1,0,0,0,0},
			null,null //G = m3 kg-1 s-2
		);
		
		//electric conductance siemens S   A / V   m<sup>-2</sup> x kg<sup>-1</sup> x s<sup>3</sup> x A<sup>2</sup>
		//capacitance farad F   C / V        m<sup>-2</sup> x kg<sup>-1</sup> x s<sup>4</sup> x A<sup>2</sup>
		//magnetic flux weber Wb    V x s    m<sup>2</sup> x kg x s<sup>-2</sup> x A<sup>-1</sup>
		//magnetic flux density tesla T Wb    m<sup>2</sup>  kg x s<sup>-2</sup> x A<sup>-1</sup>
		//magnetic field strength A / m
		//inductance henry H Wb/ A      m<sup>2</sup> x kg x s<sup>-2</sup> x A<sup>-2</sup>
	}
	
	private void put(String name, String dim, int[] def, String unitSymbol, String unitName)
	{
		Map m = new HashMap();
		map.put(name,m);
		
		m.put("NAME",name);
		m.put("DIM",dim);
		m.put("DEF",def);
		m.put("UNIT_SYMBOL",unitSymbol);
		m.put("UNIT_NAME",unitName);
	}
	
	public Object g() throws Exception
	{return map;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dim")) return DIM;
		if(key.equals("keys")) return new String[]{"dim"};
		throw new Exception("Unknown key: "+key);
	}
}