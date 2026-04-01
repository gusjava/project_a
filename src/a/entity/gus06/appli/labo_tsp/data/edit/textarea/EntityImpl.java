package a.entity.gus06.appli.labo_tsp.data.edit.textarea;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20190307";}


	private Service area;
	private Service manager;
	private Service exportData;
	private Service importData;
	private Service persist;


	public EntityImpl() throws Exception
	{
		area = Outside.service(this,"*gus06.swing.textarea.holder2");
		manager = Outside.service(this,"gus06.appli.labo_tsp.data.manager");
		exportData = Outside.service(this,"gus06.appli.labo_tsp.data.export1");
		importData = Outside.service(this,"gus06.appli.labo_tsp.data.import1");
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		area.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{areaChanged();}
		});
		
		manager.addActionListener(this);
		refresh();
		
		persist.v("textarea",area.r("comp"));
	}
	
	
	public Object i() throws Exception
	{return area.i();}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	private void refresh()
	{
		try
		{
			String s = (String) exportData.g();
			area.p(s);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	private void areaChanged()
	{
		try
		{
			String s = (String) area.g();
			
			manager.removeActionListener(this);
			importData.p(s);
			manager.addActionListener(this);
		}
		catch(Exception e)
		{Outside.err(this,"areaChanged()",e);}
	}


}
