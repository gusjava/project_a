package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.awt.Rectangle;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151112";}


	private Service motley_e;
	private Service motley_h;
	private Service motley_g;
	private Service motley_set;
	private Service motley_list;
	private Service motley_map;
	private Service motley_file;
	private Service motley_url;
	private Service motley_textcomp;
	private Service motley_string;
	
	private Service motley_intarray;
	private Service motley_longarray;
	private Service motley_doublearray;
	private Service motley_floatarray;
	
	private Service motley_intmatrix;
	private Service motley_longmatrix;
	private Service motley_doublematrix;
	private Service motley_floatmatrix;
	
	private Service motley_rectangle;
	private Service motley_date;
	private Service motley_color;
	private Service motley_font;
	
	
	public EntityImpl() throws Exception
	{
		motley_e = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.e");
		motley_h = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.h");
		motley_g = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.g");
		motley_set = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.set");
		motley_list = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.list");
		motley_map = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.map");
		motley_file = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.file");
		motley_url = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.url");
		motley_textcomp = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.textcomp");
		motley_string = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.string");
		
		motley_intarray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intarray");
		motley_longarray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray");
		motley_doublearray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.doublearray");
		motley_floatarray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatarray");
		
		motley_intmatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intmatrix");
		motley_longmatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longmatrix");
		motley_doublematrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.doublematrix");
		motley_floatmatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatmatrix");
		
		motley_rectangle = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.rectangle");
		motley_date = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.date");
		motley_color = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.color");
		motley_font = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.font");
	}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] oo = (Object[]) o[0];
		T sumT = (T) o[1];
		
		
		if(oo.length==0) return null;
		if(oo.length==1) return oo[0];
		
		if(hasH(oo)) return motley_h.t(oo);
		if(hasG(oo)) return motley_g.t(new Object[]{oo,sumT});
		
		if(st_e(oo)) return motley_e.t(oo);
		
		if(st_string(oo)) return motley_string.t(oo);
		if(st_set(oo)) return motley_set.t(oo);
		if(st_map(oo)) return motley_map.t(oo);
		if(st_list(oo)) return motley_list.t(oo);
		if(st_file(oo)) return motley_file.t(oo);
		if(st_url(oo)) return motley_url.t(oo);
		if(st_textcomp(oo)) return motley_textcomp.t(oo);
		
		if(st_intmatrix(oo)) return motley_intmatrix.t(oo);
		if(st_longmatrix(oo)) return motley_longmatrix.t(oo);
		if(st_doublematrix(oo)) return motley_doublematrix.t(oo);
		if(st_floatmatrix(oo)) return motley_floatmatrix.t(oo);
		
		if(st_intarray(oo)) return motley_intarray.t(oo);
		if(st_longarray(oo)) return motley_longarray.t(oo);
		if(st_doublearray(oo)) return motley_doublearray.t(oo);
		if(st_floatarray(oo)) return motley_floatarray.t(oo);
		
		if(st_rectangle(oo)) return motley_rectangle.t(oo);
		if(st_date(oo)) return motley_date.t(oo);
		if(st_color(oo)) return motley_color.t(oo);
		if(st_font(oo)) return motley_font.t(oo);
		
		return motley_string.t(oo);
	}
	
	
	
	private boolean hasH(Object[] oo)
	{
		for(Object o:oo) if(o instanceof H) return true;
		return false;
	}
	
	private boolean hasG(Object[] oo)
	{
		for(Object o:oo) if(o instanceof G) return true;
		return false;
	}
	
	
	
	private boolean st_e(Object[] oo)
	{return oo[0] instanceof E;}
	
	private boolean st_string(Object[] oo)
	{return oo[0] instanceof String;}
	
	private boolean st_set(Object[] oo)
	{return oo[0] instanceof Set;}
	
	private boolean st_map(Object[] oo)
	{return oo[0] instanceof Map;}
	
	private boolean st_list(Object[] oo)
	{return oo[0] instanceof List;}
	
	private boolean st_file(Object[] oo)
	{return oo[0] instanceof File;}
	
	private boolean st_url(Object[] oo)
	{return oo[0] instanceof URL;}
	
	private boolean st_textcomp(Object[] oo)
	{return oo[0] instanceof JTextComponent;}
	
	
	private boolean st_intarray(Object[] oo)
	{return oo[0] instanceof int[];}
	
	private boolean st_longarray(Object[] oo)
	{return oo[0] instanceof long[];}
	
	private boolean st_doublearray(Object[] oo)
	{return oo[0] instanceof double[];}
	
	private boolean st_floatarray(Object[] oo)
	{return oo[0] instanceof float[];}
	

	private boolean st_intmatrix(Object[] oo)
	{return oo[0] instanceof int[][];}

	private boolean st_longmatrix(Object[] oo)
	{return oo[0] instanceof long[][];}
	
	private boolean st_doublematrix(Object[] oo)
	{return oo[0] instanceof double[][];}
	
	private boolean st_floatmatrix(Object[] oo)
	{return oo[0] instanceof float[][];}


	private boolean st_rectangle(Object[] oo)
	{return oo[0] instanceof Rectangle;}

	private boolean st_date(Object[] oo)
	{return oo[0] instanceof Date;}

	private boolean st_color(Object[] oo)
	{return oo[0] instanceof Color;}

	private boolean st_font(Object[] oo)
	{return oo[0] instanceof Font;}
}
