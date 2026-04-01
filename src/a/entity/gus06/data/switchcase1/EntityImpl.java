package a.entity.gus06.data.switchcase1;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170321";}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((List) obj);
	}
	
	
	private class Holder implements T
	{
		private List list;
		
		public Holder(List list)
		{this.list = list;}
		
		public Object t(Object obj) throws Exception
		{
			for(int i=0;i<list.size();i++)
			{
				Object elem = list.get(i);
				Object[] result = computeResult(elem,obj);
				
				if(((Boolean)result[0]).booleanValue())
					return result[1];
			}
			return null;
		}
	}
	
	
	
	private Object[] computeResult(Object elem, Object value) throws Exception
	{
		if(elem instanceof Map)
		{
			Map map = (Map) elem;
			
			if(!map.containsKey(value)) return new Object[]{Boolean.FALSE,null};
			
			Object r = map.get(value);
			return new Object[]{Boolean.TRUE,r};
		}
		if(elem instanceof List)
		{
			List list = (List) elem;
			int size = list.size();
			if(size!=1 && size!=2) throw new Exception("Invalid list size: "+size);
			
			F filter = (F) list.get(0);
			if(!filter.f(value)) return new Object[]{Boolean.FALSE,null};
			
			if(size==1) return new Object[]{Boolean.TRUE,value};
			
			Object r = list.get(1);
			if(r instanceof T) r = ((T)r).t(value);
			
			return new Object[]{Boolean.TRUE,r};
		}
		throw new Exception("Invalid data type: "+elem.getClass().getName());
	}
}
