package a.entity.gus06.feature.wrap.builder1;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}
	
	
	private Service wrapObj;
	private Service wrapBool;
	private Service wrapDouble;
	
	public EntityImpl() throws Exception
	{
		wrapObj = Outside.service(this,"gus06.feature.wrap.o.gpti");
		wrapBool = Outside.service(this,"gus06.feature.wrap.bool.gpft");
		wrapDouble = Outside.service(this,"gus06.feature.wrap.double1.gph");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Wrap((Map) obj);}
	
	
	
	private I toI(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof I) return (I) obj;
		return (I) wrapObj.t(obj);
	}
	
	private G toG(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof G) return (G) obj;
		return (G) wrapObj.t(obj);
	}
	
	private F toF(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof F) return (F) obj;
		if(obj instanceof Boolean) return (F) wrapBool.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private H toH(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof H) return (H) obj;
		if(obj instanceof Double) return (H) wrapDouble.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
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
		
		private E defaultE;
		private G defaultG;
		private P defaultP;
		private T defaultT;
		private F defaultF;
		private H defaultH;
		private I defaultI;
		private R defaultR;
		private V defaultV;
		private S defaultS;
		
		public Wrap(Map map) throws Exception
		{
			e = (E) get(map,"E");
			g = toG(get(map,"G"));
			p = (P) get(map,"P");
			t = (T) get(map,"T");
			f = toF(get(map,"F"));
			h = toH(get(map,"H"));
			i = toI(get(map,"I"));
			r = (R) get(map,"R");
			v = (V) get(map,"V");
			s = (S) get(map,"S");
			
			Object defaultObj = get(map,"default");
			if(defaultObj!=null)
			{
				defaultE = (E) (defaultObj instanceof E? defaultObj : null);
				defaultG = (G) (defaultObj instanceof G? defaultObj : null);
				defaultP = (P) (defaultObj instanceof P? defaultObj : null);
				defaultT = (T) (defaultObj instanceof T? defaultObj : null);
				defaultF = (F) (defaultObj instanceof F? defaultObj : null);
				defaultH = (H) (defaultObj instanceof H? defaultObj : null);
				defaultI = (I) (defaultObj instanceof I? defaultObj : null);
				defaultR = (R) (defaultObj instanceof R? defaultObj : null);
				defaultV = (V) (defaultObj instanceof V? defaultObj : null);
				defaultS = (S) (defaultObj instanceof S? defaultObj : null);
			}
		}
		
		private Object get(Map map, String key)
		{
			if(map==null) return null;
			if(!map.containsKey(key)) return null;
			return map.get(key);
		}
		
		private Object check(Object obj, Object defaultObj, String feature) throws Exception
		{
			if(obj!=null) return obj;
			if(defaultObj!=null) return defaultObj;
			throw new Exception("Feature not available: "+feature);
		}
		
		
		
		public void e() throws Exception
		{
			E e1 = (E) check(e, defaultE, "E");
			e1.e();
		}
		
		public Object g() throws Exception
		{
			G g1 = (G) check(g, defaultG, "G");
			return g1.g();
		}
		
		public void p(Object obj) throws Exception
		{
			P p1 = (P) check(p, defaultP, "P");
			p1.p(obj);
		}
		
		public Object t(Object obj) throws Exception
		{
			T t1 = (T) check(t, defaultT, "t");
			return t1.t(obj);
		}
		
		public boolean f(Object obj) throws Exception
		{
			F f1 = (F) check(f, defaultF, "f");
			return f1.f(obj);
		}
		
		public double h(double value) throws Exception
		{
			H h1 = (H) check(h, defaultH, "h");
			return h1.h(value);
		}
		
		public Object i() throws Exception
		{
			I i1 = (I) check(i, defaultI, "i");
			return i1.i();
		}
		
		public Object r(String key) throws Exception
		{
			R r1 = (R) check(r, defaultR, "r");
			return r1.r(key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			V v1 = (V) check(v, defaultV, "v");
			v1.v(key,obj);
		}
		
		public void addActionListener(ActionListener l) throws Exception
		{
			S s1 = s!=null ? s : defaultS;
			if(s1!=null) s1.addActionListener(l);
		}
		
		public void removeActionListener(ActionListener l) throws Exception
		{
			S s1 = s!=null ? s : defaultS;
			if(s1!=null) s1.removeActionListener(l);
		}
		
		public List listeners() throws Exception
		{
			S s1 = s!=null ? s : defaultS;
			return s1!=null ? s1.listeners() : null;
		}
	}
}