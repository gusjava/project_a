package a.entity.gus06.watching.jvm.memory.gui0b;

import java.awt.BorderLayout;
import java.awt.Color;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.text.NumberFormat;
import java.util.Locale;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190524";}

	public final static NumberFormat NF = NumberFormat.getNumberInstance(Locale.FRENCH);

	private Service memory;
	private Service realTime;
	
	private JPanel panel;
	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		memory = Outside.service(this,"gus06.watching.jvm.memory");
		realTime = Outside.service(this,"*gus06.swing.panel.screen.realtime1");
		
		H f_used = (H) memory.r("usedMemory");
		H f_total = (H) memory.r("totalMemory");
		double totalMem = f_total.h(0);
		
		long total = Runtime.getRuntime().totalMemory();
		long max = Runtime.getRuntime().maxMemory();
		
		realTime.v("background",Color.BLACK);
		realTime.v("range",new double[]{0,totalMem});
		realTime.v("color",Color.GREEN);
		realTime.p(f_used);
		
		label = (JLabel) memory.r("label2Used");
		
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont((float) 10));
		label.setToolTipText("<html>max="+NF.format(max)+"<br/>total="+NF.format(total)+"</html>");
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) realTime.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}
}
