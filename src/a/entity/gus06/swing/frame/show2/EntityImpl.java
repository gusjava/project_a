package a.entity.gus06.swing.frame.show2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.Scrollable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180331";}


	private Service findComp;
	private Service findDim;
	private Service custDisplay;

	public EntityImpl() throws Exception
	{
		findComp = Outside.service(this,"gus06.find.jcomponent");
		findDim = Outside.service(this,"gus06.find.dimension");
		custDisplay = Outside.service(this,"gus06.swing.frame.cust2.display");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = findComp(o[0]);
		Dimension dim = (Dimension) findDim.t(o[1]);
		String title = (String) o[2];
		
		JFrame frame = new JFrame();
		frame.setContentPane(comp);
		frame.setSize(dim);
		frame.setLocationRelativeTo(null);
		
		custDisplay.v(title,frame);
		setVisible(frame);
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
