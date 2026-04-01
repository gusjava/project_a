package a.entity.gus06.sys.expression1.apply.op._serviceempty;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160630";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return new Service0();
	}
	
	private class Service0 implements Service
	{
		public void e() throws Exception  {}
		public boolean f(Object obj) throws Exception {return true;}
		public boolean b() throws Exception {return true;}
		public double h(double value) throws Exception{return 0;}
		public void p(Object obj) throws Exception{}
		public Object g() throws Exception{return null;}
		public void v(String key, Object obj) throws Exception{}
		public Object r(String key) throws Exception{return null;}
		public Object t(Object obj) throws Exception{return null;}
		public Object i() throws Exception{return null;}
		public void addActionListener(ActionListener listener){}
		public void removeActionListener(ActionListener listener){}
		public List listeners(){return null;}
		public void run(){}
	}
}
