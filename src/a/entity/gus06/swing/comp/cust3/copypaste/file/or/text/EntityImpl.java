package a.entity.gus06.swing.comp.cust3.copypaste.file.or.text;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210717";}
	
	public static final String KEY_C = "ctrl c";
	public static final String KEY_V = "ctrl v";


	private Service toClipboard;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.string.or.file");
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
		private Object getter;
		public Copier(Object getter){this.getter = getter;}
		
		public void e() throws Exception
		{
			Object content = toContent(getter);
			if(isValid(content)) toClipboard.p(content);
		}
	}
	
	
	private class Paster implements E
	{
		private Object setter;
		public Paster(Object setter){this.setter = setter;}
		
		public void e() throws Exception
		{
			Object content = toClipboard.g();
			if(isValid(content)) ((P) setter).p(content);
		}
	}
	
	
	private boolean isValid(Object content)
	{
		if(content==null) return false;
		if(content instanceof File) return ((File) content).exists();
		if(content instanceof String) return true;
		return false;
	}
	
	private Object toContent(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof G) return ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}