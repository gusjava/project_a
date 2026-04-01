package a.entity.gus06.sys.xhtmlparser1.gui.tree.cust.renderer;

import a.framework.*;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Color;
import javax.swing.Icon;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200103";}

	public static final Color SELECTION_COLOR = new Color(210,235,235);

	public static final String K_TYPE = "type";
	public static final String K_NAME = "name";
	public static final String K_VALUE = "value";
	public static final String K_CLOSINGTAG = "closingtag";
	
	public static final String T_ROOT = "root";
	public static final String T_ELEMENT = "element";
	public static final String T_TEXT = "text";
	
	
	private Service custUI;
	
	private Icon iconRoot;
	private Icon iconText;
	private Icon iconTag1;
	private Icon iconTag2;
	
	
	public EntityImpl() throws Exception
	{
		custUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
		
		iconRoot = (Icon) Outside.resource(this,"icon#CLIPBOARD_file");
		iconText = (Icon) Outside.resource(this,"icon#CLIPBOARD_text");
		iconTag1 = (Icon) Outside.resource(this,"icon#UTIL_tag_a");
		iconTag2 = (Icon) Outside.resource(this,"icon#UTIL_tag_b");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setCellRenderer(new TreeCellRenderer1());
		custUI.p(tree);
	}
	
	private class TreeCellRenderer1 extends JLabel implements TreeCellRenderer
	{
		public TreeCellRenderer1()
		{
			super();
			setOpaque(true);
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			if(value==null) return reset(); 
			Map tag = (Map) value;
			if(tag.isEmpty()) return reset();
			
			String type = (String) tag.get(K_TYPE);
			
			setText(findText(tag,type));
			setIcon(findIcon(tag,type));
			setBackground(findBackground(selected));
			
			return this;
		}
		
		private Component reset()
		{
			setText("");
			setIcon(null);
			setBackground(Color.WHITE);
			return this;
		}
	}
	
	
	private String findText(Map tag, String type)
	{
		if(type.equals(T_ROOT)) return "Root";
		if(tag.containsKey(K_VALUE))
		{
			String text = ((String) tag.get(K_VALUE)).replaceAll("[\n\t ]+"," ").trim();
			if(text.length()>25) return text.substring(0,25)+"...";
			return text;
		}
		return "?";
	}
	
	private Icon findIcon(Map tag, String type)
	{
		if(type.equals(T_ROOT)) return iconRoot;
		if(type.equals(T_TEXT)) return iconText;
		if(type.equals(T_ELEMENT)) return tag.containsKey(K_CLOSINGTAG) ? iconTag1 : iconTag2;
		return null;
	}
	
	private Color findBackground(boolean selected)
	{
		if(selected) return SELECTION_COLOR;
		return Color.WHITE;
	}
}