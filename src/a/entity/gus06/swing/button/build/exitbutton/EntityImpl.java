package a.entity.gus06.swing.button.build.exitbutton;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150609";}
	
	public Object i() throws Exception
	{
		JButton b = new JButton("Exit");
		b.addActionListener(this);
		return b;
	}

	public void actionPerformed(ActionEvent e)
	{System.exit(0);}
}
