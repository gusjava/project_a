package a.entity.gus06.list.iterator.holder;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161127";}

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((List) obj);}
	
	
	
	private class Holder implements T, E
	{
		private List list;
		private int index;
		
		public Holder(List list)
		{
			this.list = list;
			index = 0;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Integer) return get((Integer) obj);
			
			if(obj.equals("list")) return list;
			if(obj.equals("size")) return Integer.valueOf(size());
			if(obj.equals("index")) return Integer.valueOf(index);
			
			if(obj.equals("current")) return current();
			if(obj.equals("first")) return first();
			if(obj.equals("previous")) return previous();
			if(obj.equals("next")) return next();
			if(obj.equals("last")) return last();
			
			throw new Exception("Unknown command: "+obj);
		}
		
		private Object current()
		{
			if(index<0 || index>=size()) return null;
			return list.get(index);
		}
		
		public void e() throws Exception
		{
			index = 0;
		}
		
		private int size()
		{
			return list.size();
		}
		
		private Object first()
		{
			setIndex(0);
			return current();
		}
		
		private Object previous()
		{
			setIndex(index-1);
			return current();
		}
		
		private Object next()
		{
			setIndex(index+1);
			return current();
		}
		
		private Object last()
		{
			setIndex(size()-1);
			return current();
		}
		
		private Object get(Integer n)
		{
			setIndex(n.intValue());
			return current();
		}
		
		private void setIndex(int v)
		{
			int size = size();
			if(size==0) {index=-1;return;}
			
			while(v<0) v += size;
			while(v>=size) v -= size;
			index = v;
		}
	}
}
