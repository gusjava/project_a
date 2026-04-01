package a.entity.gus06.appli.entitytester.gui.stringtransform.panel;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20150920";}


	private Service persist;


	private JPanel panel;
	
	private JTextArea area_input;
	private JTextArea area_output;
	
	
	

	public EntityImpl() throws Exception
	{
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		area_input = new JTextArea();
		area_input.setMargin(new Insets(3,3,3,3));
		
		area_output = new JTextArea();
		area_output.setMargin(new Insets(3,3,3,3));
		area_output.setEditable(false);
		
		panel = new JPanel(new GridLayout(2,1,5,5));
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel.add(new JScrollPane(area_input));
		panel.add(new JScrollPane(area_output));
		
		persist.v(getClass().getName()+"_input",area_input);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		T t = (T) obj;
		String s = area_input.getText();
		s = (String) t.t(s);
		area_output.setText(s);
	}
	
	public void e() throws Exception
	{
		String s = area_output.getText();
		area_input.setText(s);
	}
}
