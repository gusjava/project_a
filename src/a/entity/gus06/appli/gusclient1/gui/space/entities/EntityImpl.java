package a.entity.gus06.appli.gusclient1.gui.space.entities;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140719";}

	private Service tabHolder;
	private Service tabPersist;
	
	private Service entityGui;
	private Service classFinder;
	private Service compileErr;
	private Service templateGui;
	private Service entityToolsGui;
	



	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
	
		entityGui = Outside.service(this,"*gus06.appli.gusclient1.gui.entity");
		classFinder = Outside.service(this,"*gus06.java.introspection.gui.gui1");
		compileErr = Outside.service(this,"*gus06.java.compiler.gui.errorpanel");
		templateGui = Outside.service(this,"*gus06.appli.gusclient1.gui.template");
		entityToolsGui = Outside.service(this,"*gus06.appli.gusclient1.gui.entitytools");
		
		tabHolder.v("entity#Entity manager",entityGui.i());
		tabHolder.v("UTIL_class#Class finder",classFinder.i());
		tabHolder.v("ACTION_compile#Compilation",compileErr.i());
		tabHolder.v("UTIL_template#Templates",templateGui.i());
		tabHolder.v("UTIL_tool#Entity tools",entityToolsGui.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
