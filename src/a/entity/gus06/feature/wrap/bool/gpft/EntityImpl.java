package a.entity.gus06.feature.wrap.bool.gpft;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150708";}

	
	
	public Object t(Object obj) throws Exception
	{return new Wrap((Boolean) obj);}
	
	public Object g() throws Exception
	{return new Wrap(Boolean.TRUE);}
	
	
	
		
	
	private class Wrap implements G, F, P, T
	{
		private Boolean data;
		public Wrap(Boolean data) {this.data = data;}
		
		public Object g() throws Exception
		{return data;}
		
		public void p(Object obj) throws Exception
		{this.data = (Boolean) obj;}
		
		public boolean f(Object obj) throws Exception
		{return data.booleanValue();}
		
		public Object t(Object obj) throws Exception
		{return data;}
	}
}
