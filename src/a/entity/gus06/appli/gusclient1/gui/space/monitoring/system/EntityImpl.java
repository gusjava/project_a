package a.entity.gus06.appli.gusclient1.gui.space.monitoring.system;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, R {

	public String creationDate() {return "20140808";}


	private Service tabHolder;

	private Service sysoutViewer;
	private Service syserrViewer;
	private Service syspropViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		sysoutViewer = Outside.service(this,"*gus06.system.out.gui.viewer");
		syserrViewer = Outside.service(this,"*gus06.system.err.gui.viewer");
		syspropViewer = Outside.service(this,"*gus06.system.prop.gui.viewer");
		
		tabHolder.v("GUI_sysout#System.out",sysoutViewer.i());
		tabHolder.v("GUI_syserr#System.err",syserrViewer.i());
		tabHolder.v("GUI_sysprop#Prop / Env",syspropViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("sysoutViewer")) return sysoutViewer;
		if(key.equals("syserrViewer")) return syserrViewer;
		if(key.equals("syspropViewer")) return syspropViewer;
		
		if(key.equals("keys")) return new String[]{"sysoutViewer","syserrViewer","syspropViewer"};
		throw new Exception("Unknown key: "+key);
	}
}