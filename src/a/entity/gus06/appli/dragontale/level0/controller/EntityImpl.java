package a.entity.gus06.appli.dragontale.level0.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import a.framework.*;

public class EntityImpl implements Entity, E, P, R {

	public String creationDate() {return "20200517";}

	public static final int MENU_NB = 3;
	
	private Service choose;
	
	private int selected = 0;
	
	public EntityImpl() throws Exception
	{
		choose = Outside.service(this,"gus06.appli.dragontale.level0.choose");
	}


	public void e() throws Exception
	{
		selected = 0;
	}

	public void p(Object obj) throws Exception
	{
		String key = (String) obj;
		
		if(key.equals("UP")) {selectedUp();return;}
		if(key.equals("DOWN")) {selectedDown();return;}
		if(key.equals("ENTER")) {choose();return;}
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("selected")) return selected;
		if(key.equals("keys")) return new String[]{"selected"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void selectedUp()
	{
		selected--;
		if(selected == -1) selected = MENU_NB-1;
	}
	
	private void selectedDown()
	{
		selected++;
		if(selected == MENU_NB) selected = 0;
	}
	
	private void choose()
	{
		try{choose.p(""+selected);}
		catch(Exception e)
		{Outside.err(this,"choose()",e);}
	}
}
