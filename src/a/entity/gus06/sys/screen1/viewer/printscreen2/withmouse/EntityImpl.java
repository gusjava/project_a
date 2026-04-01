package a.entity.gus06.sys.screen1.viewer.printscreen2.withmouse;

import java.util.Date;
import java.awt.*;
import a.framework.*;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20220715";}

	public static final double FACTOR = 0.20;


	private Service timer;
	private Service printScreen;
	private Service screenPanel;
	
	private JPanel panel;
	private Dimension dim;

	public EntityImpl() throws Exception
	{
		timer = Outside.service(this,"gus06.time.timer.ms100");
		printScreen = Outside.service(this,"gus06.awt.robot.printscreen2.withmouse");
		screenPanel = Outside.service(this,"gus06.swing.panel.screen.image");
		
		panel = (JPanel) screenPanel.i();
		
		Rectangle rec = getScreenRect();
		int x = (int)(rec.getWidth()*FACTOR);
		int y = (int)(rec.getHeight()*FACTOR);
		dim = new Dimension(x,y);
		
		panel.setMinimumSize(dim);
		panel.setMaximumSize(dim);
		panel.setPreferredSize(dim);
		
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{update();}
		});
	}



	public Object i() throws Exception
	{return screenPanel.i();}


	
	private void update()
	{
		try
		{
			if(!((JComponent) i()).isShowing()) return;
			screenPanel.p(printScreen.g());
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
	
	
	private Rectangle getScreenRect()
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration gc = device.getDefaultConfiguration();
		return gc.getBounds();
	}
}
