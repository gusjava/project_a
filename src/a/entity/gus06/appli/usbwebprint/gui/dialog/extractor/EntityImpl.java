package a.entity.gus06.appli.usbwebprint.gui.dialog.extractor;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140915";}

	public static final Font FONT = new Font("Calibri",Font.BOLD,18);
	public static final int EDGE = 10;
	
	
	private Icon icon_USB;
	private Icon icon_wait;
	
	private JPanel panel;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;



	public EntityImpl() throws Exception
	{
		icon_USB = (Icon) Outside.resource(this,"icon#USBKEY_image1");
		icon_wait = (Icon) Outside.resource(this,"icon#WAIT_anime1");
		
		label1 = new JLabel("  USB WEB PRINT");
		label2 = new JLabel(" ");
		label3 = new JLabel(icon_wait);
		
		JPanel p1 = new JPanel(new GridLayout(3,1));
		p1.setOpaque(false);
		p1.add(label1);
		p1.add(label2);
		p1.add(label3);
		
		label1.setFont(FONT);
		label2.setFont(FONT);
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(EDGE,EDGE,EDGE,EDGE));
		panel.setBackground(Color.WHITE);
		
		panel.add(new JLabel(icon_USB),BorderLayout.WEST);
		panel.add(p1,BorderLayout.CENTER);
	}

	

	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		String value = (String) obj;
		if(value!=null) label2.setText("  Lecture : "+value+"% ");
		else label2.setText(" ");
	}
}
