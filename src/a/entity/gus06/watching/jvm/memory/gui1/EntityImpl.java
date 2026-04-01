package a.entity.gus06.watching.jvm.memory.gui1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190209";}


	private Service memory;
	private Service realTime;
	
	private JPanel panel;
	private JLabel label;
	private JButton button;
	

	public EntityImpl() throws Exception
	{
		memory = Outside.service(this,"gus06.watching.jvm.memory");
		realTime = Outside.service(this,"*gus06.swing.panel.screen.realtime1");
		
		H f_free = (H) memory.r("freeMemory");
		H f_total = (H) memory.r("totalMemory");
		double totalMem = f_total.h(0);
		
		realTime.v("background",Color.BLACK);
		realTime.v("range",new double[]{0,totalMem});
		realTime.v("color",Color.GREEN);
		realTime.p(f_free);
		
		label = (JLabel) memory.r("label1All");
		
		button = new JButton("garbage collector");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{System.gc();}});
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) realTime.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}

	public Object i() throws Exception
	{return panel;}
}
