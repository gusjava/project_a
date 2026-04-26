package a.entity.gus06.swing.comp.cust3.stringcopy;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201023";}
	
	public static final String KEY = "ctrl c";


	private Service toClipboard;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		Object provider = o[1];
		
		Copier copier = new Copier(provider);
		onKey.p(new Object[]{comp,KEY,copier});
	}
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof G) return (String) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Copier implements E
	{
		private Object provider;
		public Copier(Object provider)
		{this.provider = provider;}
		
		public void e() throws Exception
		{
			String s = toString_(provider);
			if(s==null) return;
			
			toClipboard.p(s);
		}
	}
}
