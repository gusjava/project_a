package a.entity.gus06.jdbc.gui.user1.list.updater;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}


	private Service threadManager;
	private Service findUser;
	private Map map;


	public EntityImpl() throws Exception
	{
		threadManager = Outside.service(this,"gus.x.thread.wrapper1");
		findUser = Outside.service(this,"gus06.jdbc.generic.perform.find.userset");
		map = new HashMap();
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object holder = o[0];
		JList list = (JList) o[1];
		JLabel label = (JLabel) o[2];
		
		if(!map.containsKey(holder))
			map.put(holder,new UpdateHolder(holder,list,label));
		
		UpdateHolder updateHolder = (UpdateHolder) map.get(holder);
		updateHolder.e();
	}
	
	
	
	
	
	
	
	private Vector perform(Object holder, JList list, JLabel label, Vector previous)
	{
		try
		{
			Connection cx = cx(holder);
			if(cx==null) return previous;
			
			Object selected = list.getSelectedValue();
			Vector vector = cxToVector(cx);
			if(isSame(vector,previous)) return previous;

			label.setText(" "+vector.size()+" ");
			list.setListData(vector);
			
			if(selected!=null) list.setSelectedValue(selected,true);
			return vector;
		}
		catch(Exception e)
		{Outside.err(this,"perform(Object,JList,JLabel,Vector)",e);}
		return previous;
	}
	
	
	
	
	
	
	
	private class UpdateHolder implements Runnable, E
	{
		private Object holder;
		private JList list;
		private JLabel label;
		
		private Vector previous;
		private Thread t;
		
		public UpdateHolder(Object holder, JList list, JLabel label)
		{
			this.holder = holder;
			this.list = list;
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
			previous = perform(holder,list,label,previous);
		}
	}
	
	
	
	

	
	
	private boolean isSame(Vector v1, Vector v2)
	{
		if(v1==null && v2==null) return true;
		if(v1==null || v2==null) return false;
		if(v1.size() != v2.size()) return false;
		
		for(int i=0;i<v1.size();i++)
			if(!v1.get(i).equals(v2.get(i))) return false;
		return true;
	}
	
	
	
	private Vector cxToVector(Connection cx) throws Exception
	{
		Set set = (Set) findUser.t(cx);
		Vector vec = set!=null?new Vector(set):new Vector();
		Collections.sort(vec);
		return vec;
	}
	
	
	private Connection cx(Object holder) throws Exception
	{return (Connection) ((G) holder).g();}
}