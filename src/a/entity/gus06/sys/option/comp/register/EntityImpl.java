package a.entity.gus06.sys.option.comp.register;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20141123";}


	public static final String KEY = "number";

	private Service manager;
	private Service wrapper;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.option.manager");
		wrapper = Outside.service(this,"gus06.swing.comp.wrapper");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Object comp = wrapper.t(obj);
		
		if(!init(key,(P) comp))
		store(key,(G) comp);
		new Holder(key,comp);
	}
	
	
	
	
	
	private class Holder
	{
		private P p;
		private G g;
		private S s;
		
		private String key;
		private boolean transfering = false;
		
		public Holder(String key, Object comp) throws Exception
		{
			this.p = (P) comp;
			this.g = (G) comp;
			this.s = (S) comp;
			
			this.key = key;
			
			s.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {compToMap();}
			});
			manager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {mapToComp();}
			});
		}
		
		
		private void compToMap()
		{
			if(transfering) return;
			transfering = true;
			store(key,g);
			transfering = false;
		}
		
		private void mapToComp()
		{
			if(transfering) return;
			transfering = true;
			init(key,p);
			transfering = false;
		}
	}

	
	
	
	
	private boolean init(String key, P p)
	{
		try
		{
			String value = (String) manager.r(key);
			if(value==null) return false;
			p.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"init(String,P)",e);}
		return true;
	}
	
	private void store(String key, G g)
	{
		try
		{
			String value = (String) g.g();
			if(value==null) return;
			manager.v(key,value);
		}
		catch(Exception e)
		{Outside.err(this,"store(String,G)",e);}
	}
}
