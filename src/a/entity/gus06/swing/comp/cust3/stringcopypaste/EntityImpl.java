package a.entity.gus06.swing.comp.cust3.stringcopypaste;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201023";}
	
	public static final String KEY_C = "ctrl c";
	public static final String KEY_V = "ctrl v";


	private Service toClipboard;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		Object setter = o[1];
		Object getter = o[2];
		
		Paster paster = new Paster(setter);
		Copier copier = new Copier(getter);
		
		onKey.p(new Object[]{comp,KEY_V,paster});
		onKey.p(new Object[]{comp,KEY_C,copier});
	}
	
	
	private class Copier implements E
	{
		private Object obj;
		public Copier(Object obj){this.obj = obj;}
		
		public void e() throws Exception
		{
			String s = toString_(obj);
			if(s==null) return;
			toClipboard.p(s);
		}
	}
	
	
	private class Paster implements E
	{
		private Object obj;
		public Paster(Object obj){this.obj = obj;}
		
		public void e() throws Exception
		{
			String s = (String) toClipboard.g();
			if(s==null) return;
			((P) obj).p(s);
		}
	}
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof G) return (String) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
