package a.entity.gus06.watching.jvm.memory.gui0a;

import java.awt.BorderLayout;
import java.awt.Color;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190519";}


	private Service memory;
	private Service realTime;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		memory = Outside.service(this,"gus06.watching.jvm.memory");
		realTime = Outside.service(this,"*gus06.swing.panel.screen.realtime1");
		
		H f_used = (H) memory.r("usedMemory");
		H f_total = (H) memory.r("totalMemory");
		double totalMem = f_total.h(0);
		
		realTime.v("background",Color.BLACK);
		realTime.v("range",new double[]{0,totalMem});
		realTime.v("color",Color.GREEN);
		realTime.p(f_used);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) realTime.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}
}
