package a.entity.gus06.app.mainframe.titlechanger;

import a.framework.*;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20140802";}


	private Service defaultTitle;
	private Service started;
	
	private JFrame frame;
	private String info;
	
	

	public EntityImpl() throws Exception
	{
		defaultTitle = Outside.service(this,"gus06.app.mainframe.defaulttitle");
		started = Outside.service(this,"started");
		frame = (JFrame) Outside.resource(this,"mainframe");
		
		started.addActionListener(this);
		updateTitle();
	}
	
	
	public void p(Object obj) throws Exception
	{ 
		info = (String) obj;
		updateTitle();
	}
	
	
	private void updateTitle() throws Exception
	{frame.setTitle(buildTitle());}
	
	
	private String buildTitle() throws Exception
	{
		String title = (String) defaultTitle.g();
		if(info==null || info.equals("")) return title;
		return title+" - "+info;
	}


	public void actionPerformed(ActionEvent e)
	{init();}
	
	
	private void init()
	{
		try{updateTitle();}
		catch(Exception e)
		{Outside.err(this,"init()",e);}
	}
}
