package a.entity.gus06.swing.label.cust.title;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20201018";}
	
	public void p(Object obj) throws Exception
	{
		JLabel label = (JLabel) obj;
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	public Object i() throws Exception
	{
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		return label;
	}
}