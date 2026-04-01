package a.entity.gus06.sys.jsparser1.resolve.struct;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	public static final String TYPE_STRUCT = "struct";
	
	public static final String FUNCTION_NAME = "name";

	private Service cut1;
	private Service cut2;
	private Service clean;
	
	public EntityImpl() throws Exception
	{
		cut1 = Outside.service(this,"gus06.sys.jsparser1.resolve.struct.cut1");
		cut2 = Outside.service(this,"gus06.sys.jsparser1.resolve.struct.cut2");
		clean = Outside.service(this,"gus06.sys.jsparser1.tool.list.clean");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) clean.t(o[0]);
		R res = (R) o[1];
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_STRUCT);
		Map valueMap = new HashMap();
		tag.put(VALUE, valueMap);
		
		List entries = (List) cut1.t(input);
		for(int i=0;i<entries.size();i++)
		{
			List entry = (List) entries.get(i);
			handleEntry(entry, valueMap, res, i);
		}
		return tag;
	}
	
	
	
	
	private void handleEntry(List entry, Map valueMap, R res, int index)
	{
		try
		{
			List kk = (List) cut2.t(entry);
				
			if(kk.size()==2)
			{
				List keyPart = (List) kk.get(0);
				List valuePart = (List) kk.get(1);
				
				String key = formatKey(keyPart);
				Object exp = resolveExpression(valuePart, res);
				valueMap.put(key, exp);
			}
			else if(kk.size()==1)
			{
				List part = (List) kk.get(0);
				
				T t = (T) res.r("function");
				Map function = (Map) t.t(new Object[]{part, res});
				
				String key = getFunctionName(function);
				Object value = function;
				valueMap.put(key, value);
			}
			else
			{
				throw new Exception("Entry size not supported: "+kk.size());
			}
		}
		catch(Exception e)
		{
			valueMap.put(index+"###-entry", entry);
			valueMap.put(index+"###-err", e);
			
			Outside.err(this,"handleEntry(List,Map)",e);
		}
	}
	
	
	
	private Object resolveExpression(List part, R res) throws Exception
	{
		T t = (T) res.r("expression");
		return t.t(new Object[]{part, res});
	}
	
	
	private String formatKey(List key) throws Exception
	{
		if(key.size()!=1) throw new Exception("Invalid key size: ["+key.size()+"] for key: "+key);
		
		Map m = (Map) key.get(0);
		return (String) m.get(VALUE);
	}
	
	private String getFunctionName(Map m) throws Exception
	{
		Map value = (Map) m.get(VALUE);
		if(!value.containsKey(FUNCTION_NAME)) return "function";
		return (String) value.get(FUNCTION_NAME);
	}
	
}