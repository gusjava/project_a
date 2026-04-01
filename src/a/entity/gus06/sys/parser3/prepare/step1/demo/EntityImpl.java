package a.entity.gus06.sys.parser3.prepare.step1.demo;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I, G {

	public String creationDate() {return "20151026";}


	private Service engine;
	private Service persister;
	private Service viewer;

	private JButton button;
	private JTextArea area;
	
	private JSplitPane split;
	
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.parser3.prepare.step1");
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		viewer = Outside.service(this,"*gus06.data.viewer.g.output");
		
		button = new JButton("Perform");
		button.addActionListener(this);
		
		area = new JTextArea();
		persist("area",area);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		split = new JSplitPane();
		split.setLeftComponent(panel);
		split.setRightComponent((JComponent) viewer.i());
		
		split.setDividerLocation(300);
	}
	
	
	private void persist(String key, Object obj) throws Exception
	{persister.v(getClass().getName()+"_"+key,obj);}
	
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public Object g() throws Exception
	{
		String text = area.getText();
		return engine.t(text);
	}



	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try{viewer.p(this);}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}
