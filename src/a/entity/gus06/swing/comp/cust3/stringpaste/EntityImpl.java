package a.entity.gus06.swing.comp.cust3.stringpaste;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201023";}
	
	public static final String KEY = "ctrl v";


	private Service clipboard;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		P handler = (P) o[1];
		
		Executer executer = new Executer(handler);
		onKey.p(new Object[]{comp,KEY,executer});
	}
	
	
	
	private class Executer implements E
	{
		private P handler;
		public Executer(P handler)
		{this.handler = handler;}
		
		public void e() throws Exception
		{
			String s = (String) clipboard.g();
			if(s!=null) handler.p(s);
		}
	}
}
