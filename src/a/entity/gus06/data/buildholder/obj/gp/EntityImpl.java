package a.entity.gus06.data.buildholder.obj.gp;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20161210";}


	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}

	
	
	public Object g() throws Exception
	{return new Holder(null);}
	
	
	
	private class Holder implements P, G
	{
		private Object data;
		
		public Holder(Object data)
		{this.data = data;}
		
		public Object g() throws Exception
		{return data;}
		
		public void p(Object obj) throws Exception
		{data = obj;}
	}
}
