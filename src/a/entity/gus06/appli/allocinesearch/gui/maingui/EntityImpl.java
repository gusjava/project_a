package a.entity.gus06.appli.allocinesearch.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150210";}


	private Service apiSearch;
	private Service inputHolder;
	private Service viewer;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		apiSearch = Outside.service(this,"gus06.web.allocine.api.search");
		inputHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		viewer = Outside.service(this,"*gus06.appli.allocinesearch.gui.resultview");
		
		JComponent inputComp = (JComponent) inputHolder.i();
		inputComp.setFont(inputComp.getFont().deriveFont((float)16));
		
		inputHolder.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(inputComp,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			String input = (String) inputHolder.g();
			Object data = apiSearch.t(input);
			viewer.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
