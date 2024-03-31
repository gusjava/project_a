package a.entity.gus.z.appli1.gui4.tools;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231116";}

	private Service tabPersist;
	private Service tabHolder;
	
	private Service entityGui;
	private Service docGui;
	private Service importGui;

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		
		entityGui = Outside.service(this,"*gus.y.entitygenerator1.multi.gui");
		docGui = Outside.service(this,"*gus.y.docgenerator1.gui");
		importGui = Outside.service(this,"*gus.y.entityimporter1.maingui");
		
		tabHolder.v("GUI_generator_entity#entity generator", entityGui);
		tabHolder.v("GUI_generator_doc#doc generator", docGui);
		tabHolder.v("GUI_import#gus06 importer", importGui);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
