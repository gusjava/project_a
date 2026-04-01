package a.entity.gus06.math.function.build.fromrule;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20160808";}


	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("abs",Outside.service(this,"gus06.math.function.h.abs"));
		put("cube",Outside.service(this,"gus06.math.function.h.cube"));
		put("cbrt",Outside.service(this,"gus06.math.function.h.cuberoot"));
		put("decr",Outside.service(this,"gus06.math.function.h.decrement"));
		put("double",Outside.service(this,"gus06.math.function.h.double1"));
		put("half",Outside.service(this,"gus06.math.function.h.half"));
		put("heaviside",Outside.service(this,"gus06.math.function.h.heaviside"));
		put("id",Outside.service(this,"gus06.math.function.h.identity"));
		put("incr",Outside.service(this,"gus06.math.function.h.increment"));
		put("inv",Outside.service(this,"gus06.math.function.h.inverse"));
		put("one",Outside.service(this,"gus06.math.function.h.one"));
		put("opp",Outside.service(this,"gus06.math.function.h.opp"));
		put("sigmoid",Outside.service(this,"gus06.math.function.h.sigmoid"));
		put("signum",Outside.service(this,"gus06.math.function.h.signum"));
		put("square",Outside.service(this,"gus06.math.function.h.square"));
		put("sqrt",Outside.service(this,"gus06.math.function.h.squareroot"));
		put("triple",Outside.service(this,"gus06.math.function.h.triple"));
		put("zero",Outside.service(this,"gus06.math.function.h.zero"));
		
		put("cos",Outside.service(this,"gus06.math.function.trigo.cosine"));
		put("sin",Outside.service(this,"gus06.math.function.trigo.sine"));
		put("tan",Outside.service(this,"gus06.math.function.trigo.tangent"));
		
		put("cosh",Outside.service(this,"gus06.math.function.hyperbolic.cosine"));
		put("sinh",Outside.service(this,"gus06.math.function.hyperbolic.sine"));
		put("tanh",Outside.service(this,"gus06.math.function.hyperbolic.tangent"));
	}
	
	
	private void put(String key, H h)
	{map.put(key,new H_Wrap(h));}
	
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key)) 	return map.get(key);
		if(key.matches("x[0-9]+")) 	return new H_x(key.substring(1));
		return null;
	}
	
	
	
	private class H_Wrap implements H
	{
		private H h;
		public H_Wrap(H h) {this.h = h;}
		
		public double h(double value) throws Exception
		{return h.h(value);}
	}
	
	
	private class H_x implements H
	{
		private int times;
		public H_x(String s)
		{times = Integer.parseInt(s);}
		
		public double h(double value) throws Exception
		{return times*value;}
	}
}
