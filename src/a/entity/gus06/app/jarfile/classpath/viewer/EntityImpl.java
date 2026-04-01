package a.entity.gus06.app.jarfile.classpath.viewer;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140829";}


	private Service srcPanel;
	private Service docPanel;
	private Service classLabel;
	private Service splitCust;
	
	private JPanel panel;
	private JSplitPane split;
	
	private String classPath;
	
	
	
	public EntityImpl() throws Exception
	{
		srcPanel = Outside.service(this,"*gus06.app.jarfile.classpath.viewer.panel.src");
		docPanel = Outside.service(this,"*gus06.app.jarfile.classpath.viewer.panel.doc");
		classLabel = Outside.service(this,"*gus06.app.jarfile.classpath.label");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitCust.p(split);
		
		split.setLeftComponent((JComponent) srcPanel.i());
		split.setRightComponent((JComponent) docPanel.i());
		
		split.setDividerLocation(250);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) classLabel.i(),BorderLayout.NORTH);
		panel.add(split,BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		classPath = (String) obj;
		
		classLabel.p(classPath);
		srcPanel.p(classPath);
		docPanel.p(classPath);
	}
}
