package a.entity.gus06.sys.keystroke1.holder.comp.textcomp;

import a.framework.*;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191229";}


	private Service stringToKeyStroke;
	

	public EntityImpl() throws Exception
	{
		stringToKeyStroke = Outside.service(this,"gus06.convert.stringtokeystroke");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		String id = (String) o[0];
		String key1 = (String) o[1];
		JTextComponent comp = (JTextComponent) o[2];
		Action action = (Action) o[3];
		
		return new Holder(id,key1,comp,action);
	}
	
	
	
	private class Holder implements R, P
	{
		private String id;
		private String key1;
		private JTextComponent comp;
		private Action action;
		private KeyStroke keyStroke;
		
		public Holder(String id, String key1, JTextComponent comp, Action action) throws Exception
		{
			this.key1 = key1;
			this.comp = comp;
			this.action = action;
			
			updateComp();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("id")) return id;
			if(key.equals("key1")) return key1;
			if(key.equals("comp")) return comp;
			if(key.equals("action")) return action;
			
			if(key.equals("keys")) return new String[]{"id","key1","comp","action"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void p(Object obj) throws Exception
		{
			key1 = (String) obj;
			updateComp();
		}
		
		private void updateComp() throws Exception
		{
			if(keyStroke!=null) comp.getInputMap().remove(keyStroke);
			keyStroke = (KeyStroke) stringToKeyStroke.t(key1);
			comp.getInputMap().put(keyStroke,action);
		}
	}
}
