package a.entity.gus06.swing.textcomp.cust.selection.clipboardcolored;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20200325";}


	private Service clipboard;
	private Service each100;
	
	private Map map;


	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		each100 = Outside.service(this,"gus06.time.timer.ms100");
		
		map = new HashMap();
		each100.addActionListener(this);
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		map.put(comp, comp.getSelectionColor());
	}


	public void actionPerformed(ActionEvent e)
	{check();}
	
	
	private void check()
	{
		try
		{
			String s = (String) clipboard.g();
			if(s!=null) handleAll(s);
			else resetAll();
		}
		catch(Exception e)
		{Outside.err(this,"check()",e);}
	}
	
	
	private void handleAll(String s)
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			JTextComponent comp = (JTextComponent) it.next();
			String selection = comp.getSelectedText();
			if(selection!=null)
			{
				Color color = (Color) map.get(comp);
				if(selection.equals(s)) color = changeColor(color);
				setSelectionColor(comp, color);
			}
		}
	}
	
	
	private void resetAll()
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			JTextComponent comp = (JTextComponent) it.next();
			Color color = (Color) map.get(comp);
			setSelectionColor(comp, color);
		}
	}


	private Color changeColor(Color c)
	{
		int r = Math.min(255,c.getRed()+110);
		int g = Math.min(255,c.getGreen()+90);
		int b = Math.min(255,c.getBlue()+90);
		return new Color(r,g,b);
	}
	
	
	private void setSelectionColor(final JTextComponent comp, final Color color)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {comp.setSelectionColor(color);}
		});
	}
}
