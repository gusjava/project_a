package a.entity.gus06.swing.comp.cust3.on.keytyped.with.execute;

import javax.swing.JComponent;
import a.framework.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170819";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.convert.stringtokeyeventcode");
	}


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length==3) 
		{
			JComponent comp = (JComponent) t[0];
			Object key = t[1];
			E exec = (E) t[2];
			handleKey(comp,key,exec);
		}
		else if(t.length==2)
		{
			JComponent comp = (JComponent) t[0];
			Map map = (Map) t[1];
			
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				E exec = (E) map.get(key);
				handleKey(comp,key,exec);
			}
		}
		else throw new Exception("Wong data number: "+t.length);
	}
	
	
	private void handleKey(JComponent comp, Object key, E exec) throws Exception
	{
		int code = ((Integer) find.t(key)).intValue();
		new Holder(comp,code,exec);
	}
	
	
	
	private class Holder extends KeyAdapter
	{
		private JComponent comp;
		private int code;
		private E exec;
		
		public Holder(JComponent comp, int code, E exec)
		{
			this.comp = comp;
			this.code = code;
			this.exec = exec;
			
			comp.setFocusable(true);
			comp.addKeyListener(this);
		}
		public void keyTyped(KeyEvent e)
		{
			if(e.getKeyCode()==code) exec(exec);
		}
	}
	
	
	
	private void exec(E exec)
	{
		try{exec.e();}
		catch(Exception e)
		{Outside.err(this,"exec(E)",e);}
	}
}