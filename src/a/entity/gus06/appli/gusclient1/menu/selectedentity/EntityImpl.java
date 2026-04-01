package a.entity.gus06.appli.gusclient1.menu.selectedentity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140731";}


	public static final String KEY = "gusclient1_menu_selectedentity";


	private Service custMenu;
	private Service localize;

	private Service toClipboard1;
	private Service toClipboard2;
	private Service toClipboard3;
	private Service openDir;
	private Service rename;
	private Service remove;
	private Service duplicate;
	
	private Service compile;
	private Service deploy;
	private Service load;
	private Service view;
	private Service execute;
	
	private Service cdl;
	private Service cdlv;
	private Service cdle;

	private JMenu menu;

	
	public EntityImpl() throws Exception
	{
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.selectedentity.cust");
		localize = Outside.service(this,"gus06.ling.localize.manager");
	
		toClipboard1 = Outside.service(this,"gus06.appli.gusclient1.action.entity.toclipboard.name");
		toClipboard2 = Outside.service(this,"gus06.appli.gusclient1.action.entity.toclipboard.src");
		toClipboard3 = Outside.service(this,"gus06.appli.gusclient1.action.entity.toclipboard.srcmd5");
		openDir = Outside.service(this,"gus06.appli.gusclient1.action.entity.opendir");
		rename = Outside.service(this,"gus06.appli.gusclient1.action.entity.rename");
		remove = Outside.service(this,"gus06.appli.gusclient1.action.entity.remove");
		duplicate = Outside.service(this,"gus06.appli.gusclient1.action.entity.duplicate");
		
		compile = Outside.service(this,"gus06.appli.gusclient1.action.entity.compile");
		deploy = Outside.service(this,"gus06.appli.gusclient1.action.entity.deploy");
		load = Outside.service(this,"gus06.appli.gusclient1.action.entity.load");
		view = Outside.service(this,"gus06.appli.gusclient1.action.entity.view");
		execute = Outside.service(this,"gus06.appli.gusclient1.action.entity.execute");
		
		cdl = Outside.service(this,"gus06.appli.gusclient1.action.entity.full.cdl");
		cdlv = Outside.service(this,"gus06.appli.gusclient1.action.entity.full.cdlv");
		cdle = Outside.service(this,"gus06.appli.gusclient1.action.entity.full.cdle");
	
		
		menu = new JMenu("Selected entity");
		localize.v(KEY,menu);
		custMenu.p(menu);
		
		add(openDir);
	
		menu.addSeparator();
		
		add(toClipboard1);
		add(toClipboard2);
		add(toClipboard3);
	
		menu.addSeparator();
	
		add(rename);
		add(remove);
		add(duplicate);
	
		menu.addSeparator();
	
		add(compile);
		add(deploy);
		add(load);
		add(view);
		add(execute);
	
		menu.addSeparator();
		
		add(cdl);
		add(cdlv);
		add(cdle);
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
