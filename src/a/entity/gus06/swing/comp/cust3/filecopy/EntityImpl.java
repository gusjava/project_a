package a.entity.gus06.swing.comp.cust3.filecopy;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191220";}
	
	public static final String KEY = "ctrl c";


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
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		Object provider = o[1];
		
		Copier copier = new Copier(provider);
		onKey.p(new Object[]{comp,KEY,copier});
	}
	
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return (File) obj;
		if(obj instanceof G) return (File) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Copier implements E
	{
		private Object provider;
		public Copier(Object provider)
		{this.provider = provider;}
		
		public void e() throws Exception
		{
			File file = toFile(provider);
			if(file==null || !file.exists()) return;
			
			toClipboard.p(file);
		}
	}
}
