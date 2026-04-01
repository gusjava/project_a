package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.util.Objects;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250610";}
	
	private Service tab;
	
	private Service summaryGui;
	private Service handlingGui;
	private Service debugGui;
	private Service actionGui;
	private Service doubloonGui;
	
	private Object engine;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		actionGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.actions");
		debugGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.debug");
		doubloonGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons");
		handlingGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.handling");
		summaryGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.summary");
		
		tab.v("UTIL_summary#Summary",summaryGui.i());
		tab.v("UTIL_settings#Handling",handlingGui.i());
		tab.v("UTIL_debug#Debug",debugGui.i());
		tab.v("UTIL_running#Actions",actionGui.i());
		tab.v("UTIL_doubloons_search#Doubloons",doubloonGui.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		summaryGui.p(new Object[]{engine,selected});
		handlingGui.p(new Object[]{engine,selected});
		debugGui.p(new Object[]{engine,selected});
		doubloonGui.p(new Object[]{engine,selected});
		actionGui.p(new Object[]{engine,selected});
	}
	
	
	
	private void reset() throws Exception
	{
		engine = null;
		selected = null;
		
		summaryGui.p(null);
		handlingGui.p(null);
		debugGui.p(null);
		doubloonGui.p(null);
		actionGui.p(null);
	}
}