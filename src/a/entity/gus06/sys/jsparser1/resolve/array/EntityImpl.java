package a.entity.gus06.sys.jsparser1.resolve.array;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_ARRAY = "array";

	private Service cut;
	private Service removeLF;
	
	public EntityImpl() throws Exception
	{
		cut = Outside.service(this,"gus06.sys.jsparser1.resolve.array.cut");
		removeLF = Outside.service(this,"gus06.sys.jsparser1.tool.list.remove.lf");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		R res = (R) o[1];
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_ARRAY);
		List valueList = new ArrayList();
		tag.put(VALUE, valueList);
		
		List parts = (List) cut.t(input);
		for(int i=0;i<parts.size();i++)
		{
			List part = (List) parts.get(i);
			Object element = resolveExpression(part, res);
			if(element!=null) valueList.add(element);
		}
		return tag;
	}
	
	
	private Object resolveExpression(List part, R res) throws Exception
	{
		try
		{
			T t = (T) res.r("expression");
			return t.t(new Object[]{part, res});
		}
		catch(Exception e)
		{
			String message = "failed to handle array element: "+part;
			throw new Exception(message, e);
		}
	}
}