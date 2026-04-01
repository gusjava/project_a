package a.entity.gus06.appli.ideamanager.gui.gui1;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.JTree;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150425";}


	private Service tagBrowser;
	private Service persister;
	private Service treeRenderer;
	private Service expandCollapse;
	private Service buildPanel;
	private Service backuper;
	
	private JTextArea textArea;
	private JTree browser;
	private JSplitPane split;
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		tagBrowser = Outside.service(this,"gus06.swing.textarea.buildtagbrowser");
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		treeRenderer = Outside.service(this,"gus06.swing.tree.cust.renderer.display1");
		expandCollapse = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons");
		buildPanel = Outside.service(this,"gus06.swing.textarea.buildpanel1");
		backuper = Outside.service(this,"gus06.swing.textcomp.cust.firstedit.backup");
		
		textArea = new JTextArea();
		persister.v(getClass().getName()+"_textArea",textArea);
		backuper.p(textArea);
		
		browser = (JTree) tagBrowser.t(textArea);
		panel = (JPanel) buildPanel.t(textArea);
		
		treeRenderer.p(browser);
		expandCollapse.p(browser);

		split = new JSplitPane();
		split.setDividerLocation(200);
		split.setLeftComponent(new JScrollPane(browser));
		split.setRightComponent(panel);
	}
	
	
	public Object i() throws Exception
	{return split;}
}
