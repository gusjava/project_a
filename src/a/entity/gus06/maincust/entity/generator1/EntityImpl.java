package a.entity.gus06.maincust.entity.generator1;

import a.framework.*;
import javax.swing.JComponent;
import java.util.List;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140810";}

	public static final boolean GRAPHIC_MODE = true;



	private Service buildProvider;
	private Service buildService1;
	private Service buildGraphicHolder;
	

	public EntityImpl() throws Exception
	{
		buildProvider = Outside.service(this,"gus06.maincust.entity.generator1.provider");
		buildService1 = Outside.service(this,"gus06.maincust.entity.generator1.service1");
		buildGraphicHolder = Outside.service(this,"gus06.maincust.entity.generator1.graphicholder");
	}
	
	
	
	
	
	
	public Object t(Object obj) throws Exception
	{return new Service2((String) obj);}
	
	public Object r(String key) throws Exception
	{return new Service2(key);}
	
	
	
	
	
	private class Service2 implements Service
	{
		private String name;
	
		private Service s;
		private I i;
		private G g;
		
		
		public Service2(String name) throws Exception
		{
			this.name = name;
		
			g = (G) buildProvider.t(name);
			s = (Service) buildService1.t(new Object[]{name,g});
			if(GRAPHIC_MODE) i = (I) buildGraphicHolder.t(new Object[]{name,g});
		}
		
		
		
		public void e() throws Exception
		{s.e();}
		
		public void p(Object obj) throws Exception
		{s.p(obj);}
		
		public Object g() throws Exception
		{return s.g();}

		public boolean b() throws Exception
		{return s.b();}
		
		public double h(double value) throws Exception
		{return s.h(value);}
		
		public Object t(Object obj) throws Exception
		{return s.t(obj);}
		
		public boolean f(Object obj) throws Exception
		{return s.f(obj);}
		
		public Object r(String key) throws Exception
		{return s.r(key);}
		
		public void v(String key, Object obj) throws Exception
		{s.v(key,obj);}
		
		public void addActionListener(ActionListener listener) throws Exception
		{s.addActionListener(listener);}
		
		public void removeActionListener(ActionListener listener) throws Exception
		{s.removeActionListener(listener);}
		
		public List listeners() throws Exception
		{return s.listeners();}
		
		
		public Object i() throws Exception
		{return i!=null?(JComponent) i.i():(JComponent) s.i();}
	}
}