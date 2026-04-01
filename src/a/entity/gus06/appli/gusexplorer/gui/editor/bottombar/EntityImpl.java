package a.entity.gus06.appli.gusexplorer.gui.editor.bottombar;

import a.framework.*;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20190331";}


	private Service toolbar;
	private Service fillBar;

	private S editor;
	private JToolBar bar;
	


	public EntityImpl() throws Exception
	{
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		fillBar = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar");
		
		bar = (JToolBar) toolbar.i();
	}
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(editor!=null) editor.removeActionListener(this);
		editor = (S) obj;
		if(editor!=null) editor.addActionListener(this);
		refresh();
	}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	private void refresh()
	{
		try
		{fillBar.p(new Object[]{bar,editor});}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}
