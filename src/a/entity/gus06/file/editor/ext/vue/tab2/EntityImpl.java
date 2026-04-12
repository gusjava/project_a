package a.entity.gus06.file.editor.ext.vue.tab2;

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
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, V, E {

	public String creationDate() {return "20260104";}
	
	public static final Font FONT = new Font("Courier",Font.PLAIN,14);

	private Service engine;
	private Service custSplit;
	private Service editor;

	private JPanel panel;
	private JSplitPane split;
	private JButton button;
	
	private JTextArea area;
	private JTextArea comp;
	
	private Map root;
	private String script;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.vuejsparser1.script.engine");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		editor = Outside.service(this,"*gus06.data.viewer.object");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		area.setFont(FONT);
		
		split = new JSplitPane();
		split.setRightComponent(new JScrollPane(area));
		split.setLeftComponent((JComponent) editor.i());
		split.setDividerLocation(300);
		
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
	{}
	
	
	
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
			area.setText("");
			editor.p(null);
			
			Map m = (Map) engine.t(comp.getText());
			script = (String) m.get("script");
			root = (Map) m.get("scriptData");
			
			area.setText(script);
			editor.p(root);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	
	private void initComp(JTextArea comp1) throws Exception
	{
		if(comp!=null) throw new Exception("Comp already initialized");
		comp = comp1;
	}
}
