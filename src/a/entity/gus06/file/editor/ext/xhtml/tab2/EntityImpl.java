package a.entity.gus06.file.editor.ext.xhtml.tab2;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import java.util.Map;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, V, E {

	public String creationDate() {return "20200103";}

	public static final String K_CLOSINGTAG = "closingtag";
	public static final String K_TEXTSTART = "textstart";
	public static final String K_TEXTEND = "textend";
	
	public static final Font FONT = new Font("Courier",Font.PLAIN,14);

	private Service parser;
	private Service custSplit;
	private Service rmEmptyText;
	private Service treeHolder;
	private Service indent;

	private JPanel panel;
	private JSplitPane split;
	private JButton button;
	private JTree tree;
	
	private JTextArea area;
	private JTextArea comp;
	
	private Map rootTag;
	private Map selectedTag;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.xhtmlparser1.engine");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		rmEmptyText = Outside.service(this,"gus06.sys.xhtmlparser1.tool.rm.emptytext");
		treeHolder = Outside.service(this,"*gus06.sys.xhtmlparser1.gui.tree.holder");
		indent = Outside.service(this,"gus06.sys.xhtmlparser1.indentation");
		
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setBackground(new Color(255,204,153));
		area.setEditable(false);
		area.setFont(FONT);
		
		tree = (JTree) treeHolder.i();
		treeHolder.addActionListener(this);
		
		split = new JSplitPane();
		split.setRightComponent(new JScrollPane(area));
		split.setLeftComponent(new JScrollPane(tree));
		
		custSplit.p(split);
		
		button = new JButton("Refresh");
		button.setEnabled(false);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	

	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("comp")) {initComp((JTextArea) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		button.setEnabled(file!=null);
		refresh();
	}
	
	
	public void e() throws Exception
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			treeHolder.p(null);
			setText(null);
			
			rootTag = (Map) parser.t(comp.getText());
			rmEmptyText.p(rootTag);
			selectedTag = null;
			
			treeHolder.p(rootTag);
			setText(rootTag);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	public void selected()
	{
		try
		{
			selectedTag = (Map) treeHolder.g();
			if(selectedTag!=null) setText(selectedTag);
			else setText(rootTag);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
	
	
	
	
	private void initComp(JTextArea comp1) throws Exception
	{
		if(comp!=null) throw new Exception("Comp already initialized");
		comp = comp1;
	}
	
	
	private void setText(Map tag) throws Exception
	{
		String text = tagToIndentedString(tag);
		area.setText(text);
		area.setCaretPosition(0);
	}
	
	
	
	private String tagToIndentedString(Map tag) throws Exception
	{return (String) indent.t(tag);}
	
	
	
	private String tagToRealString(Map tag)
	{
		int start = (int) tag.get(K_TEXTSTART);
		int end = findEnd(tag);
		return comp.getText().substring(start,end);
	}
	
	
	private int findEnd(Map tag)
	{
		if(tag.containsKey(K_CLOSINGTAG))
		{
			Map closingTag = (Map) tag.get(K_CLOSINGTAG);
			return (int) closingTag.get(K_TEXTEND);
		}
		return (int) tag.get(K_TEXTEND);
	}

}