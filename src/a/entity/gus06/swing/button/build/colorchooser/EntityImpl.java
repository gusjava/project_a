package a.entity.gus06.swing.button.build.colorchooser;

import a.framework.*;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EntityImpl implements Entity, I, T {

	public String creationDate() {return "20170306";}
	
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	public Object i() throws Exception
	{return new JButtonColorChooser(DEFAULT_COLOR);}
	
	public Object t(Object obj) throws Exception
	{return new JButtonColorChooser((Color) obj);}
	
	
	
	private class JButtonColorChooser extends JButton implements P, G, ActionListener
	{
		private Color color;
		
		public JButtonColorChooser(Color color)
		{
			super();
			setColor(color);
			addActionListener(this); 
		}
		
		public void actionPerformed(ActionEvent e)
		{chooseColor();}
		
		private void chooseColor()
		{
			Color newColor = JColorChooser.showDialog(null,"choose color",color);
			if(newColor!=null)setColor(newColor);
		}
		
		public void setColor(Color color)
		{
			this.color = color;
			setBackground(color);
			setToolTipText("r="+color.getRed()+" ,g="+color.getGreen()+",b="+color.getBlue()); 
		}

		public Object g() throws Exception
		{return color;}
		
		public void p(Object obj) throws Exception
		{setColor((Color) obj);}
	}
}
