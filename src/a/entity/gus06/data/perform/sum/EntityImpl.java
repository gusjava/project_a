package a.entity.gus06.data.perform.sum;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.Icon;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}


	private Service toArray_bool;
	private Service toArray_int;
	private Service toArray_double;
	private Service toArray_float;
	private Service toArray_long;
	
	private Service toArray_string;
	private Service toArray_list;
	private Service toArray_set;
	private Service toArray_map;
	
	private Service toArray_e;
	private Service toArray_h;
	private Service toArray_p;
	private Service toArray_f;
	private Service toArray_t;
	
	private Service toArray_icon;
	private Service toArray_printstream;
	

	private Service sum_bool;
	private Service sum_int;
	private Service sum_double;
	private Service sum_float;
	private Service sum_long;
	
	private Service sum_double2;
	private Service sum_int2;
	
	private Service sum_string;
	private Service sum_list;
	private Service sum_set;
	private Service sum_map;
	
	private Service sum_e;
	private Service sum_h;
	private Service sum_p;
	private Service sum_f;
	
	private Service sum_icon;
	private Service sum_printstream;
	
	private Service sum_motley;
	
	
	public EntityImpl() throws Exception
	{
		toArray_bool = Outside.service(this,"gus06.convert.objarraytoboolarray.strict");
		toArray_int = Outside.service(this,"gus06.convert.objarraytointarray.strict");
		toArray_double = Outside.service(this,"gus06.convert.objarraytodoublearray.strict");
		toArray_float = Outside.service(this,"gus06.convert.objarraytofloatarray.strict");
		toArray_long = Outside.service(this,"gus06.convert.objarraytolongarray.strict");
		
		toArray_string = Outside.service(this,"gus06.convert.objarraytostringarray.strict");
		toArray_list = Outside.service(this,"gus06.convert.objarraytolistarray.strict");
		toArray_set = Outside.service(this,"gus06.convert.objarraytosetarray.strict");
		toArray_map = Outside.service(this,"gus06.convert.objarraytomaparray.strict");
		
		toArray_e = Outside.service(this,"gus06.convert.objarraytoearray.strict");
		toArray_h = Outside.service(this,"gus06.convert.objarraytoharray.strict");
		toArray_p = Outside.service(this,"gus06.convert.objarraytoparray.strict");
		toArray_f = Outside.service(this,"gus06.convert.objarraytofarray.strict");
		toArray_t = Outside.service(this,"gus06.convert.objarraytotarray.strict");
		
		toArray_icon = Outside.service(this,"gus06.convert.objarraytoiconarray.strict");
		toArray_printstream = Outside.service(this,"gus06.convert.objarraytoprintstreamarray.strict");
		
		sum_bool = Outside.service(this,"gus06.math.tabboolean.and");
		sum_int = Outside.service(this,"gus06.math.tabint.sum");
		sum_double = Outside.service(this,"gus06.math.tabdouble.sum");
		sum_float = Outside.service(this,"gus06.math.tabfloat.sum");
		sum_long = Outside.service(this,"gus06.math.tablong.sum");
		
		sum_double2 = Outside.service(this,"gus06.math.matrixdouble.op.sumcells");
		sum_int2 = Outside.service(this,"gus06.math.matrixint.op.sumcells");
		
		sum_string = Outside.service(this,"gus06.string.stringarray.tostring");
		sum_list = Outside.service(this,"gus06.list.listarray.tolist");
		sum_set = Outside.service(this,"gus06.set.setarray.toset");
		sum_map = Outside.service(this,"gus06.map.maparray.tomap");
		
		sum_e = Outside.service(this,"gus06.feature.op.sum.e");
		sum_h = Outside.service(this,"gus06.feature.op.sum.h");
		sum_p = Outside.service(this,"gus06.feature.op.sum.p");
		sum_f = Outside.service(this,"gus06.feature.op.filter.and");
		
		sum_icon = Outside.service(this,"gus.y.convert1.iconstoicon");
		sum_printstream = Outside.service(this,"gus06.io.printstream.sum");
		
		sum_motley = Outside.service(this,"gus06.data.perform.sum.motley");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[][]) return sum_double2.t(obj);
		if(obj instanceof int[][]) return sum_int2.t(obj);
		
		if(obj instanceof boolean[]) return sum_bool.t(obj);
		if(obj instanceof int[]) return sum_int.t(obj);
		if(obj instanceof double[]) return sum_double.t(obj);
		if(obj instanceof float[]) return sum_float.t(obj);
		if(obj instanceof long[]) return sum_long.t(obj);
		
		if(obj instanceof String[]) return sum_string.t(obj);
		if(obj instanceof List[]) return sum_list.t(obj);
		if(obj instanceof Set[]) return sum_set.t(obj);
		if(obj instanceof Map[]) return sum_map.t(obj);
		
		if(obj instanceof E[]) return sum_e.t(obj);
		if(obj instanceof H[]) return sum_h.t(obj);
		if(obj instanceof P[]) return sum_p.t(obj);
		if(obj instanceof F[]) return sum_f.t(obj);
		
		if(obj instanceof Icon[]) return sum_icon.t(obj);
		if(obj instanceof PrintStream[]) return sum_printstream.t(obj);
		
		if(obj instanceof Object[]) return arraySum((Object[]) obj);
		if(obj instanceof List) return arraySum(listToArray((List) obj));
		if(obj instanceof Set) return arraySum(setToArray((Set) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object arraySum(Object[] oo) throws Exception
	{
		boolean[] bb = (boolean[]) toArray_bool.t(oo);
		if(bb!=null) return sum_bool.t(bb);
		
		int[] nn = (int[]) toArray_int.t(oo);
		if(nn!=null) return sum_int.t(nn);
		
		double[] dd = (double[]) toArray_double.t(oo);
		if(dd!=null) return sum_double.t(dd);
		
		float[] xx = (float[]) toArray_float.t(oo);
		if(xx!=null) return sum_float.t(xx);
		
		long[] yy = (long[]) toArray_long.t(oo);
		if(yy!=null) return sum_long.t(yy);
		
		
		String[] aa = (String[]) toArray_string.t(oo);
		if(aa!=null) return sum_string.t(aa);
		
		List[] ll = (List[]) toArray_list.t(oo);
		if(ll!=null) return sum_list.t(ll);
		
		Set[] ss = (Set[]) toArray_set.t(oo);
		if(ss!=null) return sum_set.t(ss);
		
		Map[] mm = (Map[]) toArray_map.t(oo);
		if(mm!=null) return sum_map.t(mm);
		
		
		E[] ee = (E[]) toArray_e.t(oo);
		if(ee!=null) return sum_e.t(ee);
		
		H[] hh = (H[]) toArray_h.t(oo);
		if(hh!=null) return sum_h.t(hh);
		
		P[] pp = (P[]) toArray_p.t(oo);
		if(pp!=null) return sum_p.t(pp);
		
		F[] ff = (F[]) toArray_f.t(oo);
		if(ff!=null) return sum_f.t(ff);
		
		
		Icon[] ii = (Icon[]) toArray_icon.t(oo);
		if(ii!=null) return sum_icon.t(ii);
		
		PrintStream[] kk = (PrintStream[]) toArray_printstream.t(oo);
		if(kk!=null) return sum_printstream.t(kk);
		
		return sum_motley.t(oo);
	}
	
	
	private Object[] listToArray(List l)
	{return l.toArray();}
	
	private Object[] setToArray(Set l)
	{return l.toArray();}
}
