package a.entity.gus06.sys.async.guibuilder.dataholder;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141207";}


	private Service guiBuilder;


	public EntityImpl() throws Exception
	{guiBuilder = Outside.service(this,"gus06.sys.async.guibuilder.callback");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return new Holder((String) obj,null);
			
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return new Holder((String) o[0],o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private class Holder extends S1 implements P, G, I, R, V
	{
		private Object editor;
		private Object data;
		private JComponent comp;
		
		public Holder(String name, Object data) throws Exception
		{
			this.data = data;
			this.comp = (JComponent) guiBuilder.t(new Object[]{name,new P(){
				public void p(Object obj) throws Exception
				{initEditor(obj);}
			}});
		}
		
		public Object i() throws Exception
		{return comp;}
		
		public synchronized Object g() throws Exception
		{return data;}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("editor")) return editor;
			if(key.equals("data")) return data;
			
			if(key.equals("keys")) return new String[]{"editor","data"};
			throw new Exception("Unknown key: "+key);
		}
		
		
		public synchronized void p(Object obj) throws Exception
		{
			this.data = obj;
			update();
		}
		
		public synchronized void v(String key, Object obj) throws Exception
		{
			if(editor==null) throw new Exception("Failed to call V on editor: null");
			if(!(editor instanceof V)) throw new Exception("V not available for editor: "+editor);
			
			((V) editor).v(key, obj);
		}
		
		
		private synchronized void initEditor(Object editor) throws Exception
		{
			this.editor = editor;
			update();
			initialized();
		}
		
		private void update() throws Exception
		{
			if(editor!=null && editor instanceof P)
			((P) editor).p(data);
		}
		
		private void initialized()
		{send(this,"initialized()");}
	}
}