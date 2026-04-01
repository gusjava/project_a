package a.entity.gus06.sys.charanalyzer1.gui.maingui;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191112";}


	private Service gui1;
	private Service buildArea;
	private Service cust;
	
	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.sys.charanalyzer1.gui.gui1");
		buildArea = Outside.service(this,"gus06.swing.textarea.buildarea1");
		cust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		JTextArea area = (JTextArea) buildArea.i();
		gui1.p(area);
		
		split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setRightComponent(new JScrollPane(area));
		split.setLeftComponent((JComponent) gui1.i());
		
		cust.p(split);
	}
	
	
	public Object i() throws Exception
	{return split;}
}
