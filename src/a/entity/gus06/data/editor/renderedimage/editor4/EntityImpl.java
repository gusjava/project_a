package a.entity.gus06.data.editor.renderedimage.editor4;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G {

	public String creationDate() {return "20191119";}


	private Service screen;
	private Service bar;
	private Service executeF5;


	private JPanel panel;
	private JToolBar toolbar;
	
	private Object image;
	private Object image0;
	
	
	
	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		bar = Outside.service(this,"*gus06.data.editor.renderedimage.editor4.bar");
		executeF5 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f5");
		
		JToolBar toolbar = (JToolBar) bar.i();
		
		JComponent screenComp = (JComponent) screen.i();
		screenComp.setBackground(Color.WHITE);
		
		executeF5.p(new Object[]{screenComp,new E() {
			public void e() throws Exception {reload();}
		}});
		
		panel = new JPanel(new BorderLayout());
		panel.add(screenComp,BorderLayout.CENTER);
		panel.add(center(toolbar),BorderLayout.SOUTH);
		
		bar.addActionListener(this);
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
		screen.p(image);
		bar.p(image);
	}
	
	
	
	private void reload()
	{
		try
		{
			if(image==image0) return;
			
			image = image0;
			screen.p(image);
			bar.p(image);
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	

	public void actionPerformed(ActionEvent e)
	{edited();}
	
	
	
	private void edited()
	{
		try
		{
			image = bar.g();
			screen.p(image);
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"edited()",e);}
	}
	
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}
