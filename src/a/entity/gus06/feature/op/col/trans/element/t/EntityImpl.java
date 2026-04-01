package a.entity.gus06.feature.op.col.trans.element.t;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}


	
	public Object t(Object obj) throws Exception
	{return new T1((T) obj);}
	
	
	
	
	private class T1 implements T
	{
		private T t;
		public T1(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Object[]) return newArray((Object[]) obj);
			if(obj instanceof List) return newList((List) obj);
			if(obj instanceof Set) return newSet((Set) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private Object[] newArray(Object[] l) throws Exception
		{
			Object[] l1 = new Object[l.length];
			for(int i=0;i<l.length;i++) l1[i] = t.t(l[i]);
			return l1;
		}
		
		private List newList(List l) throws Exception
		{
			List l1 = new ArrayList();
			for(Object o:l) l1.add(t.t(o));
			return l1;
		}
		
		private Set newSet(Set l) throws Exception
		{
			Set l1 = new HashSet();
			Iterator it = l.iterator();
			while(it.hasNext()) l1.add(t.t(it.next()));
			return l1;
		}
	}
}
