package a.entity.gus06.dir.runtask.org.listing.editing;

import a.framework.*;
import java.io.File;
import java.util.Set;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200531";}
	
	
	private Service newGui;
	private Service show;
	private Service repaint;
	
	public EntityImpl() throws Exception
	{
		newGui = Outside.service(this,"factory#gus06.sys.direditor1.gui.main");
		show = Outside.service(this,"gus06.swing.frame.show");
		repaint = Outside.service(this,"gus06.swing.frame.cust2.display");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Object gui = newGui.g();
		((P) gui).p(dir);
		
		Object comp = ((I)gui).i();
		final JFrame frame = (JFrame) show.t(comp);
		repaint.v("dir_edit#"+dir.getName(),frame);
		
		((S) gui).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{frame.setVisible(false);}
		});
	}
}
