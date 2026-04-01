package a.entity.gus06.sys.filemovedown1.show;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20240203";}

	public static final Dimension DIM = new Dimension(1000,800);


	private Service viewer;
	private Service dialog;
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.sys.filemovedown1.gui1");
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1");
		
		dialog.v("dimension",DIM);
		viewer.addActionListener(this);
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		
		viewer.p(file);
		dialog.p(viewer.i());
		
		JComponent comp = (JComponent) viewer.r("fieldComp");
		comp.requestFocusInWindow();
	}


	public void actionPerformed(ActionEvent e)
	{close();}
	
	
	private void close()
	{
		try{dialog.p(null);}
		catch(Exception e)
		{Outside.err(this,"close()",e);}
	}
}
