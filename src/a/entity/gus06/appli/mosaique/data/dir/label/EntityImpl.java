package a.entity.gus06.appli.mosaique.data.dir.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20141122";}


	private Service dataHolder;
	private Service rendering;

	private JLabel label;

	public EntityImpl() throws Exception
	{
		dataHolder = Outside.service(this,"gus06.appli.mosaique.data.dir");
		rendering = Outside.service(this,"gus06.swing.label.cust3.filedisplay");
		
		label = new JLabel(" ");
		dataHolder.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	private void updateGui()
	{
		try
		{
			File f = (File) dataHolder.g();
			rendering.p(new Object[]{label,f});
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
