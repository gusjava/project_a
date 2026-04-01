package a.entity.gus06.appli.gusclient1.menu.gus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140811";}


	private Service custMenu;

	private Service compileAll;
	private Service rebuildApp;
	private Service testApp;
	private Service killTest;
	private Service extractAll;
	private Service sendCmd;
	private Service menu_errors;
	
	
	private JMenu menu;
	

	
	public EntityImpl() throws Exception
	{
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.gus.cust");
	
		compileAll = Outside.service(this,"gus06.appli.gusclient1.action.gus.compileall");
		rebuildApp = Outside.service(this,"gus06.appli.gusclient1.action.gus.rebuildapp");
		testApp = Outside.service(this,"gus06.appli.gusclient1.action.gus.testapp");
		killTest = Outside.service(this,"gus06.appli.gusclient1.action.gus.killtest");
		extractAll = Outside.service(this,"gus06.appli.gusclient1.action.gus.extractall");
		sendCmd = Outside.service(this,"gus06.appli.gusclient1.action.gus.send");
		menu_errors = Outside.service(this,"gus06.appli.gusclient1.menu.errors");
		
		menu = new JMenu("Gus");
		custMenu.p(menu);
		
		add(compileAll);
		add(rebuildApp);
		add(testApp);
		add(killTest);
		add(extractAll);
		add(sendCmd);
		
		menu.add((JComponent) menu_errors.i());
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
