package a.entity.gus06.data.handle.object.fromrule;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Date;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20160819";}
	
	
	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("browse",Outside.service(this,"gus06.data.perform.browse"));
		put("clipboard",Outside.service(this,"gus06.clipboard.access"));
		put("close",Outside.service(this,"gus06.data.perform.close"));
		put("delete",Outside.service(this,"gus06.data.perform.delete"));
		put("empty",Outside.service(this,"gus06.data.perform.empty"));
		put("exec",Outside.service(this,"gus06.data.perform.exec"));
		put("open",Outside.service(this,"gus06.data.perform.open"));
		put("permute",Outside.service(this,"gus06.data.perform.permute"));
		put("permute_inv",Outside.service(this,"gus06.data.perform.permute.inv"));
		put("reverse",Outside.service(this,"gus06.data.perform.reverse"));
		put("shuffle",Outside.service(this,"gus06.data.perform.shuffle"));
		put("sort",Outside.service(this,"gus06.data.perform.sort"));
		put("sort_inv",Outside.service(this,"gus06.data.perform.sort.inv"));
		put("sortnum",Outside.service(this,"gus06.data.perform.sortnum"));
		put("sortnum_inv",Outside.service(this,"gus06.data.perform.sortnum.inv"));
	}
	
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key)) return map.get(key);
		return null;
	}
	
	
	private void put(String key, P p)
	{map.put(key,new P_Wrap(p));}
	
	
	
	
	private class P_Wrap implements P
	{
		private P p;
		public P_Wrap(P p) {this.p = p;}
		
		public void p(Object obj) throws Exception
		{p.p(obj);}
	}
}
