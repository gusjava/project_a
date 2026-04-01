package a.entity.gus06.entitydev.pseudo.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140808";}

	private Service repaint;
	private Service findPseudo;

	private JLabel label;


	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		
		label = new JLabel(" ");
		
		updateGui();
		findPseudo.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	private void updateGui()
	{
		try
		{
			String pseudo = (String) findPseudo.g();
			String display = "USER#"+pseudo+" ";
		
			repaint.v(display,label);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
