package a.entity.gus06.appli.vindinium.gui.maingui.buildbutton;

import javax.swing.JButton;
import a.framework.*;
import javax.swing.border.Border;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170917";}


	private Service ovalBorder;
	private Service rollover;

	public EntityImpl() throws Exception
	{
		ovalBorder = Outside.service(this,"gus06.swing.border.build.ovalborder");
		rollover = Outside.service(this,"gus06.swing.comp.cust.onrollover.fgred.bold");
	}

	public Object t(Object obj) throws Exception
	{return button((String) obj);}

	
	private JButton button(String title) throws Exception
	{
		JButton button = new JButton(" "+title);
		button.setFont(button.getFont().deriveFont((float) 18));
		button.setBorder((Border) ovalBorder.g());
		rollover.p(button);
		return button;
	}
}
