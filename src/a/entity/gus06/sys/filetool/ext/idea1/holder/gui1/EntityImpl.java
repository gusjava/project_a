package a.entity.gus06.sys.filetool.ext.idea1.holder.gui1;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.JTree;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V, I, P, R {

	public String creationDate() {return "20160915";}
	
	public static final String DEFAULT_DELIM = "@";


	private Service textEditor;
	private Service tagBrowser;
	private Service treeRenderer;
	private Service expandCollapse;
	private Service autoSaver;
	private Service debugInfos;
	private Service editFromArea;
	
	private JTextComponent textComp;
	private JTree browser;
	private JSplitPane split;
	private JPanel panel;
	
	private File file;
	private String delim = DEFAULT_DELIM;
	private File iconDir;


	public EntityImpl() throws Exception
	{
		textEditor = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		tagBrowser = Outside.service(this,"gus06.swing.textarea.buildtagbrowser2");
		treeRenderer = Outside.service(this,"*gus06.sys.filetool.ext.idea1.holder.renderer");
		expandCollapse = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons");
		autoSaver = Outside.service(this,"*gus06.file.editor.holder.text.autosaver");
		debugInfos = Outside.service(this,"gus06.swing.textarea.buildtagbrowser2.debug.displayinfos");
		editFromArea = Outside.service(this,"gus06.swing.textarea.buildtagbrowser2.edit.fromarea");
		
		textComp = (JTextComponent) textEditor.r("comp");
		browser = (JTree) tagBrowser.t(new Object[]{textComp,delim});
		
		debugInfos.p(new Object[]{browser,textComp});
		editFromArea.p(new Object[]{browser,textComp});
		
		treeRenderer.p(browser);
		expandCollapse.p(browser);

		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);
		
		split.setLeftComponent(new JScrollPane(browser));
		split.setRightComponent((JComponent) textEditor.i());
		
		autoSaver.v("comp",textComp);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		boolean loaded = autoSaver.f(file);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delim"))
		{
			delim = (String) obj;
			((V) browser).v("delim",delim);
			return;
		}
		if(key.equals("iconDir"))
		{
			iconDir = (File) obj;
			treeRenderer.v("iconDir",iconDir);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return textComp;
		if(key.equals("file")) return file;
		if(key.equals("keys")) return new String[]{"comp","file"};
		
		throw new Exception("Unknown key: "+key);
	}
}