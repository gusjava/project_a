package a.entity.gus06.sys.expression1.apply.op._jframe1;

import a.framework.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180403";}

	public final static String KEY_DISPLAY = "display";
	public final static String KEY_CONTENT = "content";
	public final static String KEY_DRAGGABLE = "draggable";
	public final static String KEY_RESIZABLE = "resizable";
	public final static String KEY_UNDECORATED = "undecorated";
	public final static String KEY_ALWAYSONTOP = "alwaysontop";
	public final static String KEY_AUTOPACK = "autopack";
	public final static String KEY_PACK = "pack";
	public final static String KEY_VISIBLE = "visible";
	public final static String KEY_SIZE = "size";
	public final static String KEY_BOUNDS = "bounds";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_PERSISTKEY = "persistkey";
	
	
	
	private Service repaint;
	private Service draggable;
	private Service boundsPersister;
	private Service findDimension;
	private Service findEmptyBorder;
	private Service setAutoPack;
	private Service custBounds;
	
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.frame.cust2.display");
		draggable = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		boundsPersister = Outside.service(this,"gus06.swing.frame.persister.bounds");
		findDimension = Outside.service(this,"gus06.find.dimension");
		findEmptyBorder = Outside.service(this,"gus06.find.emptyborder");
		setAutoPack = Outside.service(this,"gus06.awt.window.autopack");
		custBounds = Outside.service(this,"gus06.swing.frame.cust2.bounds");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JFrame build(Map map) throws Exception
	{
		String display = (String) get(map,KEY_DISPLAY);
		JComponent content = (JComponent) get(map,KEY_CONTENT);
		Boolean draggable = (Boolean) get(map,KEY_DRAGGABLE);
		Boolean resizable = (Boolean) get(map,KEY_RESIZABLE);
		Boolean undecorated = (Boolean) get(map,KEY_UNDECORATED);
		Boolean alwaysOnTop = (Boolean) get(map,KEY_ALWAYSONTOP);
		Boolean visible = (Boolean) get(map,KEY_VISIBLE);
		Boolean autopack = (Boolean) get(map,KEY_AUTOPACK);
		Boolean pack = (Boolean) get(map,KEY_PACK);
		String persistKey = (String) get(map,KEY_PERSISTKEY);
		String bounds = (String) get(map,KEY_BOUNDS);
		Object size = get(map,KEY_SIZE);
		Object margin = get(map,KEY_MARGIN);
		
		content = marginedContent(content,margin);
		
		JFrame frame = new JFrame();
		
		if(display!=null)
		{
			repaint.v(display,frame);
		}
		if(content!=null)
		{
			frame.setContentPane(content);
		}
		if(draggable!=null)
		{
			setDraggable(frame,draggable.booleanValue());
		}
		if(resizable!=null)
		{
			frame.setResizable(resizable.booleanValue());
		}
		if(undecorated!=null)
		{
			frame.setUndecorated(undecorated.booleanValue());
		}
		if(alwaysOnTop!=null)
		{
			frame.setAlwaysOnTop(alwaysOnTop.booleanValue());
		}
		
		if(visible!=null)
		{
			frame.setVisible(visible.booleanValue());
		}
		if(autopack!=null)
		{
			if(autopack.booleanValue()) setAutoPack.p(frame);
		}
		if(pack!=null)
		{
			if(pack.booleanValue()) frame.pack();
		}
		if(persistKey!=null)
		{
			persistKey(frame,persistKey);
		}
		if(size!=null)
		{
			setSize(frame,size);
		}
		
		if(bounds!=null)
		{
			setBounds(frame,bounds);
		}
		else
		{
			frame.setLocationRelativeTo(null);
		}
		
		return frame;
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private void setDraggable(JFrame frame, boolean v) throws Exception
	{if(v) draggable.p(frame);}
	
	
	private void persistKey(JFrame frame, String key) throws Exception
	{boundsPersister.v(getClass().getName()+"_bounds_"+key,frame);}
	
	
	private void setSize(JFrame frame, Object size) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(size);
		frame.setPreferredSize(dim);
		frame.setMinimumSize(dim);
		frame.setMaximumSize(dim);
	}
	
	private void setBounds(JFrame frame, String bounds) throws Exception
	{
		custBounds.v(bounds,frame);
	}
	
	
	
	private JComponent marginedContent(JComponent content, Object margin) throws Exception
	{
		if(margin==null) return content;
		
		JPanel panel = new JPanel(new BorderLayout());
		
		Border border = (Border) findEmptyBorder.t(margin);
		panel.setBorder(border);
		
		if(content !=null) panel.add(content,BorderLayout.CENTER);
		return panel;
	}
}
