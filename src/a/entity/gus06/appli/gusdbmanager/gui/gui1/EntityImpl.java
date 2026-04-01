package a.entity.gus06.appli.gusdbmanager.gui.gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20150613";}

	
	private Service listGui;
	private Service editorGui;
	
	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		listGui = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1.list");
		editorGui = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1.editor");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setOneTouchExpandable(false);
		split.setDividerLocation(250);
		
		split.setLeftComponent((JComponent) listGui.i());
		split.setRightComponent((JComponent) editorGui.i());

		listGui.addActionListener(this);
	}



	public Object i() throws Exception
	{return split;}
	
	
	public void actionPerformed(ActionEvent e)
	{selectionChanged();}

	
	private void selectionChanged()
	{
		try
		{
			String id = (String) listGui.g();
			editorGui.p(id);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
