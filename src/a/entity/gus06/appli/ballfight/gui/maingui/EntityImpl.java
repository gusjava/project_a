package a.entity.gus06.appli.ballfight.gui.maingui;

import a.framework.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20150216";}
	
	public static final int INIT_NUMBER = 200;
	public static final int INIT_BALLSIZE = 20;


	private Service persist;
	
	private ScreenJPanel screen;
	private JButton button;
	private JPanel panel;
	
	private JTextField field_number;
	private JTextField field_size;



	public EntityImpl() throws Exception
	{
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		screen = new ScreenJPanel();
		
		button = new JButton("D�marrer"); 
		button.addActionListener(this);
		
		field_number = new JTextField(6);
		field_number.setText(""+INIT_NUMBER);
		
		field_size = new JTextField(6);
		field_size.setText(""+INIT_BALLSIZE);
		
		persist.v(getClass().getName()+"_number",field_number);
		persist.v(getClass().getName()+"_size",field_size);
		
		JPanel p = new JPanel(new FlowLayout());
		
		p.add(new JLabel(" Nombre:"));
		p.add(field_number);
		p.add(new JLabel(" Taille:"));
		p.add(field_size);
		p.add(new JLabel(" Situation:"));
		p.add(screen.infoPanel());
		
		panel = new JPanel(new BorderLayout());
		panel.add(p,BorderLayout.NORTH);
		panel.add(screen,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		new Thread(screen,"THREAD_"+getClass().getName()).start();
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{initSimulation();}
	
	
	
	
	private void initSimulation()
	{
		try
		{
			int number = Integer.parseInt(field_number.getText());
			int size = Integer.parseInt(field_size.getText());
			
			screen.initSimulation(number,size);
		}
		catch(Exception e)
		{Outside.err(this,"initSimulation()",e);}
	}
}