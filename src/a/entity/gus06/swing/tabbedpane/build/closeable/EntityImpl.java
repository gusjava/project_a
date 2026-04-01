package a.entity.gus06.swing.tabbedpane.build.closeable;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class EntityImpl extends S1 implements Entity, I, R, V, F, ChangeListener {

	public String creationDate() {return "20141203";}



	private Service buildDraggable;
	private Service buildTab;
	private Service buildCTab;
	
	private JTabbedPane tabbedPane;
	
	private F acceptClose;
	private P performClose;
	
	private JComponent removedComp;
	private int removedIndex = -1;
	private int invertedIndex0 = -1;
	private int invertedIndex1 = -1;
	
	
	


	public EntityImpl() throws Exception
	{
		buildDraggable = Outside.service(this,"*gus06.swing.tabbedpane.build.draggable");
		
		buildTab = Outside.service(this,"gus06.swing.tabbedpane.build.closeable.buildtab");
		buildCTab = Outside.service(this,"gus06.swing.tabbedpane.build.closeable.buildtab.closeable");
		
		tabbedPane = (JTabbedPane) buildDraggable.i();
		((S) tabbedPane).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {performInversion();}
		});
		
		tabbedPane.addChangeListener(this);
	}
	
	
	public boolean f(Object obj) throws Exception
	{return tabbedPane.indexOfComponent((JComponent) obj)!=-1;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	
	
	
	private void performInversion()
	{
		try
		{
			R r = (R) tabbedPane;
			invertedIndex0 = int_(r.r("index0"));
			invertedIndex1 = int_(r.r("index1"));
			tabsInverted();
		}
		catch(Exception e)
		{Outside.err(this,"performInversion()",e);}
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("removedComp")) return removedComp;
		if(key.equals("removedIndex")) return ""+removedIndex;
		
		if(key.equals("selectedComp")) return getSelectedComp();
		if(key.equals("selectedIndex")) return getSelectedIndex();
		
		if(key.equals("invertedIndex0")) return ""+invertedIndex0;
		if(key.equals("invertedIndex1")) return ""+invertedIndex1;
		
		if(key.equals("keys")) return new String[]{
			"removedComp","removedIndex",
			"selectedComp","selectedIndex",
			"invertedIndex0","invertedIndex1"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("acceptClose"))	{acceptClose = (F) obj;return;}
		if(key.equals("performClose"))	{performClose = (P) obj;return;}
		if(key.equals("addCTab"))	{addCTab((Object[]) obj);return;}
		if(key.equals("addTab")){addTab((Object[]) obj);return;}
		if(key.equals("removeTab"))	{removeTab((JComponent) obj,false);return;}
		if(key.equals("selectTab"))	{selectTab((JComponent) obj);return;}
		if(key.equals("removeAll"))	{removeAll();return;}
		
		JComponent c = toComp(obj);
		if(key.startsWith("*")) addCTab(key.substring(1),c);
		else addTab(key,c);
	}
	
	
	
	public void stateChanged(ChangeEvent e)
	{tabSelected();}
	
	
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		throw new Exception("Invalid type: "+obj.getClass().getName());
	}
	

	private JComponent toCTab(Object obj) throws Exception
	{return (JComponent) buildCTab.t(obj);}
	
	private JComponent toTab(Object obj) throws Exception
	{return (JComponent) buildTab.t(obj);}
	
	
	
	
	
	
	
	private void addTab(Object[] t) throws Exception
	{
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		addTab(t[0],toComp(t[1]));
	}
	
	private void addCTab(Object[] t) throws Exception
	{
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		addCTab(t[0],toComp(t[1]));
	}
	
	
	
	
	
	
	
	private void addTab(Object dObj, JComponent c) throws Exception
	{
		if(hasComponent(c)) return;
		JComponent t = toTab(dObj);
		addTab_(t,c);
	}
	
	private void addCTab(Object dObj, JComponent c) throws Exception
	{
		if(hasComponent(c)) return;
		JComponent t = toCTab(dObj);
		((S)t).addActionListener(new TabRemover(c));
		addTab_(t,c);
	}
	
	
	
	
	
	private void addTab_(JComponent t, JComponent c)
	{
		tabbedPane.addTab("",c);
		int index = tabbedPane.getTabCount()-1;
		tabbedPane.setTabComponentAt(index,t);
	}
	
	
	
	
	
	
	private class TabRemover implements ActionListener 
	{
		private JComponent c;
		public TabRemover(JComponent c)
		{this.c = c;}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("closed()"))
			removeTab(c,true);
		}
	}
	
	
	
	private void removeTab(JComponent c, boolean ask)
	{
		try
		{
			int index = tabbedPane.indexOfComponent(c);
			if(index==-1) return;
			if(ask && acceptClose!=null && !acceptClose.f(c)) return;
			
			if(performClose!=null) performClose.p(c);
			if(hasComponent(c)) tabbedPane.remove(index);
			
			removedIndex = index;
			removedComp = c;
			tabRemoved();
		}
		catch(Exception e)
		{Outside.err(this,"removeTab(JComponent,boolean)",e);}
	}
	
	
	
	
	private void selectTab(JComponent c)
	{
		if(equals(c,getSelectedComp())) return;
		tabbedPane.setSelectedComponent(c);
		tabSelected();
	}
	
	
	private void removeAll()
	{tabbedPane.removeAll();}
	
	
	private boolean hasComponent(Component c)
	{return tabbedPane.indexOfComponent(c)>=0;}
	
	
	
	private JComponent getSelectedComp()
	{return (JComponent) tabbedPane.getSelectedComponent();}
	
	private int getSelectedIndex()
	{return tabbedPane.getSelectedIndex();}
	
	
	
	
	
	
	private void tabRemoved()
	{send(this,"tabRemoved()");}
	
	private void tabsInverted()
	{send(this,"tabsInverted()");}
	
	private void tabSelected()
	{send(this,"tabSelected()");}
	
	
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
	
	private int int_(Object obj)
	{return Integer.parseInt((String) obj);}
}
