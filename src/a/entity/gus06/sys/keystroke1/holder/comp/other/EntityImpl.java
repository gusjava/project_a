package a.entity.gus06.sys.keystroke1.holder.comp.other;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Action;

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
		JComponent comp = (JComponent) o[2];
		Action action = (Action) o[3];
		
		return new Holder(id,key1,comp,action);
	}
	
	
	
	private class Holder implements R, P
	{
		private String id;
		private String key1;
		private JComponent comp;
		private Action action;
		private KeyStroke keyStroke;
		
		public Holder(String id, String key1, JComponent comp, Action action) throws Exception
		{
			this.id = id;
			this.key1 = key1;
			this.comp = comp;
			this.action = action;
			
			comp.getActionMap().put(id,action);
			
			updateComp();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("key1")) return key1;
			if(key.equals("comp")) return comp;
			if(key.equals("action")) return action;
			
			if(key.equals("keys")) return new String[]{"key1","comp","action"};
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
			comp.getInputMap().put(keyStroke,id);
		}
	}
}
