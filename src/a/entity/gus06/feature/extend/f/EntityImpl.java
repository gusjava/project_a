package a.entity.gus06.feature.extend.f;

import a.framework.*;
import java.util.List;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220530";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object main = o[0];
		T extension = (T) o[1];
		
		return new Wrap(main, extension);
	}
	
	
	
	
	
	private class Wrap implements E, G, P, T, F, H, I, R, V, S
	{
		private E e;
		private G g;
		private P p;
		private T t;
		private F f;
		private H h;
		private I i;
		private R r;
		private V v;
		private S s;
		
		private T extension;
		
		public Wrap(Object main, T extension) throws Exception
		{
			e = (E) (main instanceof E ? main : null);
			g = (G) (main instanceof G ? main : null);
			p = (P) (main instanceof P ? main : null);
			t = (T) (main instanceof T ? main : null);
			f = (F) (main instanceof F ? main : null);
			h = (H) (main instanceof H ? main : null);
			i = (I) (main instanceof I ? main : null);
			r = (R) (main instanceof R ? main : null);
			v = (V) (main instanceof V ? main : null);
			s = (S) (main instanceof S ? main : null);
			
			this.extension = extension;
		}
		
		private void check(Object obj, String feature) throws Exception
		{if(obj==null) throw new Exception("Feature not available: "+feature);}
		
		
		
		public void e() throws Exception
		{
			check(e,"E");
			e.e();
		}
		
		public Object g() throws Exception
		{
			check(g,"G");
			return g.g();
		}
		
		public void p(Object obj) throws Exception
		{
			check(p,"P");
			p.p(obj);
		}
		
		public Object t(Object obj) throws Exception
		{
			check(t,"T");
			return t.t(obj);
		}
		
		public boolean f(Object obj) throws Exception
		{
			check(f,"F");
			return f.f(extension.t(obj));
		}
		
		public double h(double value) throws Exception
		{
			check(h,"H");
			return h.h(value);
		}
		
		public Object i() throws Exception
		{
			check(i,"I");
			return i.i();
		}
		
		public Object r(String key) throws Exception
		{
			check(r,"R");
			return r.r(key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			check(v,"V");
			v.v(key,obj);
		}
		
		public void addActionListener(ActionListener l) throws Exception
		{
			if(s!=null) s.addActionListener(l);
		}
		
		public void removeActionListener(ActionListener l) throws Exception
		{
			if(s!=null) s.removeActionListener(l);
		}
		
		public List listeners() throws Exception
		{
			return s!=null ? s.listeners() : null;
		}
	}
}