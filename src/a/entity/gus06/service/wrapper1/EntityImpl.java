package a.entity.gus06.service.wrapper1;

import a.framework.*;
import java.util.List;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170524";}


	

	public Object t(Object obj) throws Exception
	{
		return new ServiceImpl(obj);
	}

	
	
	private class ServiceImpl implements Service
	{
		private Object target;
		
		public ServiceImpl(Object target)
		{this.target = target;}
		
		private boolean check(Class c)
		{return target!=null && c.isAssignableFrom(target.getClass());}
		
		
		public void e() throws Exception
		{if(check(E.class)) ((E) target).e();}

		public boolean f(Object obj) throws Exception
		{return check(F.class) ? ((F) target).f(obj) : true;}

		public boolean b() throws Exception
		{return check(B.class) ? ((B) target).b() : true;}
		
		public double h(double value) throws Exception
		{return check(H.class) ? ((H) target).h(value) : 0;}

		public void p(Object obj) throws Exception
		{if(check(P.class)) ((P) target).p(obj);}
		
		public Object g() throws Exception
		{return check(G.class) ? ((G) target).g() : null;}
		
		public void v(String key, Object obj) throws Exception
		{if(check(V.class)) ((V) target).v(key,obj);}
		
		public Object r(String key) throws Exception
		{return check(R.class) ? ((R) target).r(key) : null;}
		
		public Object t(Object obj) throws Exception
		{return check(T.class) ? ((T) target).t(obj) : null;}
		
		public Object i() throws Exception
		{return check(I.class) ? ((I) target).i() : null;}
		
		public void addActionListener(ActionListener listener) throws Exception
		{if(check(S.class)) ((S) target).addActionListener(listener);}
		
		public void removeActionListener(ActionListener listener) throws Exception
		{if(check(S.class)) ((S) target).removeActionListener(listener);}
		
		public List listeners() throws Exception
		{return check(S.class) ? ((S) target).listeners() : null;}
		
		public void run()
		{if(check(Runnable.class)) ((Runnable) target).run();}
	}
}
