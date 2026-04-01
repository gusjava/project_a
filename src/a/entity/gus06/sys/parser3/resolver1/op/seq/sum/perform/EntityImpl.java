package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.perform;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.Action;
import javax.swing.JComponent;
import java.io.PrintStream;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180223";}


	
	private Service toArray_bool;
	private Service toArray_int;
	private Service toArray_double;
	private Service toArray_float;
	private Service toArray_long;
	
	private Service toArray_long2;
	private Service toArray_double2;
	private Service toArray_bigint2;
	
	private Service toArray_string;
	private Service toArray_list;
	private Service toArray_set;
	private Service toArray_map;
	
	private Service toArray_e;
	private Service toArray_h;
	private Service toArray_p;
	private Service toArray_f;
	private Service toArray_t;
	private Service toArray_g;
	
	private Service toArray_icon;
	private Service toArray_jcomponent;
	private Service toArray_printstream;
	private Service toArray_action;
	private Service toArray_image;
	
	
	private Service sum_bool;
	private Service sum_int;
	private Service sum_double;
	private Service sum_float;
	private Service sum_long;
	private Service sum_bigint;
	
	private Service sum_string;
	private Service sum_list;
	private Service sum_set;
	private Service sum_map;
	
	private Service sum_e;
	private Service sum_h;
	private Service sum_p;
	private Service sum_f;
	private Service sum_t;
	private Service sum_g;
	
	private Service sum_icon;
	private Service sum_jcomponent;
	private Service sum_printstream;
	private Service sum_action;
	
	private Service isType_bytearray;
	
	private Service isType_intarray;
	private Service isType_longarray;
	private Service isType_doublearray;
	
	private Service isType_intmatrix;
	private Service isType_longmatrix;
	private Service isType_doublematrix;
	
	private Service sum_bytearray;
	
	private Service sum_intarray;
	private Service sum_longarray;
	private Service sum_doublearray;
	
	private Service sum_intmatrix;
	private Service sum_longmatrix;
	private Service sum_doublematrix;
	
	private Service sum_motley;
	
	


	public EntityImpl() throws Exception
	{
		toArray_bool = Outside.service(this,"gus06.convert.objarraytoboolarray.strict");
		toArray_int = Outside.service(this,"gus06.convert.objarraytointarray.strict");
		toArray_double = Outside.service(this,"gus06.convert.objarraytodoublearray.strict");
		toArray_float = Outside.service(this,"gus06.convert.objarraytofloatarray.strict");
		toArray_long = Outside.service(this,"gus06.convert.objarraytolongarray.longint");
		
		toArray_long2 = Outside.service(this,"gus06.convert.objarraytolongarray.longint");
		toArray_double2 = Outside.service(this,"gus06.convert.objarraytodoublearray.number");
		toArray_bigint2 = Outside.service(this,"gus06.convert.objarraytobigintegerarray.intlong");
		
		toArray_string = Outside.service(this,"gus06.convert.objarraytostringarray.strict");
		toArray_list = Outside.service(this,"gus06.convert.objarraytolistarray.strict");
		toArray_set = Outside.service(this,"gus06.convert.objarraytosetarray.strict");
		toArray_map = Outside.service(this,"gus06.convert.objarraytomaparray.strict");
		
		toArray_e = Outside.service(this,"gus06.convert.objarraytoearray.strict");
		toArray_h = Outside.service(this,"gus06.convert.objarraytoharray.strict");
		toArray_p = Outside.service(this,"gus06.convert.objarraytoparray.strict");
		toArray_f = Outside.service(this,"gus06.convert.objarraytofarray.strict");
		toArray_t = Outside.service(this,"gus06.convert.objarraytotarray.strict");
		toArray_g = Outside.service(this,"gus06.convert.objarraytogarray.strict");
		
		toArray_icon = Outside.service(this,"gus06.convert.objarraytoiconarray.strict");
		toArray_jcomponent = Outside.service(this,"gus06.convert.objarraytojcomponentarray.strict");
		toArray_printstream = Outside.service(this,"gus06.convert.objarraytoprintstreamarray.strict");
		toArray_action = Outside.service(this,"gus06.convert.objarraytoactionarray.strict");
		toArray_image = Outside.service(this,"gus06.convert.objarraytoimagearray.strict");
		
		
		sum_bool = Outside.service(this,"gus06.math.tabboolean.and");
		sum_int = Outside.service(this,"gus06.math.tabint.sum");
		sum_double = Outside.service(this,"gus06.math.tabdouble.sum.mathcontext8");
		sum_float = Outside.service(this,"gus06.math.tabfloat.sum");
		sum_long = Outside.service(this,"gus06.math.tablong.sum");
		sum_bigint = Outside.service(this,"gus06.math.tabbigint.sum");
		
		sum_string = Outside.service(this,"gus06.string.stringarray.tostring");
		sum_list = Outside.service(this,"gus06.sys.opposite1.list.sum");
		sum_set = Outside.service(this,"gus06.sys.opposite1.set.sum");
		sum_map = Outside.service(this,"gus06.map.maparray.tomap");
		
		sum_e = Outside.service(this,"gus06.feature.op.sum.e");
		sum_h = Outside.service(this,"gus06.feature.op.sum.h");
		sum_p = Outside.service(this,"gus06.feature.op.sum.p");
		sum_f = Outside.service(this,"gus06.feature.op.filter.and");
		sum_t = Outside.service(this,"gus06.feature.op.sum.t");
		sum_g = Outside.service(this,"gus06.feature.op.sum.g");
		
		sum_icon = Outside.service(this,"gus06.convert.iconstoicon");
		sum_jcomponent = Outside.service(this,"gus06.swing.comp.perform.sum");
		sum_printstream = Outside.service(this,"gus06.io.printstream.sum");
		sum_action = Outside.service(this,"gus06.swing.action.sum");
		
		isType_bytearray = Outside.service(this,"gus06.data.array.istype.bytearray");
		sum_bytearray = Outside.service(this,"gus06.data.array.sum.bytearray");
		
		isType_intarray = Outside.service(this,"gus06.data.array.istype.intarray");
		isType_longarray = Outside.service(this,"gus06.data.array.istype.longarray");
		isType_doublearray = Outside.service(this,"gus06.data.array.istype.intdoublearray");
		
		isType_intmatrix = Outside.service(this,"gus06.data.array.istype.intmatrix");
		isType_longmatrix = Outside.service(this,"gus06.data.array.istype.longmatrix");
		isType_doublematrix = Outside.service(this,"gus06.data.array.istype.intdoublematrix");
		
		sum_intarray = Outside.service(this,"gus06.math.vectors.sum.intarray");
		sum_longarray = Outside.service(this,"gus06.math.vectors.sum.longarray");
		sum_doublearray = Outside.service(this,"gus06.math.vectors.sum.doublearray");
		
		sum_intmatrix = Outside.service(this,"gus06.math.matrixint.op.sum");
		sum_longmatrix = Outside.service(this,"gus06.math.matrixlong.op.sum");
		sum_doublematrix = Outside.service(this,"gus06.math.matrixdouble.op.sum");
		
		sum_motley = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		return perform((Object[]) obj);
	}
	
	
	
	private Object perform(Object[] oo) throws Exception
	{
		boolean[] bb = (boolean[]) toArray_bool.t(oo);
		if(bb!=null) return sum_bool.t(bb);
		
		int[] nn = (int[]) toArray_int.t(oo);
		if(nn!=null) return sum_int.t(nn);
		
		long[] yy = (long[]) toArray_long.t(oo);
		if(yy!=null) return sum_long.t(yy);
		
		BigInteger[] bi2 = (BigInteger[]) toArray_bigint2.t(oo);
		if(bi2!=null) return sum_bigint.t(bi2);
		
		double[] dd = (double[]) toArray_double.t(oo);
		if(dd!=null) return sum_double.t(dd);
		
		float[] xx = (float[]) toArray_float.t(oo);
		if(xx!=null) return sum_float.t(xx);
		
		
		long[] yy2 = (long[]) toArray_long2.t(oo);
		if(yy2!=null) return sum_long.t(yy2);
		
		double[] dd2 = (double[]) toArray_double2.t(oo);
		if(dd2!=null) return sum_double.t(dd2);
		
		
		
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
		
		T[] tt = (T[]) toArray_t.t(oo);
		if(tt!=null) return sum_t.t(new Object[]{tt,this});
		
		G[] gg = (G[]) toArray_g.t(oo);
		if(gg!=null) return sum_g.t(new Object[]{gg,this});
		
		
		
		Icon[] ii = (Icon[]) toArray_icon.t(oo);
		if(ii!=null) return sum_icon.t(ii);
		
		JComponent[] cc = (JComponent[]) toArray_jcomponent.t(oo);
		if(cc!=null) return sum_jcomponent.t(cc);
		
		PrintStream[] kk = (PrintStream[]) toArray_printstream.t(oo);
		if(kk!=null) return sum_printstream.t(kk);
		
		Action[] ww = (Action[]) toArray_action.t(oo);
		if(ww!=null) return sum_action.t(ww);
		
		
		if(isType_bytearray.f(oo)) return sum_bytearray.t(oo);
		
		if(isType_intarray.f(oo)) return sum_intarray.t(oo);
		if(isType_longarray.f(oo)) return sum_longarray.t(oo);
		if(isType_doublearray.f(oo)) return sum_doublearray.t(oo);
		
		if(isType_intmatrix.f(oo)) return sum_intmatrix.t(oo);
		if(isType_longmatrix.f(oo)) return sum_longmatrix.t(oo);
		if(isType_doublematrix.f(oo)) return sum_doublematrix.t(oo);
		
		
		return sum_motley.t(new Object[]{oo,this});
	}
}