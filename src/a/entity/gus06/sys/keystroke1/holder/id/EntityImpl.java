package a.entity.gus06.sys.keystroke1.holder.id;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.Action;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170513";}


	private Service holderComp;
	private Service persister;
	

	public EntityImpl() throws Exception
	{
		holderComp = Outside.service(this,"gus06.sys.keystroke1.holder.comp");
		persister = Outside.service(this,"gus06.app.persister1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		String id = (String) o[0];
		String key0 = (String) o[1];
		JComponent comp = (JComponent) o[2];
		Action action = (Action) o[3];
		
		return new Holder(id,key0,comp,action);
	}
	
	
	
	private class Holder extends S1 implements R, V, P, E
	{
		private String id;
		private String key1;
		private String key0;
		
		private Set holders;
		
		public Holder(String id, String key0, JComponent comp, Action action) throws Exception
		{
			this.id = id;
			this.key0 = key0;
			
			String pKey = (String) persister.r(id+"_keystroke");
			this.key1 = pKey!=null ? pKey : key0;
			
			holders = new HashSet();
			Object holder = holderComp.t(new Object[]{id,key1,comp,action});
			holders.add(holder);
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("id")) return id;
			if(key.equals("key1")) return key1;
			if(key.equals("key0")) return key0;
			if(key.equals("holders")) return holders;
			
			if(key.equals("keys")) return new String[]{"id","key1","key0","holders"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("comp_action")) {addHolder((Object[]) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		public void p(Object obj) throws Exception
		{changeKey((String) obj);}
		
		
		public void e() throws Exception
		{changeKey(key0);}
		
		
		private void changeKey(String k) throws Exception
		{
			if(k==null || k.equals("")) k = key0;
			
			key1 = k;
			persister.v(id+"_keystroke",key1);
			
			Iterator it = holders.iterator();
			while(it.hasNext())
			{
				P holder = (P) it.next();
				holder.p(key1);
			}
		}
		
		private void addHolder(Object[] o) throws Exception
		{
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			JComponent comp = (JComponent) o[0];
			Action action = (Action) o[1];
			
			Object holder = holderComp.t(new Object[]{id,key1,comp,action});
			holders.add(holder);
		}
	}
}
