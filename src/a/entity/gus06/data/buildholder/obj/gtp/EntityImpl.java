package a.entity.gus06.data.buildholder.obj.gtp;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20161015";}


	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}

	
	
	public Object g() throws Exception
	{return new Holder(null);}
	
	
	
	private class Holder extends S1 implements T, P, G
	{
		private Object data;
		
		public Holder(Object data)
		{this.data = data;}
		
		public Object g() throws Exception
		{return data;}
		
		public void p(Object obj) throws Exception
		{
			data = obj;
			modified();
		}
		
		public Object t(Object obj) throws Exception
		{
			Object data1 = obj;
			int change = getChange(data1);
			data = data1;
			modified();
			return Integer.valueOf(change);
		}
		
		private int getChange(Object data1)
		{
			if(data==null && data1==null) return 0;
			if(data!=null && data1!=null && data.equals(data1)) return 0;
			
			if(data==null) return 1;
			if(data1==null) return -1;
			return 2;
		}
		
		private void modified()
		{send(this,"modified()");}
	}
}
