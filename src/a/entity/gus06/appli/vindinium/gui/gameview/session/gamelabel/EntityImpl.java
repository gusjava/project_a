package a.entity.gus06.appli.vindinium.gui.gameview.session.gamelabel;

import java.awt.Font;
import java.util.Map;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}

	public static final Font FONT = new Font("Courier",Font.PLAIN,12);
	

	private JLabel label;

	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		label.setFont(FONT);
	}


	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		int[] times = (int[]) data.get(DATA_._TIMES);
		String mode = (String) data.get(DATA_._MODE);
		
		label.setText(mode+" ["+(times[0]+1)+"/"+times[1]+"]");
	}

	public Object i() throws Exception
	{return label;}
}
