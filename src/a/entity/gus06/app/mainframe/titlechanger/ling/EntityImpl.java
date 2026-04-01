package a.entity.gus06.app.mainframe.titlechanger.ling;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class EntityImpl implements Entity, P, ActionListener {

	public String creationDate() {return "20140808";}


	private Service defaultTitle;
	private Service langManager;
	private Service lingString;

	private JFrame frame;
	private String info;
	
	

	public EntityImpl() throws Exception
	{
		defaultTitle = Outside.service(this,"gus06.app.mainframe.defaulttitle");
		langManager = Outside.service(this,"gus06.ling.language.manager");
		lingString = Outside.service(this,"gus06.ling.find.lingstring");
		frame = (JFrame) Outside.resource(this,"mainframe");
		
		updateTitle();
		langManager.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{updateTitle();}
		
	
	
	public void p(Object obj) throws Exception
	{
		info = (String) obj;
		updateTitle();
	}
	
	
	private void updateTitle()
	{
		try{frame.setTitle(buildTitle());}
		catch(Exception e)
		{Outside.err(this,"updateTitle()",e);}
	}
	
	
	private String buildTitle() throws Exception
	{
		String title = (String) defaultTitle.g();
		if(info==null || info.equals("")) return ling(title);
		return ling(title)+" - "+ling(info);
	}
	
	
	private String ling(String key) throws Exception
	{return (String) lingString.r(key);}
}
