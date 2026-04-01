package a.entity.gus06.appli.vindinium.gui.gameview.session.progress;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}
	
	public static final Color COLOR = Color.LIGHT_GRAY;
	public static final Color COLOR1 = Color.GRAY;
	
	private JPanel panel;
	private JProgressBar bar;
	private JLabel label;



	public EntityImpl() throws Exception
	{
		bar = new JProgressBar();
		bar.setForeground(COLOR);
		bar.setBackground(Color.WHITE);
		bar.setBorderPainted(false);
		bar.setStringPainted(true);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(bar,BorderLayout.CENTER);
		panel.add(label,BorderLayout.EAST);
	}



	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		int[] turns = (int[]) data.get(DATA_._TURNS);
		
		bar.setValue(turns[0]);
		bar.setMaximum(turns[1]);
		
		label.setText(" "+turns[0]+" / "+turns[1]+" ");
		updateForeground();
	}

	public Object i() throws Exception
	{return panel;}

	
	
	private void updateForeground()
	{
		if(bar.getValue()==bar.getMaximum())
			bar.setForeground(COLOR1);
		else bar.setForeground(COLOR);
	}
}
