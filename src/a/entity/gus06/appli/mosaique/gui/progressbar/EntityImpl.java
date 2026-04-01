package a.entity.gus06.appli.mosaique.gui.progressbar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20141122";}


	private Service engine;


	private JProgressBar bar;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.appli.mosaique.engine");
		
		bar = new JProgressBar();
		bar.setBorderPainted(false);
		bar.setStringPainted(true);
		bar.setForeground(Color.GRAY);
		
		engine.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return bar;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			int[] value = (int[]) engine.r("progress");
			if(value==null) {bar.setMaximum(0);return;}
			
			bar.setMaximum(value[1]);
			bar.setValue(value[0]);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}
