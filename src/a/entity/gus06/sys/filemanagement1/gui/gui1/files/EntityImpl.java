package a.entity.gus06.sys.filemanagement1.gui.gui1.files;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201102";}


	private Service tab;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	
	private File root;
	private Object engine;
	
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_1.roots");
		gui2 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_2.explorer");
		gui3 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_3.search");
		gui4 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_4.analyze");
		gui5 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_5.local");
		
		tab.v("Roots",gui1.i());
		tab.v("Explorer",gui2.i());
		tab.v("Search",gui3.i());
		tab.v("Analyze",gui4.i());
		tab.v("Local",gui5.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
		
		gui1.p(engine);
		gui2.p(engine);
		gui3.p(engine);
		gui4.p(engine);
		gui5.p(engine);
	}
}