package a.entity.gus06.file.editor.ext.vue.tab1;

import a.framework.*;
import java.awt.Color;
import javax.swing.text.JTextComponent;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20260104";}
	
	public static final Color BACKGROUND = new Color(153,255,204);

	
	private Service gui;
	private Service onClick;
	
	private JTextComponent comp;


	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.file.editor.ext.xhtml.tab1");
		onClick = Outside.service(this,"gus06.swing.label.cust3.onclick.execute");
		
		comp = (JTextComponent) gui.r("comp");
		comp.setBackground(BACKGROUND);
		
		if(comp instanceof JTextArea)
		((JTextArea) comp).setTabSize(4);
		
		JLabel labelStart = buildExecuteLabel("<", (E)this::goToStart);
		JLabel labelData = buildExecuteLabel("data", (E)this::goToData);
		JLabel labelMounted = buildExecuteLabel("mounted", (E)this::goToMounted);
		JLabel labelMethods = buildExecuteLabel("methods", (E)this::goToMethods);
		JLabel labelComputed = buildExecuteLabel("computed", (E)this::goToComputed);
		JLabel labelWatch = buildExecuteLabel("watch", (E)this::goToWatch);
		JLabel labelTemplate = buildExecuteLabel("template", (E)this::goToTemplate);
		JLabel labelEnd = buildExecuteLabel(">", (E)this::goToEnd);
		
		JToolBar bar = (JToolBar) gui.r("bar1");
		
		bar.addSeparator();
		bar.add(labelStart);
		bar.addSeparator();
		bar.add(labelData);
		bar.addSeparator();
		bar.add(labelMounted);
		bar.addSeparator();
		bar.add(labelMethods);
		bar.addSeparator();
		bar.add(labelComputed);
		bar.addSeparator();
		bar.add(labelWatch);
		bar.addSeparator();
		bar.add(labelTemplate);
		bar.addSeparator();
		bar.add(labelEnd);
		bar.addSeparator();
	}
	
	
	private JLabel buildExecuteLabel(String text, E execute) throws Exception
	{
		JLabel label = new JLabel(text);
		onClick.p(new Object[]{label, execute});
		return label;
	}
	
	
	public Object i() throws Exception
	{return gui.i();}
	
	public Object g() throws Exception
	{return gui.g();}
	
	public Object r(String key) throws Exception
	{return gui.r(key);}
	
	public void v(String key, Object obj) throws Exception
	{gui.v(key, obj);}
	
	public void p(Object obj) throws Exception
	{gui.p(obj);}
	
	
	
	
	
	private void goToStart() throws Exception
	{
		comp.setCaretPosition(0);
	}
	
	private void goToEnd() throws Exception
	{
		String text = comp.getText();
		comp.setCaretPosition(text.length());
	}
	
	private void goToData()
	{
		try{browseTo("data()");}
		catch(Exception e)
		{Outside.err(this,"goToData()",e);}
	}
	
	private void goToMounted()
	{
		try{browseTo("mounted()");}
		catch(Exception e)
		{Outside.err(this,"goToMounted()",e);}
	}
	
	private void goToMethods()
	{
		try{browseTo("methods:");}
		catch(Exception e)
		{Outside.err(this,"goToMethods()",e);}
	}
	
	private void goToComputed()
	{
		try{browseTo("computed:");}
		catch(Exception e)
		{Outside.err(this,"goToComputed()",e);}
	}
	
	private void goToWatch()
	{
		try{browseTo("watch:");}
		catch(Exception e)
		{Outside.err(this,"goToWatch()",e);}
	}
	
	private void goToTemplate()
	{
		try{browseTo("<template");}
		catch(Exception e)
		{Outside.err(this,"goToTemplate()",e);}
	}
	
	private void browseTo(String anchor) throws Exception
	{
		String text = comp.getText();
		int pos = text.indexOf(anchor);
		if(pos!=-1) browseTo(text, pos);
	}
	
	private void browseTo(String text, int pos) throws Exception
	{
		comp.setCaretPosition(text.length());
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {comp.setCaretPosition(pos);}
		});
	}
}
