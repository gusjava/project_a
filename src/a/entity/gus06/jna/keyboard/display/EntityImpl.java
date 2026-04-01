package a.entity.gus06.jna.keyboard.display;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, G, S {

	public String creationDate() {return "20161127";}

	private Service support;
	private Service find;
	
	private List list;


	public EntityImpl() throws Exception
	{
		support = Outside.service(this,"gus06.jna.keyboard.support");
		find = Outside.service(this,"gus06.jna.keyboard.display.find");
	}
	
	
	
	public Object g() throws Exception
	{
		String code = (String) support.g();
		return (String) find.t(code);
	}

	public void addActionListener(ActionListener l) throws Exception
	{support.addActionListener(l);}

	public void removeActionListener(ActionListener l) throws Exception
	{support.removeActionListener(l);}
	
	public List listeners() throws Exception
	{return support.listeners();}
}
