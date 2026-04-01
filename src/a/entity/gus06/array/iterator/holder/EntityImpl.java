package a.entity.gus06.array.iterator.holder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161127";}

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((Object[]) obj);}
	
	
	
	private class Holder implements T, E
	{
		private Object[] array;
		private int index;
		
		public Holder(Object[] array)
		{
			this.array = array;
			index = 0;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Integer) return get((Integer) obj);
			
			if(obj.equals("list")) return array;
			if(obj.equals("size")) return Integer.valueOf(size());
			if(obj.equals("index")) return Integer.valueOf(index);
			
			if(obj.equals("current")) return current();
			if(obj.equals("first")) return first();
			if(obj.equals("previous")) return previous();
			if(obj.equals("next")) return next();
			if(obj.equals("last")) return last();
			
			throw new Exception("Unknown command: "+obj);
		}
		
		public void e() throws Exception
		{
			index = 0;
		}
		
		private int size()
		{
			return array.length;
		}
		
		private Object current()
		{
			if(index<0 || index>=size()) return null;
			return array[index];
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
