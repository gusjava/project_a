package a.entity.gus06.debug.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140919";}


	private Service sysoutViewer;
	private Service syserrViewer;
	private Service syspropViewer;
	
	private Service threadViewer;
	private Service exceptionViewer;
	private Service windowsViewer;
	private Service libraryViewer;
	private Service jvmMemViewer;
	private Service mainViewer;
	private Service infoViewer;
	
	private Service appJarViewer;
	private Service callTester;
	private Service errGenerator;
	private Service exitButton;
	
	private JTabbedPane tab;

	public EntityImpl() throws Exception
	{
		sysoutViewer = Outside.service(this,"*gus06.system.out.gui.viewer");
		syserrViewer = Outside.service(this,"*gus06.system.err.gui.viewer");
		syspropViewer = Outside.service(this,"*gus06.system.prop.gui.viewer");
		
		threadViewer = Outside.service(this,"*gus06.thread.gui.viewer");
		exceptionViewer = Outside.service(this,"*gus06.exception.gui.viewer");
		windowsViewer = Outside.service(this,"*gus06.awt.window.debug.gui");
		libraryViewer = Outside.service(this,"*gus06.app.library.gui.displaygui");
		jvmMemViewer = Outside.service(this,"*gus06.watching.jvm.memory.gui1");
		mainViewer = Outside.service(this,"*gus06.app.main.gui.viewer");
		infoViewer = Outside.service(this,"*gus06.app.info.gui.viewer");
		
		appJarViewer = Outside.service(this,"*gus06.app.jarfile.gui.viewer");
		callTester = Outside.service(this,"*gus06.debug.gui.calltester");
		errGenerator = Outside.service(this,"*gus06.debug.gui.errgenerator");
		exitButton = Outside.service(this,"gus06.swing.button.build.exitbutton");
		
		
		tab = new JTabbedPane();
		
		tab.addTab("System.out",(JComponent) sysoutViewer.i());
		tab.addTab("System.err",(JComponent) syserrViewer.i());
		tab.addTab("System.prop",(JComponent) syspropViewer.i());
		
		tab.addTab("Thread",(JComponent) threadViewer.i());
		tab.addTab("Exceptions",(JComponent) exceptionViewer.i());
		tab.addTab("Windows",(JComponent) windowsViewer.i());
		tab.addTab("Libraries",(JComponent) libraryViewer.i());
		tab.addTab("JVM memory",(JComponent) jvmMemViewer.i());
		tab.addTab("Main map",(JComponent) mainViewer.i());
		tab.addTab("App infos",(JComponent) infoViewer.i());
		tab.addTab("App jar",(JComponent) appJarViewer.i());
		tab.addTab("Call tester",(JComponent) callTester.i());
		tab.addTab("Err generator",(JComponent) errGenerator.i());
		tab.addTab("Exit",(JComponent) exitButton.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
}