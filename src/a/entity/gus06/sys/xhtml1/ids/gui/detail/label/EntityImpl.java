package a.entity.gus06.sys.xhtml1.ids.gui.detail.label;

import a.framework.*;

import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Icon;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220909";}
	
	
	
	private Service iconProvider;
	private JLabel label;



	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
		
		label = new JLabel(" ");
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	

	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}
	
	
	public void p(Object obj) throws Exception
	{
		String location = (String) obj;
		if(location==null)
		{
			label.setText(" ");
			label.setIcon(null);
		}
		else
		{
			label.setText(location);
			label.setIcon(icon("FILE_xhtml"));
		}
	}
}