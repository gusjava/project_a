package a.entity.gus06.jna.keyboard.buffer.gadget1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.image.BufferedImage;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20141223";}

	public static final Dimension DIM = new Dimension(250,50);
	public static final Font FONT = new Font("ComicSans",Font.BOLD,30);
	public static final Color COLOR = Color.WHITE;
	public static final long LAPSE = 1000;


	private Service buffer;
	//private Service handleStates;
	//private Service insideMouse;
	//private Service behindFrame;
	
	
	private BufferedImage image;
	
	
	private Timer timer;
	private TimerTask task;
	
	private JLabel label;
	private JPanel1 panel;




	public EntityImpl() throws Exception
	{
		buffer = Outside.service(this,"gus06.jna.keyboard.buffer");
		//handleStates = Outside.service(this,"gus06.swing.comp.handleframe.states");
		//insideMouse = Outside.service(this,"gus06.swing.comp.filter.mouseinside");
		//behindFrame = Outside.service(this,"gus06.image.printcomponent.behindframe");
		
		label = new JLabel(" ");
		label.setFont(FONT);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel = new JPanel1();
		panel.setOpaque(true);
		panel.setBackground(COLOR);
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel.setMaximumSize(DIM);
		panel.setMinimumSize(DIM);
		panel.setPreferredSize(DIM);
		
		panel.add(label,BorderLayout.CENTER);
		
		timer = new Timer("TIMER_"+getClass().getName());
		buffer.addActionListener(this);
	}



	public Object i() throws Exception
	{return panel;}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateLabel();}
	
	
	
	
	private synchronized void updateLabel()
	{
		try
		{
			String display = getDisplay();
			
			if(display==null) return;
			if(label.getText().length() >= display.length()) return;
			
			label.setText(display);
			//handleStates.v("show",panel);
			
			if(task!=null) task.cancel();
			task = new TimerTask() {public void run() {clear();}};
			timer.schedule(task,LAPSE);
		}
		catch(Exception e)
		{Outside.err(this,"updateLabel()",e);}
	}
	
	
	
	private synchronized void clear()
	{
		try
		{
			label.setText("");
			//if(insideMouse.f(panel)) return;
			 
			//image = (BufferedImage) behindFrame.t(panel);
			//handleStates.v("hide",panel);
		}
		catch(Exception e)
		{Outside.err(this,"clear()",e);}
	}
	
	
	
	private String getDisplay() throws Exception
	{
		String display = (String) buffer.g();
		if(display==null || display.equals("")) return null;
		return display;
	}
	
	
	
	
	
	private class JPanel1 extends JPanel
	{
		public JPanel1()
		{super(new BorderLayout());}
		
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			if(image!=null) g2.drawImage(image,0,0,image.getWidth(this),image.getHeight(this),this);
			super.paintComponents(g);
		}
	}
}
