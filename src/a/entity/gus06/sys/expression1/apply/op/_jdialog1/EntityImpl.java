package a.entity.gus06.sys.expression1.apply.op._jdialog1;

import a.framework.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160831";}

	public final static String KEY_DISPLAY = "display";
	public final static String KEY_CONTENT = "content";
	public final static String KEY_DRAGGABLE = "draggable";
	public final static String KEY_RESIZABLE = "resizable";
	public final static String KEY_REMOVABLE = "removable";
	public final static String KEY_UNDECORATED = "undecorated";
	public final static String KEY_ALWAYSONTOP = "alwaysontop";
	public final static String KEY_AUTOPACK = "autopack";
	public final static String KEY_PACK = "pack";
	public final static String KEY_VISIBLE = "visible";
	public final static String KEY_SIZE = "size";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_PERSISTKEY = "persistkey";
	
	
	
	private Service repaint;
	private Service draggable;
	private Service removable;
	private Service boundsPersister;
	private Service findDimension;
	private Service findEmptyBorder;
	private Service setAutoPack;
	
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.dialog.cust2.display");
		draggable = Outside.service(this,"gus.x.swing.comp.cust.dragframe");
		removable = Outside.service(this,"gus06.swing.comp.cust.removable");
		boundsPersister = Outside.service(this,"gus06.swing.dialog.persister.bounds");
		findDimension = Outside.service(this,"gus06.find.dimension");
		findEmptyBorder = Outside.service(this,"gus06.find.emptyborder");
		setAutoPack = Outside.service(this,"gus06.awt.window.autopack");
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
	
	
	
	private JDialog build(Map map) throws Exception
	{
		String display = (String) get(map,KEY_DISPLAY);
		JComponent content = (JComponent) get(map,KEY_CONTENT);
		Boolean draggable = (Boolean) get(map,KEY_DRAGGABLE);
		Boolean resizable = (Boolean) get(map,KEY_RESIZABLE);
		Boolean removable = (Boolean) get(map,KEY_REMOVABLE);
		Boolean undecorated = (Boolean) get(map,KEY_UNDECORATED);
		Boolean alwaysOnTop = (Boolean) get(map,KEY_ALWAYSONTOP);
		Boolean visible = (Boolean) get(map,KEY_VISIBLE);
		Boolean autopack = (Boolean) get(map,KEY_AUTOPACK);
		Boolean pack = (Boolean) get(map,KEY_PACK);
		String persistKey = (String) get(map,KEY_PERSISTKEY);
		Object size = get(map,KEY_SIZE);
		Object margin = get(map,KEY_MARGIN);
		
		content = marginedContent(content,margin);
		
		JDialog dialog = new JDialog();
		
		if(display!=null)
		{
			repaint.v(display,dialog);
		}
		if(content!=null)
		{
			dialog.setContentPane(content);
		}
		if(draggable!=null)
		{
			setDraggable(dialog,draggable.booleanValue());
		}
		if(removable!=null)
		{
			setRemovable(dialog,removable.booleanValue());
		}
		if(resizable!=null)
		{
			dialog.setResizable(resizable.booleanValue());
		}
		if(undecorated!=null)
		{
			dialog.setUndecorated(undecorated.booleanValue());
		}
		if(alwaysOnTop!=null)
		{
			dialog.setAlwaysOnTop(alwaysOnTop.booleanValue());
		}
		if(size!=null)
		{
			setSize(dialog,size);
		}
		dialog.setLocationRelativeTo(null);
		
		if(visible!=null)
		{
			dialog.setVisible(visible.booleanValue());
		}
		if(autopack!=null)
		{
			if(autopack.booleanValue()) setAutoPack.p(dialog);
		}
		if(pack!=null)
		{
			if(pack.booleanValue()) dialog.pack();
		}
		if(persistKey!=null)
		{
			persistKey(dialog,persistKey);
		}
		
		return dialog;
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private void setDraggable(JDialog dialog, boolean v) throws Exception
	{if(v) draggable.p(dialog);}
	
	
	private void setRemovable(JDialog dialog, boolean v) throws Exception
	{if(v) removable.p(dialog);}
	
	
	private void persistKey(JDialog dialog, String key) throws Exception
	{boundsPersister.v(getClass().getName()+"_bounds_"+key,dialog);}
	
	
	private void setSize(JDialog dialog, Object size) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(size);
		
		dialog.setMinimumSize(dim);
		dialog.setMaximumSize(dim);
		dialog.setPreferredSize(dim);
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