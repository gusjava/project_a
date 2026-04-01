package a.entity.gus06.sys.langdetect1.engine.demo;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20160629";}


	private Service langdetect;
	private Service persister;

	private JPanel panel;
	private JLabel label;
	private JTextArea area;
	private JButton button;



	public EntityImpl() throws Exception
	{
		langdetect = Outside.service(this,"gus06.sys.langdetect1.engine");
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		label = new JLabel(" ");
		
		area = new JTextArea();
		persister.v(getClass().getName()+"_area",area);
		
		button = new JButton("Detect");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}


	public Object i() throws Exception
	{return panel;}



	public void actionPerformed(ActionEvent e)
	{detect();}

	
	
	private void detect()
	{
		try
		{
			String text = area.getText();
			String lang = (String) langdetect.t(text);
			label.setText(lang);
		}
		catch(Exception e)
		{Outside.err(this,"detect()",e);}
	}
}