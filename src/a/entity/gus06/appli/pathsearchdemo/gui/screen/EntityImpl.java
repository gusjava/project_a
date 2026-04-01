package a.entity.gus06.appli.pathsearchdemo.gui.screen;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, V {

	public String creationDate() {return "20150327";}


	private ScreenJPanel panel;

	public EntityImpl() throws Exception
	{panel = new ScreenJPanel();}


	public Object i() throws Exception
	{return panel;}

	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("maze"))
		{
			panel.setMaze((boolean[][])obj);
			panel.repaint();
			return;
		}
		if(key.equals("state"))
		{
			panel.setState((boolean[][])obj);
			panel.repaint();
			return;
		}
		if(key.equals("start"))
		{
			panel.setStart((int[])obj);
			panel.repaint();
			return;
		}
		if(key.equals("end"))
		{
			panel.setEnd((int[])obj);
			panel.repaint();
			return;
		}
		if(key.equals("position"))
		{
			panel.setPosition((int[])obj);
			panel.repaint();
			return;
		}
		if(key.equals("path"))
		{
			panel.setPath((int[][])obj);
			panel.repaint();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
}
