package a.entity.gus06.sys.base1.builder.access;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150524";}


	private Service read;
	private Service write;
	private Service check;
	private Service listing;
	private Service clear;


	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.sys.base1.builder.access.read");
		write = Outside.service(this,"gus06.sys.base1.builder.access.write");
		check = Outside.service(this,"gus06.sys.base1.builder.access.check");
		listing = Outside.service(this,"gus06.sys.base1.builder.access.listing");
		clear = Outside.service(this,"gus06.sys.base1.builder.access.clear");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((G)obj);}
	
	
	
	
	private class Holder implements R, V, F, G, E
	{
		private G g;
		public Holder(G g) {this.g = g;}
	
		public Object r(String key) throws Exception
		{
			Object obj = null;
			synchronized(g) {obj = read(g,key);}
			return obj;
		}
	
		public void v(String key, Object obj) throws Exception
		{
			synchronized(g) {write(g,key,obj);}
		}
		
		public boolean f(Object obj) throws Exception
		{
			boolean found = false;
			synchronized(g) {found = check(g,(String)obj);}
			return found;
		}
		
		public Object g() throws Exception
		{
			Object obj = null;
			synchronized(g) {obj = listing(g);}
			return obj;
		}
		
		public void e() throws Exception
		{
			synchronized(g) {clear(g);}
		}
	}
	
	
	
	
	private Object read(G g, String id) throws Exception
	{return read.t(new Object[]{g,id});}
	
	private void write(G g, String id, Object data) throws Exception
	{write.p(new Object[]{g,id,data});}
	
	private boolean check(G g, String id) throws Exception
	{return check.f(new Object[]{g,id});}
	
	private Object listing(G g) throws Exception
	{return listing.t(g);}
	
	private void clear(G g) throws Exception
	{clear.p(g);}
}
