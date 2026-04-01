package a.entity.gus06.data.viewer.t.stringtransform.tester1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20160507";}


	private JPanel panel;
	
	private JTextArea area_input;
	private JTextArea area_output;
	
	private JButton btn_perform;
	private JButton btn_outputToInput;
	
	
	private T data;



	public EntityImpl() throws Exception
	{
		area_input = new JTextArea();
		area_input.setMargin(new Insets(3,3,3,3));
		
		area_output = new JTextArea();
		area_output.setMargin(new Insets(3,3,3,3));
		area_output.setEditable(false);
		
		JPanel panel1 = new JPanel(new GridLayout(2,1,5,5));
		panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel1.add(new JScrollPane(area_input));
		panel1.add(new JScrollPane(area_output));
		
		
		btn_perform = new JButton("Perform");
		btn_perform.addActionListener(this);
		
		btn_outputToInput = new JButton("Ouput to input");
		btn_outputToInput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{outputToInput();}
		});
		
		JPanel p_buttons = new JPanel(new GridLayout(1,2));
		p_buttons.add(btn_perform);
		p_buttons.add(btn_outputToInput);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel1,BorderLayout.CENTER); 
		panel.add(p_buttons,BorderLayout.SOUTH);
	}
	
	
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (T) obj;
		area_input.setText("");
		area_output.setText("");
	}
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try
		{
			if(data==null) return;
			String s = area_input.getText();
			area_output.setText((String) data.t(s));
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private void outputToInput()
	{
		try
		{
			String s = area_output.getText();
			area_input.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"outputToInput()",e);}
	}
}
