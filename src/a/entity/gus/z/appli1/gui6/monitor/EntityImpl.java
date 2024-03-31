package a.entity.gus.z.appli1.gui6.monitor;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231116";}

	private Service tabPersist;
	private Service tabHolder;

	private Service logViewer;
	private Service mainViewer;
	private Service uniqueViewer;
	private Service loadedViewer;
	private Service errorViewer;
	private Service envPropViewer;
	private Service classPathViewer;
	private Service classPathEViewer;

	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");

		logViewer = Outside.service(this,"*gus.y.logger1.view1");
		mainViewer = Outside.service(this,"*gus.y.dataview1.map.main");
		uniqueViewer = Outside.service(this,"*gus.y.dataview1.map.main.uniqueentity");
		loadedViewer = Outside.service(this,"*gus.y.dataview1.map.main.loadedentity");
		errorViewer = Outside.service(this,"*gus.y.errors1.gui1");
		envPropViewer = Outside.service(this,"*gus.y.jre1.system.envprop.gui1");
		classPathViewer = Outside.service(this,"*gus.y.jre1.prop.classpath.view1");
		classPathEViewer = Outside.service(this,"*gus.y.entityclass1.cl.classpath.view1");
		
		tabHolder.v("GUI_logs#logs", logViewer);
		tabHolder.v("GUI_main#main", mainViewer);
		tabHolder.v("ELEMENT_entity_entityloaded#loaded", loadedViewer);
		tabHolder.v("ELEMENT_entity_unique#unique", uniqueViewer);
		tabHolder.v("UTIL_error#errors", errorViewer);
		tabHolder.v("FILE_properties#prop/env", envPropViewer);
		tabHolder.v("GUI_classpath_appli#appli classpath", classPathViewer);
		tabHolder.v("GUI_classpath_entity#entity classpath", classPathEViewer);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
