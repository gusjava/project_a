package a.entity.gus06.sys.property1.binder;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150910";}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return new Binder(o[0],o[1]);
	}
	
	
	
	private class Binder extends S1 implements ActionListener, P, R
	{
		private Object bind1;
		private Object bind2;
		private boolean enabled;
		
		public Binder(Object bind1, Object bind2) throws Exception
		{
			this.bind1 = bind1;
			this.bind2 = bind2;
			enabled = true;
			
			addListener(bind1);
			addListener(bind2);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(!enabled) return;
			
			G source = (G) e.getSource();
			P dest = (P) (bind1==source?bind2:bind1);
			
			try
			{
				removeListener(dest);
				transfer(source,dest);
				addListener(dest);
			}
			catch (Exception e1)
			{Outside.err(EntityImpl.this, "actionPerformed(ActionEvent)", e1);}
		}
		
		private void addListener(Object o) throws Exception
		{if(o instanceof S) ((S) o).addActionListener(this);}
		
		private void removeListener(Object o) throws Exception
		{if(o instanceof S) ((S) o).removeActionListener(this);}
		
		
		
		public void p(Object obj) throws Exception
		{
			if(obj.equals("enable")) {enabled = true;return;}
			if(obj.equals("disable")) {enabled = false;return;}
			
			throw new Exception("Unknown command: "+obj);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("bind1")) return bind1;
			if(key.equals("bind2")) return bind2;
			if(key.equals("keys")) return new String[]{"bind1","bind2"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
	
	
	
	

	private void transfer(G g, P p)
	{
		try{p.p(g.g());}
		catch(Exception e)
		{Outside.err(this,"transfer(G,P)",e);}
	}
}
