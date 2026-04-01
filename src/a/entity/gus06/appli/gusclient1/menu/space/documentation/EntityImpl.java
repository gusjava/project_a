package a.entity.gus06.appli.gusclient1.menu.space.documentation;

import java.util.List;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140813";}

	public static final String SPACE = "documentation";
	public static final String LINGKEY = "gusclient1_space_"+SPACE;


	
	private Service localize;
	private Service custMenu;
	
	private Service extractFramework;
	private Service extractManager;
	private Service extractResources;
	private Service extractEntities;
	
	private Service extractAll;
	
	private JMenu menu;
	

	public EntityImpl() throws Exception
	{
		localize = Outside.service(this,"gus06.ling.localize.manager");
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.space.cust");
		
		extractFramework = Outside.service(this,"gus06.appli.gusclient1.action.space.documentation.extract.framework");
		extractManager = Outside.service(this,"gus06.appli.gusclient1.action.space.documentation.extract.manager");
		extractResources = Outside.service(this,"gus06.appli.gusclient1.action.space.documentation.extract.resources");
		extractEntities = Outside.service(this,"gus06.appli.gusclient1.action.space.documentation.extract.entities");
		
		extractAll = Outside.service(this,"gus06.appli.gusclient1.action.space.documentation.extract.all");
				
		menu = new JMenu("Documentation");
		localize.v(LINGKEY,menu);
		custMenu.v(SPACE,menu);
		
		addAction(extractFramework);
		addAction(extractManager);
		addAction(extractResources);
		addAction(extractEntities);
		
		menu.addSeparator();
		
		addAction(extractAll);
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	private void addMenu(Service s) throws Exception
	{
		JMenu m = (JMenu) s.i();
		if(m!=null) menu.add(m);
	}
	
	
	public void addAction(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
