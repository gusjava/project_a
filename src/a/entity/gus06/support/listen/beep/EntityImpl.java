package a.entity.gus06.support.listen.beep;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P, ActionListener {

	public String creationDate() {return "20140730";}

	
	public void p(Object obj) throws Exception
	{((S) obj).addActionListener(this);}
	
	
	public void actionPerformed(ActionEvent e)
	{Toolkit.getDefaultToolkit().beep();}
}
