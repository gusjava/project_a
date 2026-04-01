package a.entity.gus06.data.perform.restrict;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Number[] range = buildRange(o[1]);
		
		if(data instanceof Integer)
		{
			int number = ((Integer) data).intValue();
			if(range[0]!=null)
			{
				double limit = range[0].doubleValue();
				if(limit>number) number = (int) limit;
			} 
			if(range[1]!=null)
			{
				double limit = range[1].doubleValue();
				if(limit<number) number = (int) limit;
			} 
			return Integer.valueOf(number);
		}
		
		if(data instanceof Double)
		{
			double number = ((Double) data).doubleValue();
			if(range[0]!=null)
			{
				double limit = range[0].doubleValue();
				if(limit>number) number = limit;
			} 
			if(range[1]!=null)
			{
				double limit = range[1].doubleValue();
				if(limit<number) number = limit;
			} 
			return Double.valueOf(number);
		}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private Number[] buildRange(Object obj) throws Exception
	{
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			Number d1 = (Number) o[0];
			Number d2 = (Number) o[1];
			return new Number[]{d1,d2};
		}
		if(obj instanceof List)
		{
			List l = (List) obj;
			Number d1 = (Number) l.get(0);
			Number d2 = (Number) l.get(1);
			return new Number[]{d1,d2};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
