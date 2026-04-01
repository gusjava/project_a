package a.entity.gus06.sys.play1.manager;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl extends S1 implements Entity, P, R, V {

	public String creationDate() {return "20160612";}

	private JFrame frame;

	public EntityImpl() throws Exception
	{
		frame = new JFrame();
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s.equals("show")) {show();return;}
		if(s.equals("hide")) {hide();return;}
		if(s.equals("clear")) {clear();return;}
	}
	
	
	public Object r(String key) throws Exception
	{
		return null;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		
	}
	
	
	private void show() throws Exception
	{
		
	}
	
	private void hide() throws Exception
	{
		frame.setVisible(false);
	}
	
	private void clear() throws Exception
	{
		frame.setVisible(true);
	}
}
