package a.entity.gus06.service.wrapper3;

import a.framework.*;
import java.util.List;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251021";}


	

	public Object t(Object obj) throws Exception
	{
		return new ServiceImpl((G) obj);
	}

	
	
	private class ServiceImpl implements Service
	{
		private G g;
		private Object target;
		private boolean initialized = false;
		
		public ServiceImpl(G g)
		{this.g = g;}
		
		private void check(Class c) throws Exception
		{
			if(!initialized)
			{
				target = g.g();
				initialized = true;
			}
			if(target==null) throw new Exception("target is null (retrieved from "+g+")");
			if(!c.isAssignableFrom(target.getClass())) throw new Exception("Feature not available: "+c.getName());
		}
		
		
		public void e() throws Exception
		{
			check(E.class);
			((E) target).e();
		}

		public boolean f(Object obj) throws Exception
		{
			check(F.class);
			return ((F) target).f(obj);
		}

		public boolean b() throws Exception
		{
			check(B.class);
			return ((B) target).b();
		}
		
		public double h(double value) throws Exception
		{
			check(H.class);
			return ((H) target).h(value);
		}

		public void p(Object obj) throws Exception
		{
			check(P.class);
			((P) target).p(obj);
		}
		
		public Object g() throws Exception
		{
			check(G.class);
			return ((G) target).g();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			check(V.class);
			((V) target).v(key,obj);
		}
		
		public Object r(String key) throws Exception
		{
			check(R.class);
			return ((R) target).r(key);
		}
		
		public Object t(Object obj) throws Exception
		{
			check(T.class);
			return ((T) target).t(obj);
		}
		
		public Object i() throws Exception
		{
			check(I.class);
			return ((I) target).i();
		}
		
		public void addActionListener(ActionListener listener) throws Exception
		{
			if(target==null || !(target instanceof S)) return;
			((S) target).addActionListener(listener);
		}
		
		public void removeActionListener(ActionListener listener) throws Exception
		{
			if(target==null || !(target instanceof S)) return;
			((S) target).removeActionListener(listener);
		}
		
		public List listeners() throws Exception
		{
			if(target==null || !(target instanceof S)) return null;
			return ((S) target).listeners();
		}
		
		public void run()
		{
			if(target==null || !(target instanceof Runnable)) return;
			((Runnable) target).run();
		}
	}
}