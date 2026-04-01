package a.entity.gus06.sys.entitysrcviewer1.gui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, P, I {

	public String creationDate() {return "20191007";}


	private Service tab;
	private Service gui1;
	private Service gui2;
	private Service dirToListing;

	private JPanel panel;
	private File rootDir;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.entitysrcviewer1.gui.listing.entity");
		gui2 = Outside.service(this,"*gus06.sys.entitysrcviewer1.gui.listing.group");
		dirToListing = Outside.service(this,"gus06.entitydev.listing");
		
		
		tab.v("Entity listing",gui1.i());
		tab.v("Group listing",gui2.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		
		gui1.addActionListener(this);
		gui2.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		rootDir = (File) obj;
		reload();
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("reload()")) {reload();return;}
		if(s.equals("selectionChanged()")) {selectionChanged();return;}
	}
	
	
	private void reload()
	{
		try
		{
			List listing = (List) dirToListing.t(rootDir);
			gui1.p(listing);
			gui2.p(listing);
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}

	
	private void selectionChanged()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
