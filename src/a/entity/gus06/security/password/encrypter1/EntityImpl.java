package a.entity.gus06.security.password.encrypter1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160307";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		int n = toInt(o[1]);
		String offset = (String) o[2];
		String tail = (String) o[3];
		
		return new T1(t,n,offset,tail);
	}
	
	
	
	private class T1 implements T
	{
		private T t;
		private int n;
		private String offset;
		private String tail;
		
		public T1(T t, int n, String offset, String tail)
		{
			this.t = t;
			this.n = n;
			this.offset = offset;
			this.tail = tail;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			for(int i=0;i<n;i++) s = (String) t.t(offset+s+tail);
			return s;
		}
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
