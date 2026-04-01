package a.entity.gus06.appli.gusclient1.gui.template;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140905";}

	private Service selector;
	private Service editor;
	private Service holder;


	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.appli.gusclient1.gui.template.selector");
		editor = Outside.service(this,"*gus06.appli.gusclient1.gui.template.editor");
		holder = Outside.service(this,"gus06.appli.gusclient1.gui.template.holder");

		split = new JSplitPane();
		split.setLeftComponent((JComponent) selector.i());
		split.setRightComponent((JComponent) editor.i());

		selector.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{selected();}



	private void selected()
	{
		try
		{
			String name = (String) selector.g();
			
			holder.p(name);
			editor.p(name);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
