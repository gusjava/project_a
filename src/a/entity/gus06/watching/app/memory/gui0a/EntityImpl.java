package a.entity.gus06.watching.app.memory.gui0a;

import java.awt.BorderLayout;
import java.awt.Color;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190624";}


	private Service memory;
	private Service realTime;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		memory = Outside.service(this,"gus06.watching.app.memory");
		realTime = Outside.service(this,"*gus06.swing.panel.screen.realtime1");
		
		realTime.v("background",Color.BLACK);
		realTime.v("color",Color.ORANGE);
		realTime.p(memory);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) realTime.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}
}
