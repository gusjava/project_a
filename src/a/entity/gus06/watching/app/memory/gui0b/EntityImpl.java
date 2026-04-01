package a.entity.gus06.watching.app.memory.gui0b;

import java.awt.BorderLayout;
import java.awt.Color;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.text.NumberFormat;
import java.util.Locale;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190624";}

	public final static NumberFormat NF = NumberFormat.getNumberInstance(Locale.FRENCH);

	private Service memory;
	private Service realTime;
	
	private JPanel panel;
	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		memory = Outside.service(this,"gus06.watching.app.memory");
		realTime = Outside.service(this,"*gus06.swing.panel.screen.realtime1");
		
		realTime.v("background",Color.BLACK);
		realTime.v("color",Color.ORANGE);
		realTime.p(memory);
		
		label = (JLabel) memory.r("label");
		
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont((float) 10));
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) realTime.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}
}
