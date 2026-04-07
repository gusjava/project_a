package a.entity.gus06.jdbc.gui.cx1.db.list.updater;

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

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150622";}


	private Service threadManager;
	private Service findDbCounts;
	private Service findDbSet;
	private Service findCx;
	private Map map;


	public EntityImpl() throws Exception
	{
		threadManager = Outside.service(this,"gus.x.thread.wrapper1");
		findDbCounts = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb");
		findDbSet = Outside.service(this,"gus06.jdbc.generic.perform.find.dbset");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		map = new HashMap();
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object cxHolder = o[0];
		Object tableHolder = o[1];
		JLabel label = (JLabel) o[2];
		
		if(!map.containsKey(tableHolder))
			map.put(tableHolder,new UpdateHolder(cxHolder,tableHolder,label));
		
		UpdateHolder updateHolder = (UpdateHolder) map.get(tableHolder);
		updateHolder.e();
	}
	
	
	
	
	
	
	
	private Map perform(Object cxHolder, Object tableHolder, JLabel label, Map previous)
	{
		try
		{
			Connection cx = (Connection) findCx.t(cxHolder);
			if(cx==null) return previous;
			
			List selected = (List) ((G)tableHolder).g();
			Map map = cxToMap(cx);
			if(isSame(map,previous)) return previous;

			label.setText(" "+map.size()+" ");
			((P)tableHolder).p(map);
			
			if(!selected.isEmpty()) ((V)tableHolder).v("selected",selected);
			return map;
		}
		catch(Exception e)
		{Outside.err(this,"perform(Object,Object,JLabel,Vector)",e);}
		return previous;
	}
	
	
	
	
	
	
	
	private class UpdateHolder implements Runnable, E
	{
		private Object cxHolder;
		private Object tableHolder;
		private JLabel label;
		
		private Map previous;
		private Thread t;
		
		public UpdateHolder(Object cxHolder, Object tableHolder, JLabel label)
		{
			this.cxHolder = cxHolder;
			this.tableHolder = tableHolder;
			this.label = label;
		}
		
		public void e() throws Exception
		{
			if(t!=null && t.isAlive()) return;
			t = (Thread) threadManager.t(this);
			t.start();
		}
		
		public void run()
		{
			previous = perform(cxHolder,tableHolder,label,previous);
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