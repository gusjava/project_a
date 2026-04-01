package a.entity.gus06.sys.filemanagement1.gui.gui2.movies;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201014";}


	private Service tab;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	private Service gui6;


	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_1.actors");
		gui2 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_2.directors");
		gui3 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_3.genres");
		gui4 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_4.nationalities");
		gui5 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_5.productionyears");
		gui6 = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2_6.title");
		
		tab.v("Actors",gui1.i());
		tab.v("Directors",gui2.i());
		tab.v("Genres",gui3.i());
		tab.v("Nationalities",gui4.i());
		tab.v("Production years",gui5.i());
		tab.v("Titles",gui6.i());
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
	}
}