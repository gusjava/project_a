package a.entity.gus06.y.openrouter.gui.main;

import a.framework.*;
import java.io.File;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251218";}

	public static final String PERSIST_PROMPT = "persist.prompt";
	

	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	private Service tab;
	private Service persist;
	private Service buildEngine;
	private Service labelModel;
	private Service labelCredits;
	
	private JPanel panel;
	private Map map;
	private Object engine;
	
	
	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.y.openrouter.gui1ask");
		gui2 = Outside.service(this,"*gus06.y.openrouter.gui2translate");
		gui3 = Outside.service(this,"*gus06.y.openrouter.gui3models");
		gui4 = Outside.service(this,"*gus06.y.openrouter.gui4prompt");
		gui5 = Outside.service(this,"*gus06.y.openrouter.gui5detail");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text.tomap");
		buildEngine = Outside.service(this,"gus06.y.openrouter.buildengine");
		labelModel = Outside.service(this,"*gus06.y.openrouter.labelmodel");
		labelCredits = Outside.service(this,"*gus06.y.openrouter.labelcredits");
		
		tab.v("Ask", gui1);
		tab.v("Translate", gui2);
		tab.v("Prompt", gui4);
		tab.v("Models", gui3);
		tab.v("Detail", gui5);
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add((JComponent) labelModel.i(), BorderLayout.WEST);
		panelBottom.add((JComponent) labelCredits.i(), BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(), BorderLayout.CENTER);
		panel.add(panelBottom, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		engine = buildEngine.t(map);
		
		gui1.v("engine", engine);
		gui2.v("engine", engine);
		gui3.v("engine", engine);
		gui4.v("engine", engine);
		gui5.v("engine", engine);
		labelModel.v("engine", engine);
		labelCredits.v("engine", engine);
		
		Object areaInput = gui4.r("areaInput");
		persist.p(new Object[]{areaInput, map, PERSIST_PROMPT});
		
		((E) engine).e();
	}
}
