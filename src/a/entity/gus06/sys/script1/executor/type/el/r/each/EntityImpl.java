package a.entity.gus06.sys.script1.executor.type.el.r.each;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151111";}


	private Service wrapping1;
	private Service toArray;
	private Service toArray2;
	
	private Service each_null;
	private Service each_map;
	private Service each_set;
	private Service each_list;
	private Service each_array;
	private Service each_array2;
	private Service each_iterator;
	private Service each_string;
	private Service each_image;
	private Service each_g;


	public EntityImpl() throws Exception
	{
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		toArray = Outside.service(this,"gus06.find.objectarray");
		toArray2 = Outside.service(this,"gus06.find.objectarray2");
		
		each_null = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.null1");
		each_map = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.map");
		each_set = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.set");
		each_list = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.list");
		each_array = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.array");
		each_array2 = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.array2");
		each_iterator = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.iterator");
		each_string = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.string");
		each_image = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.image");
		each_g = Outside.service(this,"gus06.sys.script1.executor.type.el.r.each.g");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Executor((Map) obj);}
	
	
	
	private class Executor implements P
	{
		private Map tag;
		public Executor(Map tag) {this.tag = tag;}
		
		public void p(Object obj) throws Exception
		{
			Map context = (Map) obj;
			wrapping1.p(new Object[]{context,tag,new Wrap()});
		}
	}
	
	
	private class Wrap implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
			Map context = (Map) o[0];
			Map tag = (Map) o[1];
			Map pool1 = (Map) o[2];
			Object main = formatMain(o[3]);
			Map data = (Map) o[4];
			
			findService(main).p(new Object[]{context,tag,pool1,main,data});
		}
	}
	
	
	private Service findService(Object src) throws Exception
	{
		if(src==null) return each_null;
		
		if(src instanceof Map) return each_map;
		if(src instanceof Set) return each_set;
		if(src instanceof List) return each_list;
		if(src instanceof Object[][]) return each_array2;
		if(src instanceof Object[]) return each_array;
		if(src instanceof Iterator) return each_iterator;
		if(src instanceof String) return each_string;
		if(src instanceof Image) return each_image;
		if(src instanceof G) return each_g;
		
		throw new Exception("Invalid data type: "+src.getClass().getName());
	}
	
	
	
	private Object formatMain(Object main) throws Exception
	{
		if(main instanceof double[][]) return toArray2.t(main);
		if(main instanceof int[][]) return toArray2.t(main);
		if(main instanceof boolean[][]) return toArray2.t(main);
		if(main instanceof long[][]) return toArray2.t(main);
		if(main instanceof float[][]) return toArray2.t(main);
		if(main instanceof byte[][]) return toArray2.t(main);
		if(main instanceof short[][]) return toArray2.t(main);
		
		if(main instanceof double[]) return toArray.t(main);
		if(main instanceof int[]) return toArray.t(main);
		if(main instanceof boolean[]) return toArray.t(main);
		if(main instanceof long[]) return toArray.t(main);
		if(main instanceof float[]) return toArray.t(main);
		if(main instanceof byte[]) return toArray.t(main);
		if(main instanceof short[]) return toArray.t(main);
		
		if(main instanceof String) return main;
		if(main instanceof char[]) return new String((char[]) main);
		if(main instanceof CharSequence) return ((CharSequence) main).toString();
		
		return main;
	}
}
