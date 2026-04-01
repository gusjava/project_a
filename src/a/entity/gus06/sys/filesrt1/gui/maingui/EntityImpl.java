package a.entity.gus06.sys.filesrt1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.List;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20230103";}


	private Service guiDetails;
	private Service guiList;

	private JPanel panel;
	
	private List data;
	

	public EntityImpl() throws Exception
	{
		guiDetails = Outside.service(this,"*gus06.sys.filesrt1.gui.details");
		guiList = Outside.service(this,"*gus06.sys.filesrt1.gui.list");
		
		JComponent compList = (JComponent) guiList.i();
		JComponent compDetails = (JComponent) guiDetails.i();
		
		compList.setPreferredSize(new Dimension(110,0));
		
		panel = new JPanel(new BorderLayout());
		panel.add(compList, BorderLayout.WEST);
		panel.add(compDetails, BorderLayout.NORTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		guiList.p(data);
		guiDetails.p(null);
	}
}