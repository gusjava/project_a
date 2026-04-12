package a.entity.gus06.swing.panel.holder.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.Action;


public class EntityImpl implements Entity, I, V {

	public String creationDate() {return "20180117";}


	private Service toolbar;


	private JPanel comp;
	private JToolBar bar_w;
	private JToolBar bar_e;
	
	
	public EntityImpl() throws Exception
	{
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1.s25");
		
		bar_w = (JToolBar) toolbar.i();
		bar_w.setFloatable(false);
		
		bar_e = (JToolBar) toolbar.i();
		bar_e.setFloatable(false);
		
		comp = new JPanel(new BorderLayout());
		comp.add(bar_w,BorderLayout.WEST);
		comp.add(bar_e,BorderLayout.EAST);
	}
	
	
	public Object i() throws Exception
	{return comp;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj instanceof JComponent) {addComp(key, (JComponent) obj);return;}
		if(obj instanceof Action) {addAction(key, (Action) obj);return;}
		if(obj instanceof String) {addString(key, (String) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void addComp(String key, JComponent c) throws Exception
	{
		if(key.equals("w")) {bar_w.add(c);return;}
		if(key.equals("e")) {bar_e.add(c);return;}
		if(key.equals("c")) {comp.add(c,BorderLayout.CENTER);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private void addAction(String key, Action a) throws Exception
	{
		if(key.equals("w")) {bar_w.add(a);return;}
		if(key.equals("e")) {bar_e.add(a);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private void addString(String key, String s) throws Exception
	{
		if(s.equals("separator")) {addSeparator(key);return;}
		throw new Exception("Unknown command: "+s);
	}
	
	private void addSeparator(String key) throws Exception
	{
		if(key.equals("w")) {bar_w.addSeparator();return;}
		if(key.equals("e")) {bar_e.addSeparator();return;}
		
		throw new Exception("Unknown key: "+key);
	}
}
