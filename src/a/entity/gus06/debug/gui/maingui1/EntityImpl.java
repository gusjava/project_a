package a.entity.gus06.debug.gui.maingui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150305";}


	private Service debugGui;
	private Service unique;
	private String mainEntity;
	
	
	private JTabbedPane tab;


	

	public EntityImpl() throws Exception
	{
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		unique = Outside.service(this,"entityunique");
		mainEntity = (String) Outside.resource(this,"property#app.maingui1");
		
		I mainGui = (I) unique.t(mainEntity);
		
		tab = new JTabbedPane();
		tab.addTab("Main",(JComponent) mainGui.i());
		tab.addTab("Debug",(JComponent) debugGui.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
}
