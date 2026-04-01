package a.entity.gus06.swing.label.hold.title;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20161119";}
	
	
	private JLabel label;
	private String text;


	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	public Object g() throws Exception
	{return text;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		text = (String) obj;
		label.setText(text);
	}
}
