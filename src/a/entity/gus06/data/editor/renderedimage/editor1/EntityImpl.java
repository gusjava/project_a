package a.entity.gus06.data.editor.renderedimage.editor1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20151007";}


	private Service editionPanel;
	private Service editionBar;
	private Service executeF5;


	private JPanel panel;
	private JToolBar toolbar;
	
	private Object image;
	private Object image0;
	
	
	public EntityImpl() throws Exception
	{
		editionPanel = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.panel");
		editionBar = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar");
		executeF5 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f5");
		
		JToolBar toolbar = (JToolBar) editionBar.i();
		
		JComponent editionPanelComp = (JComponent) editionPanel.i();
		editionPanelComp.setBackground(Color.WHITE);
		
		executeF5.p(new Object[]{editionPanelComp,new E() {
			public void e() throws Exception {reload();}
		}});
		
		panel = new JPanel(new BorderLayout());
		panel.add(editionPanelComp,BorderLayout.CENTER);
		panel.add(center(toolbar),BorderLayout.SOUTH);
		
		editionBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{editedByBar();}
		});
		editionPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{editedByPanel();}
		});
	}
	
	
	private JPanel center(JComponent c)
	{
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.add(c);
		return p;
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		image0 = obj;
		editionPanel.p(image);
		editionBar.p(image);
	}
	
	
	
	private void reload()
	{
		try
		{
			if(image==image0) return;
			
			image = image0;
			editionPanel.p(image);
			editionBar.p(image);
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}

	

	
	
	
	
	private void editedByBar()
	{
		try
		{
			image = editionBar.g();
			editionPanel.p(image);
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"editedByBar()",e);}
	}
	
	private void editedByPanel()
	{
		try
		{
			image = editionPanel.g();
			editionBar.p(image);
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"editedByBar()",e);}
	}
	
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}