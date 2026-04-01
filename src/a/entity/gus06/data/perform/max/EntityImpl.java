package a.entity.gus06.data.perform.max;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.awt.Dimension;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}


	private Service toArray_int;
	private Service toArray_double;
	private Service toArray_float;
	private Service toArray_long;
	private Service toArray_h;
	
	private Service max_int;
	private Service max_double;
	private Service max_float;
	private Service max_long;
	private Service max_h;
	private Service max_list;
	
	private Service find;
	
	
	public EntityImpl() throws Exception
	{
		toArray_int = Outside.service(this,"gus06.convert.objarraytointarray.strict");
		toArray_double = Outside.service(this,"gus06.convert.objarraytodoublearray.strict");
		toArray_float = Outside.service(this,"gus06.convert.objarraytofloatarray.strict");
		toArray_long = Outside.service(this,"gus06.convert.objarraytolongarray.strict");
		toArray_h = Outside.service(this,"gus06.convert.objarraytoharray.strict");
		
		max_int = Outside.service(this,"gus06.math.tabint.max");
		max_double = Outside.service(this,"gus06.math.tabdouble.max");
		max_float = Outside.service(this,"gus06.math.tabfloat.max");
		max_long = Outside.service(this,"gus06.math.tablong.max");
		max_h = Outside.service(this,"gus06.feature.op.function.max");
		max_list = Outside.service(this,"gus06.list.max");
		
		find = Outside.service(this,"gus06.find.intarray");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Number) return new HMax(toDouble((Number) obj));
		
		if(obj instanceof int[]) return max_int.t(obj);
		if(obj instanceof double[]) return max_double.t(obj);
		if(obj instanceof float[]) return max_float.t(obj);
		if(obj instanceof long[]) return max_long.t(obj);
		if(obj instanceof H[]) return max_h.t(obj);
		
		if(obj instanceof Object[]) return arrayMax((Object[]) obj);
		if(obj instanceof List) return max_list.t(obj);
		if(obj instanceof Set) return max_list.t(new ArrayList((Set) obj));
		if(obj instanceof Map) return max_list.t(new ArrayList(((Map) obj).keySet()));
		
		if(obj instanceof Dimension) return max_int.t(find.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object arrayMax(Object[] oo) throws Exception
	{
		int[] nn = (int[]) toArray_int.t(oo);
		if(nn!=null) return max_int.t(nn);
		
		double[] dd = (double[]) toArray_double.t(oo);
		if(dd!=null) return max_double.t(dd);
		
		float[] xx = (float[]) toArray_float.t(oo);
		if(xx!=null) return max_float.t(xx);
		
		long[] yy = (long[]) toArray_long.t(oo);
		if(yy!=null) return max_long.t(yy);
		
		H[] hh = (H[]) toArray_h.t(oo);
		if(hh!=null) return max_h.t(hh);
		
		throw new Exception("Invalid list content for max operation");
	}
	
	
	private double toDouble(Number n)
	{return n.doubleValue();}
	
	private class HMax implements H
	{
		private double l;
		public HMax(double l) {this.l = l;}
		
		public double h(double v) throws Exception
		{return Math.max(v,l);}
	}
}
