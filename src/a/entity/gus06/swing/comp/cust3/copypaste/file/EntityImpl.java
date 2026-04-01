package a.entity.gus06.swing.comp.cust3.copypaste.file;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200331";}
	
	public static final String KEY_C = "ctrl c";
	public static final String KEY_V = "ctrl v";


	private Service toClipboard;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.file");
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
			File file = toFile(obj);
			if(file==null || !file.exists()) return;
			
			toClipboard.p(file);
		}
	}
	
	
	private class Paster implements E
	{
		private Object obj;
		public Paster(Object obj){this.obj = obj;}
		
		public void e() throws Exception
		{
			File file = (File) toClipboard.g();
			if(file==null || !file.exists()) return;
			((P) obj).p(file);
		}
	}
	
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return (File) obj;
		if(obj instanceof G) return (File) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
