package a.entity.gus06.appli.convertisseurgus05.gui.gui1;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150525";}


	private Service selector;
	private Service holder;


	private JPanel panel;
	
	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.appli.convertisseurgus05.gui.entity.selector");
		holder = Outside.service(this,"gus06.appli.convertisseurgus05.holder.selected");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) selector.i(),BorderLayout.CENTER);
		
		selector.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	private void selected()
	{
		try
		{
			String name = (String) selector.g();
			holder.p(name);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}

}
