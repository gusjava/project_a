package a.entity.gus06.swing.label.build.titlelabel1;

import java.awt.Font;
import javax.swing.JLabel;
import a.framework.*;


public class EntityImpl implements Entity, R, T, I {

	public String creationDate() {return "20160505";}

	public static final long FONTSIZE = 14;
	
	

	public JLabel label(String title)
	{
		JLabel label = new JLabel(title);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(label.getFont().deriveFont(FONTSIZE).deriveFont(Font.BOLD));
		return label;
	}



	public Object i() throws Exception
	{return label(" ");}
	
	public Object t(Object obj) throws Exception
	{return label((String) obj);}
	
	public Object r(String key) throws Exception
	{return label(key);}
}
