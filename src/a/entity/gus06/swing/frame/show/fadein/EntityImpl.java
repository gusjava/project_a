package a.entity.gus06.swing.frame.show.fadein;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class EntityImpl implements Entity, T, V, P {

	public String creationDate() {return "20161004";}


	private Service findComp;
	private Service custDisplay;
	private Service windowFadein;

	public EntityImpl() throws Exception
	{
		findComp = Outside.service(this,"gus06.find.jcomponent");
		custDisplay = Outside.service(this,"gus06.swing.frame.cust2.display");
		windowFadein = Outside.service(this,"gus06.awt.window.fadein");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JFrame frame = toFrame(obj);
		windowFadein.p(frame);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		JFrame frame = toFrame(obj);
		custDisplay.v(key,frame);
		windowFadein.p(frame);
	}
	
	public Object t(Object obj) throws Exception
	{
		JFrame frame = toFrame(obj);
		windowFadein.p(frame);
		return frame;
	}
	
	
	
	
	private JFrame toFrame(Object obj) throws Exception
	{
		if(obj instanceof JFrame) return (JFrame) obj;
		
		JComponent comp = (JComponent) findComp.t(obj);
		
		JFrame frame = new JFrame();
		frame.setContentPane(comp);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		
		return frame;
	}
}
