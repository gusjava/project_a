package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file;

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
	
	public static final String KEY_MD5 = "md5";

	private Service tab;
	private Service formatProp;
	
	private Service summaryGui;
	private Service infosGui;
	private Service handlingGui;
	private Service debugGui;
	private Service actionGui;
	private Service doubloonGui;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		formatProp = Outside.service(this,"gus06.sys.filemanagement1.tool.prop.format.map");
		
		summaryGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.summary");
		infosGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.infos");
		handlingGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.handling");
		debugGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.debug");
		actionGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.actions");
		doubloonGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.doubloons");
		
		tab.v("UTIL_summary#Summary",summaryGui.i());
		tab.v("UTIL_infos#Infos",infosGui.i());
		tab.v("UTIL_settings#Handling",handlingGui.i());
		tab.v("UTIL_debug#Debug",debugGui.i());
		tab.v("UTIL_running#Actions",actionGui.i());
		tab.v("UTIL_doubloons#Doubloons",doubloonGui.i());
		
		handlingGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{reload();}
		});
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		refreshGui();
	}
	
	
	
	private void reset() throws Exception
	{
		engine = null;
		selected = null;
		
		summaryGui.p(null);
		infosGui.p(null);
		handlingGui.p(null);
		debugGui.p(null);
		doubloonGui.p(null);
		actionGui.p(null);
	}
	
	
	private void refreshGui() throws Exception
	{
		summaryGui.p(new Object[]{engine,selected,prop});
		infosGui.p(new Object[]{engine,selected,prop});
		handlingGui.p(new Object[]{engine,selected,prop});
		debugGui.p(new Object[]{engine,selected,prop});
		doubloonGui.p(new Object[]{engine,selected,prop});
		actionGui.p(new Object[]{engine,selected,prop});
	}
	
	
	
	
	private void reload()
	{
		try
		{
			String md5 = (String) selected.get(KEY_MD5);
			prop = (Map) formatProp.t(((R)engine).r("prop:"+md5));
			
			summaryGui.p(new Object[]{engine,selected,prop});
			infosGui.p(new Object[]{engine,selected,prop});
			handlingGui.p(new Object[]{engine,selected,prop});
			debugGui.p(new Object[]{engine,selected,prop});
			doubloonGui.p(new Object[]{engine,selected,prop});
			actionGui.p(new Object[]{engine,selected,prop});
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	
	private void checkSame(String key) throws Exception
	{
		if(prop==null) return;
		if(!prop.containsKey(key)) throw new Exception("Key not found inside prop: "+key);
		if(!selected.containsKey(key)) throw new Exception("Key not found inside Selected: "+key);
		
		String v1 = ""+prop.get(key);
		String v2 = ""+selected.get(key);
		if(!Objects.equals(v1,v2)) throw new Exception("Values are not the same for key "+key+": "+v1+" & "+v2);
	}
}