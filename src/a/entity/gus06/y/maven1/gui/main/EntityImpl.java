package a.entity.gus06.y.maven1.gui.main;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20251217";}

	private Service engineBuilder;
	private Service tab;
	private Service persist;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	
	private JPanel panel;
	private Object engine;

	public EntityImpl() throws Exception
	{
		engineBuilder = Outside.service(this,"gus06.y.maven1.buildengine");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text.tomap");
		gui1 = Outside.service(this,"*gus06.y.maven1.gui1");
		gui2 = Outside.service(this,"*gus06.y.maven1.gui2");
		gui3 = Outside.service(this,"*gus06.y.maven1.gui3");
		
		tab.v("Browse", gui1);
		tab.v("Query", gui2);
		tab.v("JARs", gui3);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(), BorderLayout.CENTER);
	}
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		engine = engineBuilder.t(map);
		
		gui1.v("engine", engine);
		gui2.v("engine", engine);
		gui3.v("engine", engine);
		
		((E) engine).e();
	}
	
	public Object i() throws Exception
	{return panel;}
}
