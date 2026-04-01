package a.entity.gus06.maincust.entity.generator1.service1;

import a.framework.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140811";}



	public EntityImpl() throws Exception
	{
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String name = (String) o[0];
		G prov = (G) o[1];
		return new Service1(name,prov);
	}
	
	
	
	
	
	private class Service1 implements Service
	{
		private String name;
		private G prov;
	
		public Service1(String name, G prov) throws Exception
		{
			this.name = name;
			this.prov = prov;
		}
		
		private boolean isType(Object o, Class c)
		{return c.isAssignableFrom(o.getClass());}
		
		
		private Object o() throws Exception
		{
			Object o = prov.g();
			if(o==null) throw new Exception("Instance could not be created for entity: "+name);
			return o;
		}

		private Object o(Class c) throws Exception
		{
			Object o = o();
			if(isType(o,c)) return o;
			throw new Exception("Feature "+c.getName()+" not available for entity: "+name);
		}
	
		private Object o_(Class c)
		{
			try
			{
				Object o = o();
				if(isType(o,c)) return o;
			}
			catch(Exception e) {Outside.err(EntityImpl.this,"o_(Class)",e);}
			return null;
		}
		
		
		
		
		public void e() throws Exception
		{((E)o(E.class)).e();}
		
		public void p(Object obj) throws Exception
		{((P)o(P.class)).p(obj);}
		
		public Object i() throws Exception
		{return ((I)o(I.class)).i();}
		
		public Object g() throws Exception
		{return ((G)o(G.class)).g();}
		
		public double h(double value) throws Exception
		{return ((H)o(H.class)).h(value);}
		
		public Object t(Object obj) throws Exception
		{return ((T)o(T.class)).t(obj);}
		
		public boolean f(Object obj) throws Exception
		{return ((F)o(F.class)).f(obj);}
		
		public boolean b() throws Exception
		{return ((B)o(B.class)).b();}
		
		public Object r(String key) throws Exception
		{return ((R)o(R.class)).r(key);}
		
		public void v(String key, Object obj) throws Exception
		{((V)o(V.class)).v(key,obj);}
		
		
		
		
		public void run()
		{
			Runnable r = (Runnable)o_(Runnable.class);
			if(r!=null) r.run();
		}
		
		public void addActionListener(ActionListener listener) throws Exception
		{
			S s = (S)o_(S.class);
			if(s!=null) s.addActionListener(listener);
		}
		
		public void removeActionListener(ActionListener listener) throws Exception
		{
			S s = (S)o_(S.class);
			if(s!=null) s.removeActionListener(listener);
		}
		
		public List listeners() throws Exception
		{
			S s = (S)o_(S.class);
			return s!=null?s.listeners():new ArrayList();
		}
	}
}