package a.entity.gus06.jdbc.gui.cx1.db.list.updater2;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import a.framework.*;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, P, E, Runnable {

	public String creationDate() {return "20250818";}


	private Service threadManager;
	private Service findDbCounts;
	private Service findDbSet;
	private Service findCx;
	
	private List<UpdateHolder> list;
	private Thread t;
	private Map previous;


	public EntityImpl() throws Exception
	{
		threadManager = Outside.service(this,"gus.x.thread.wrap1");
		findDbCounts = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb");
		findDbSet = Outside.service(this,"gus06.jdbc.generic.perform.find.dbset");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		
		list = new Vector<>();
	}



	public void p(Object obj) throws Exception
	{
		if(obj==null) {e();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object cxHolder = o[0];
		Object tableHolder = o[1];
		JLabel label = (JLabel) o[2];
		
		list.add(new UpdateHolder(cxHolder,tableHolder,label));
		
		if(t!=null && t.isAlive()) return;
		t = (Thread) threadManager.t(this);
		t.start();
	}
	
	
	public void e() throws Exception
	{
		list.clear();
	}
	
		
	public void run()
	{
		while(true)
		{
			perform();
			try{Thread.sleep(100);}
			catch(Exception e){}
		}
	}
	
	
	private void perform()
	{
		try
		{
			if(list.isEmpty()) return;
			while(list.size()>1) list.remove(0);
			list.get(0).updateAll();
		}
		catch(Exception e)
		{
			Outside.err(this,"perform()",e);
		}
	}
	
	
	
	
	private class UpdateHolder
	{
		private Object cxHolder;
		private Object tableHolder;
		private JLabel label;
		
		public UpdateHolder(Object cxHolder, Object tableHolder, JLabel label)
		{
			this.cxHolder = cxHolder;
			this.tableHolder = tableHolder;
			this.label = label;
		}
		
		public void updateAll() throws Exception
		{
			Connection cx = (Connection) findCx.t(cxHolder);
			if(cx==null) return;
			
			List selected = (List) ((G)tableHolder).g();
			Map map = cxToMap(cx);
			if(isSame(map,previous)) return;
	
			label.setText(" "+map.size()+" ");
			((P)tableHolder).p(map);
			
			if(!selected.isEmpty()) ((V)tableHolder).v("selected",selected);
			previous = map;
			
		}
	}
	
	
	private boolean isSame(Map m1, Map m2)
	{
		if(m1==null && m2==null) return true;
		if(m1==null || m2==null) return false;
		if(m1.size() != m2.size()) return false;
		
		Iterator it = m1.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!m2.containsKey(key)) return false;
			if(!m1.get(key).equals(m2.get(key))) return false;
		}
		return true;
	}
	
	
	private Map cxToMap(Connection cx) throws Exception
	{
		Map map = (Map) findDbCounts.t(cx);
		Set dbSet = (Set) findDbSet.t(cx);
		
		Iterator it = dbSet.iterator();
		while(it.hasNext())
		{
			String dbName = (String) it.next();
			if(!map.containsKey(dbName)) map.put(dbName,0L);
		}
		return map;
	}
}