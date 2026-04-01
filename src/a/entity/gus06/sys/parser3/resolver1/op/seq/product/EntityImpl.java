package a.entity.gus06.sys.parser3.resolver1.op.seq.product;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}


	private Service product_h;
	private Service product_int;
	private Service product_double;
	
	private Service toArray_h;
	private Service toArray_int;
	private Service toArray_double;
	
	private Service product_tabInt;
	private Service product_dabDouble;
	
	private Service product_motley;




	public EntityImpl() throws Exception
	{
		product_h = Outside.service(this,"gus06.feature.op.product.h");
		product_int = Outside.service(this,"gus06.math.tabint.product");
		product_double = Outside.service(this,"gus06.math.tabdouble.product.mathcontext8");
		
		toArray_h = Outside.service(this,"gus06.convert.objarraytoharray.strict");
		toArray_int = Outside.service(this,"gus06.convert.objarraytointarray.strict");
		toArray_double = Outside.service(this,"gus06.convert.objarraytodoublearray.number");
		
		product_tabInt = Outside.service(this,"gus06.math.tabint.pair.product");
		product_dabDouble = Outside.service(this,"gus06.math.tabdouble.pair.product");
		
		product_motley = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.product.motley");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		int nb = cut.size();
		Object[] oo = new Object[nb];
		for(int i=0;i<nb;i++) oo[i] = t.t(cut.get(i));
		
		return perform(oo);
	}
	
	
	
	private Object perform(Object[] oo) throws Exception
	{
		if(oo.length==2)
		{
			if(oo[0] instanceof int[] && oo[1] instanceof int[])
				return product_tabInt.t(oo);
		
			if(oo[0] instanceof double[] && oo[1] instanceof double[])
				return product_dabDouble.t(oo);
		}
		
		int[] nn = (int[]) toArray_int.t(oo);
		if(nn!=null) return product_int.t(nn);
		
		double[] dd = (double[]) toArray_double.t(oo);
		if(dd!=null) return product_double.t(dd);
		
		H[] hh = (H[]) toArray_h.t(oo);
		if(hh!=null) return product_h.t(hh);
		
		return product_motley.t(oo);
	}
}