package a.entity.gus06.appli.gusclient1.menu.space.projects;

import java.util.List;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140812";}

	public static final String SPACE = "projects";
	public static final String LINGKEY = "gusclient1_space_"+SPACE;


	
	private Service localize;
	private Service custMenu;
	
	private Service createProject;
	private Service selectProject;
	private Service nextProject;
	private Service previousProject;
	
	private Service renameProject;
	private Service duplicateProject;
	private Service removeProject;
	
	private Service importProject;
	private Service exportProject;
	
	private Service openRootDir;
	private Service deployProject;
	private Service testProject;
	private Service releaseProject;
	private Service deployTestProject;
	
	private Service templateMenu;
	
	private JMenu menu;
	


	public EntityImpl() throws Exception
	{
		localize = Outside.service(this,"gus06.ling.localize.manager");
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.space.cust");
		
		createProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.createproject");
		selectProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.selectproject");
		nextProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.nextproject");
		previousProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.previousproject");
		
		renameProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.renameproject");
		duplicateProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.duplicateproject");
		removeProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.removeproject");
		
		importProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.importproject");
		exportProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.exportproject");
		
		openRootDir = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.openrootdir");
		deployProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.deployproject");
		testProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.testproject");
		releaseProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.releaseproject");
		
		deployTestProject = Outside.service(this,"gus06.appli.gusclient1.action.space.projects.deploytestproject");
		
		templateMenu = Outside.service(this,"gus06.appli.gusclient1.menu.projecttemplate");
		
		
		menu = new JMenu("Projects");
		localize.v(LINGKEY,menu);
		custMenu.v(SPACE,menu);
		
		addAction(createProject);
		addAction(selectProject);
		addAction(nextProject);
		addAction(previousProject);
		
		menu.addSeparator();
		
		addAction(renameProject);
		addAction(duplicateProject);
		addAction(removeProject);
		
		menu.addSeparator();
		
		addAction(importProject);
		addAction(exportProject);
		
		menu.addSeparator();
		
		addAction(openRootDir);
		addAction(deployProject);
		addAction(testProject);
		addAction(releaseProject);
		
		menu.addSeparator();
		
		addAction(deployTestProject);
		
		menu.addSeparator();
		
		addMenu(templateMenu);
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
