package a.entity.gus06.sys.parser2.engine1.demo;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I, G {

	public String creationDate() {return "20150902";}


	private Service engine;
	private Service persister;
	private Service viewer;

	private JTextField field;
	private JTextArea area;
	
	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.parser2.engine1");
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		viewer = Outside.service(this,"*gus06.data.viewer.g.output");
		
		field = new JTextField();
		field.addActionListener(this);
		
		area = new JTextArea();
		
		persist("field",field);
		persist("area",area);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		
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
		String delim = field.getText();
		String text = area.getText();
			
		T parser = (T) engine.t(delim);
		return parser.t(text);
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
