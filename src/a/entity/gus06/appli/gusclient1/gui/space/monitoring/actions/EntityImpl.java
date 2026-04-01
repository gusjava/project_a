package a.entity.gus06.appli.gusclient1.gui.space.monitoring.actions;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140814";}


	private Service tabHolder;

	private Service serviceWatcher;
	private Service callTester;
	private Service errGenerator;
	private Service exitButton;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		serviceWatcher = Outside.service(this,"*gus06.maincust.service.wrapper1.gui");
		callTester = Outside.service(this,"*gus06.debug.gui.calltester");
		errGenerator = Outside.service(this,"*gus06.debug.gui.errgenerator");
		exitButton = Outside.service(this,"gus06.swing.button.build.exitbutton");
		
		tabHolder.v("Call tester",callTester.i());
		tabHolder.v("Service watcher",serviceWatcher.i());
		tabHolder.v("Err generator",errGenerator.i());
		tabHolder.v("Exit",exitButton.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}