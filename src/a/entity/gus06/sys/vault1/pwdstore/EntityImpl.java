package a.entity.gus06.sys.vault1.pwdstore;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20210512";}

	private Map store;

	public EntityImpl() throws Exception
	{
		store = new HashMap();
	}
	
	public Object r(String key) throws Exception
	{
		if(!store.containsKey(key)) return null;
		return store.get(key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj==null) store.remove(key);
		else store.put(key,obj);
	}
}