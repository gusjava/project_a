package a.entity.gus06.swing.frame.show;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.Scrollable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T, V, P {

	public String creationDate() {return "20151001";}


	private Service findComp;
	private Service custDisplay;

	public EntityImpl() throws Exception
	{
		findComp = Outside.service(this,"gus06.find.jcomponent");
		custDisplay = Outside.service(this,"gus06.swing.frame.cust2.display");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public Object t(Object obj) throws Exception
	{
		JFrame frame = toFrame(obj);
		setVisible(frame);
		return frame;
	}
	
	public void v(String key, Object obj) throws Exception
	{
		JFrame frame = toFrame(obj);
		custDisplay.v(key,frame);
		setVisible(frame);
	}
	
	
	private JFrame toFrame(Object obj) throws Exception
	{
		if(obj instanceof JFrame) return (JFrame) obj;
		
		JComponent comp = findComp(obj);
		JFrame frame = new JFrame();
		frame.setContentPane(comp);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		return frame;
	}
	
	
	private JComponent findComp(Object obj) throws Exception
	{
		JComponent comp = (JComponent) findComp.t(obj);
		if(comp instanceof Scrollable) comp = new JScrollPane(comp);
		return comp;
	}
	
	
	
	private void setVisible(final JFrame frame)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{frame.setVisible(true);}
		});
	}
}
