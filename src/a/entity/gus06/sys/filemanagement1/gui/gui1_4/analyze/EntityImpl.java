package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191107";}


	private Service tab;
	private Service gui_search;
	private Service gui_generate;
	private Service gui_stats;
	private Service gui_doubloons2;
	private Service gui_export;
	private Service gui_fix;
	private Service gui_external;


	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui_search = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.search");
		gui_generate = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.generate");
		gui_stats = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.stats");
		gui_doubloons2 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.doubloons2");
		gui_export = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.export");
		gui_fix = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.fix");
		gui_external = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze.external");
		
		tab.v("Search",gui_search.i());
		tab.v("Generate",gui_generate.i());
		tab.v("Stats",gui_stats.i());
		tab.v("Doubloon dirs",gui_doubloons2.i());
		tab.v("Export",gui_export.i());
		tab.v("Fix",gui_fix.i());
		tab.v("Analyze external",gui_external.i());
	}
	
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		gui_search.p(obj);
		gui_generate.p(obj);
		gui_stats.p(obj);
		gui_doubloons2.p(obj);
		gui_export.p(obj);
		gui_fix.p(obj);
		gui_external.p(obj);
	}
}