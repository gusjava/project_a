package a.entity.gus06.list.paginator.holder;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161219";}
	
	public static final int DEFAULT_LENGTH = 10;

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((List) obj);}
	
	
	
	private class Holder implements T, E, V
	{
		private List list;
		
		private int index;
		private int length;
		
		public Holder(List list)
		{
			this.list = list;
			
			index = 0;
			length = DEFAULT_LENGTH;
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("page")) {setPage(toInt(obj));return;}
			if(key.equals("index")) {setIndex(toInt(obj));return;}
			if(key.equals("length")) {setLength(toInt(obj));return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Integer) return get((Integer) obj);
			
			if(obj.equals("list")) return list;
			if(obj.equals("length")) return Integer.valueOf(length);
			if(obj.equals("tail")) return Integer.valueOf(tail());
			if(obj.equals("pagecount")) return Integer.valueOf(pagecount());
			if(obj.equals("pagecount1")) return Integer.valueOf(pagecount1());
			
			if(obj.equals("size")) return Integer.valueOf(size());
			if(obj.equals("index")) return Integer.valueOf(index);
			if(obj.equals("current")) return current();
			
			if(obj.equals("currentPageIndex"))	return Integer.valueOf(currentPageIndex());
			if(obj.equals("currentPageSize"))	return Integer.valueOf(currentPageSize());
			if(obj.equals("currentPage"))		return currentPage();
			
			if(obj.equals("firstPage"))		return firstPage();
			if(obj.equals("nextPage"))		return nextPage();
			if(obj.equals("previousPage"))		return previousPage();
			if(obj.equals("lastPage"))		return lastPage();
			if(obj.equals("randomPage"))		return randomPage();
			
			if(obj.equals("first"))			return first();
			if(obj.equals("previous"))		return previous();
			if(obj.equals("next"))			return next();
			if(obj.equals("last"))			return last();
			if(obj.equals("random"))		return random();
			
			throw new Exception("Unknown command: "+obj);
		}
		
		public void e() throws Exception
		{
			index = 0;
		}
		
		private int size()
		{
			return list.size();
		}
		
		private Object current()
		{
			if(index<0 || index>=size()) return null;
			return list.get(index);
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
		
		private Object random()
		{
			int index = (int) (Math.random()*size());
			setIndex(index);
			return current();
		}
		
		private Object get(Integer n)
		{
			setIndex(n.intValue());
			return current();
		}
		
		
		private void setPage(int page)
		{setIndex(length*page);}
		
		
		private void setIndex(int v)
		{
			int size = size();
			if(size==0) {index=-1;return;}
			
			while(v<0) v += size;
			while(v>=size) v -= size;
			index = v;
		}
		
		private void setLength(int length) throws Exception
		{
			if(length<=1) throw new Exception("Invalid length value: "+length);
			this.length = length;
		}
		
		
		private int tail()
		{return size()%length;}
		
		
		private int pagecount1()
		{return size()/length;}
		
		
		private int pagecount()
		{
			int size = size();
			int count = size/length;
			int tail = size%length;
			return tail>0 ? count+1 : count;
		}
		
		private int currentPageIndex()
		{return index/length;}
		
		
		private int currentPageSize()
		{
			int tail = tail();
			int size = size();
			return index>=size-tail ? tail : length;
		}
		
		private List currentPage()
		{
			int i = currentPageIndex();
			int start = i*length;
			int end = start + currentPageSize();
			return list.subList(start,end);
		}
		
		private List firstPage()
		{
			setIndex(0);
			return currentPage();
		}
		
		private List previousPage()
		{
			setIndex(index-length);
			return currentPage();
		}
		
		private List nextPage()
		{
			setIndex(index+length);
			return currentPage();
		}
		
		private List lastPage()
		{
			setIndex(size()-tail());
			return currentPage();
		}
		
		private List randomPage()
		{
			int count = pagecount();
			int pageIndex = (int) (Math.random()*count);
			setIndex(length*pageIndex);
			return currentPage();
		}
		
		private int toInt(Object obj)
		{return Integer.parseInt(""+obj);}
	}
}
