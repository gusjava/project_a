package a.entity.gus06.swing.textpane.factory1.calibri1;

import a.framework.*;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComponent;
import java.awt.Color;
import javax.swing.JLabel;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140829";}

	public static final Font FONT = new Font("Calibri",Font.PLAIN,22);
	public static final Color BACKGROUND = (new JLabel()).getBackground();
	public static final int MARGIN = 5;
	

	private Service factory;
	
	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textpane.factory1");
	}
	
	
	public Object i() throws Exception
	{
		JTextPane textPane = (JTextPane) factory.i();
		
		textPane.setEditable(false);
		textPane.setMargin(new Insets(MARGIN,MARGIN,MARGIN,MARGIN));
		textPane.setFont(FONT);
		textPane.setBackground(BACKGROUND);
		
		return textPane;
	}
}
