package a.entity.gus06.reflection.wrap.class1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160910";}


	private Service analyze;
	private Service change;
	
	public EntityImpl() throws Exception
	{
		analyze = Outside.service(this,"gus06.reflection.wrap.class1.analyze");
		change = Outside.service(this,"gus06.reflection.wrap.class1.change");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder(obj);
	}
	
	
	
	private class Holder implements T, G, P, R, V
	{
		private Object data;
		
		public Holder(Object data)
		{this.data = data;}
		
		public void p(Object obj) throws Exception
		{data = obj;}
		
		public Object g() throws Exception
		{return data;}
		
		public Object t(Object obj) throws Exception
		{return analyze.t(new Object[]{data,obj});}
		
		public Object r(String key) throws Exception
		{return analyze.t(new Object[]{data,key});}
		
		public void v(String key, Object obj) throws Exception
		{change.p(new Object[]{data,key,obj});}
	}
	
}