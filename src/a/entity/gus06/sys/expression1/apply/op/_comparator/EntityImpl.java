package a.entity.gus06.sys.expression1.apply.op._comparator;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151202";}


	private Service mapCompleteK;
	private Service mapCompleteKV;
	private Service mapIncompleteK;
	private Service mapIncompleteKV;
	
	private Service setComplete;
	private Service setIncomplete;
	
	
	public EntityImpl() throws Exception
	{
		mapCompleteK = Outside.service(this,"gus06.data.compare.map.completekey");
		mapCompleteKV = Outside.service(this,"gus06.data.compare.map.completekeyvalue");
		mapIncompleteK = Outside.service(this,"gus06.data.compare.map.incompletekey");
		mapIncompleteKV = Outside.service(this,"gus06.data.compare.map.incompletekeyvalue");
		
		setComplete = Outside.service(this,"gus06.data.compare.set.complete");
		setIncomplete = Outside.service(this,"gus06.data.compare.set.incomplete");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof Set[]) return new R_set(value);
		if(value instanceof Map[]) return new R_map(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class R_set implements R
	{
		private Object o;
		public R_set(Object o) {this.o = o;}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("e")) return setComplete.t(o);
			if(key.equals("ne")) return setIncomplete.t(o);
			
			throw new Exception("Unknown key: "+key);
		}
	}
	
	
	private class R_map implements R
	{
		private Object o;
		public R_map(Object o) {this.o = o;}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("k")) return mapCompleteK.t(o);
			if(key.equals("kv")) return mapCompleteKV.t(o);
			if(key.equals("nk")) return mapIncompleteK.t(o);
			if(key.equals("nkv")) return mapIncompleteKV.t(o);
			
			throw new Exception("Unknown key: "+key);
		}
	}
}
