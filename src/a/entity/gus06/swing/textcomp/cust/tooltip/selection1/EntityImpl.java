package a.entity.gus06.swing.textcomp.cust.tooltip.selection1;

import a.framework.*;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseMotionListener;
import java.util.Objects;
import java.util.Map;
import javax.swing.JToolTip;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220527";}

	public static final String KEY_TOOLTIP_HANDLER = "tooltip_handler";
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		new Holder(comp);
	}
	
	private class Holder implements MouseMotionListener
	{
		private JTextComponent comp;
		private String selectedMem;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			comp.addMouseMotionListener(this);
		}
		
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e)
		{
			Point p = e.getPoint();
			int pos = comp.viewToModel(p);
			String selected = findSelected(pos);
			if(Objects.equals(selected, selectedMem)) return;
			
			handleTooltip(comp, selected);
			selectedMem = selected;
		}
		
		private String findSelected(int pos)
		{
			if(pos<comp.getSelectionStart()) return null;
			if(pos>comp.getSelectionEnd()) return null;
			return comp.getSelectedText();
		}
	}
	
	
	
	
	
	
	
	
	private void handleTooltip(JTextComponent comp, String selected)
	{
		try
		{
			Object tooltip = computeTooltip(comp, selected);
			
			if(tooltip==null) 
				setTooltip(comp,null,null);
			else if(tooltip instanceof String) 
				setTooltip(comp, null, (String) tooltip);
			else if(tooltip instanceof G) 
				setTooltip(comp, (G) tooltip, " ");
			else if(tooltip instanceof JToolTip)
				setTooltip(comp, (G)()->tooltip, " ");
			else if(tooltip instanceof Map)
				throw new Exception("Unsupported tooltip Map="+tooltip);
			else throw new Exception("Unsupported tooltip type: "+tooltip.getClass().getSimpleName());
		}
		catch(Exception e)
		{
			Outside.err(this,"handleTooltip(JTextComponent,String)",e);
			comp.setToolTipText(null);
		}
	}
	
	
	private void setTooltip(JTextComponent comp, G builder, String tooltipText) throws Exception
	{
		((V) comp).v("tooltipBuilder", builder);
		comp.setToolTipText(tooltipText);
	}
	
	private Object computeTooltip(JTextComponent comp, String selected) throws Exception
	{
		if(selected==null) return null;
		T t = findT(comp);
		return t!=null ? t.t(selected) : null;
	}
	
	private T findT(JTextComponent comp) throws Exception
	{
		if(!(comp instanceof R)) return null;
		Map data = (Map) ((R) comp).r("data");
		if(data==null) return null;
		return (T) get(data, KEY_TOOLTIP_HANDLER);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}