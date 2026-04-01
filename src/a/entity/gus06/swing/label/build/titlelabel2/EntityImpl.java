package a.entity.gus06.swing.label.build.titlelabel2;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;

public class EntityImpl implements Entity, I, T, R {

	public String creationDate() {return "20221104";}
	
	public static final long FONTSIZE = 14;
	
	

	public JLabel label(String title)
	{
		JLabel label = new JLabel(title);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(label.getFont().deriveFont(FONTSIZE).deriveFont(Font.BOLD));
		label.setBackground(Color.LIGHT_GRAY);
		return label;
	}



	public Object i() throws Exception
	{return label(" ");}
	
	public Object t(Object obj) throws Exception
	{return label((String) obj);}
	
	public Object r(String key) throws Exception
	{return label(key);}
}
