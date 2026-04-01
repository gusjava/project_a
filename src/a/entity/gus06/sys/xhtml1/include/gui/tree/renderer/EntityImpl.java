package a.entity.gus06.sys.xhtml1.include.gui.tree.renderer;

import a.framework.*;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import java.util.Map;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220908";}
	
	public static final String KEY_LOCATION = "location";
	public static final String KEY_TYPE = "type";
	
	public static final Color COLOR_SELECT = new Color(244,244,244);
	public static final Color COLOR_UNSELECT = Color.WHITE;
	
	
	private Service iconProvider;

	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
	}

	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}


	private Icon iconForType(String type)
	{
		if(type.equals("include")) return icon("XHTML_ui_include");
		if(type.equals("decorate")) return icon("XHTML_ui_decorate");
		if(type.equals("composition")) return icon("XHTML_ui_composition");
		return null;
	}



	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setCellRenderer(new TreeCellRenderer0());
	}
	
	
	private class TreeCellRenderer0 extends JLabel implements TreeCellRenderer
	{
		public TreeCellRenderer0()
		{
			super();
			setOpaque(true);
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf,int row, boolean hasFocus)
		{
			Map map = (Map) value;
			String location = (String) map.get(KEY_LOCATION);
			String type = (String) map.get(KEY_TYPE);
			
			setText(location);
			setIcon(iconForType(type));
			setBackground(selected ? COLOR_SELECT : COLOR_UNSELECT);
			
			return this;
		}
	}
}