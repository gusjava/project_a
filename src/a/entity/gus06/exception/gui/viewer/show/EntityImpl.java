package a.entity.gus06.exception.gui.viewer.show;

import a.framework.*;
import java.awt.Dimension;
import javax.swing.JFrame;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20221117";}

	public static final Dimension DIM = new Dimension(900,500);
	public static final String TITLE = "UTIL_error#Exceptions";

	private Service viewer;
	private Service show;
	private JFrame frame;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.exception.gui.viewer");
		show = Outside.service(this,"gus06.swing.frame.show2");
	}
	
	
	public void e() throws Exception
	{
		if(frame==null) frame = (JFrame) show.t(new Object[]{viewer,DIM,TITLE});
		frame.setVisible(true);
		frame.toFront();
		
		viewer.p("selectLast");
	}
}