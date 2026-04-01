package a.entity.gus06.appli.gusclient1.gui.space.projects.analyzer;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150311";}


	private Service tabHolder;
	private Service tabPersist;
	
	private Service analyzerProp;
	private Service analyzerMapping;
	private Service analyzerPath;
	private Service analyzerEntities;
	
	

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		analyzerProp = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.analyzer.prop");
		analyzerMapping = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.analyzer.mapping");
		analyzerPath = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.analyzer.path");
		analyzerEntities = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.analyzer.entities");
		
		tabHolder.v("Properties",analyzerProp.i());
		tabHolder.v("Mapping",analyzerMapping.i());
		tabHolder.v("Path",analyzerPath.i());
		tabHolder.v("Entities",analyzerEntities.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
