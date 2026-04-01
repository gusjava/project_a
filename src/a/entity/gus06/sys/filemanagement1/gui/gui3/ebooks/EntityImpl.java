package a.entity.gus06.sys.filemanagement1.gui.gui3.ebooks;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201102";}


	private Service tab;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	private Service gui6;
	private Service gui7;


	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_1.authors");
		gui2 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_2.subjects");
		gui3 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_3.titles");
		gui4 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_4.languages");
		gui5 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_5.publishers");
		gui6 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_6.isbn");
		gui7 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3_7.name0");
		
		tab.v("Authors",gui1.i());
		tab.v("Subjects",gui2.i());
		tab.v("Titles",gui3.i());
		tab.v("Languages",gui4.i());
		tab.v("Publishers",gui5.i());
		tab.v("ISBN",gui6.i());
		tab.v("File names",gui7.i());
	}
	
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
		gui4.p(obj);
		gui5.p(obj);
		gui6.p(obj);
		gui7.p(obj);
	}
}