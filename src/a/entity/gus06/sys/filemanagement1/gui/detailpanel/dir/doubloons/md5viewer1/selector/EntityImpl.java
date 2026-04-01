package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.md5viewer1.selector;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.GridLayout;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20250612";}
	
	public static final Color COLOR_UNSELECTED = Color.GRAY;
	public static final Color COLOR_SELECTED = Color.BLACK;

	private Service findIcon;
	private Service iconsToIcon;

	private Icon iconEmpty;
	private Icon iconSelected;

	private JPanel panel;
	private List items;
	
	private Object engine;
	private Map selected;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.path.icon.t1");
		iconsToIcon = Outside.service(this,"gus06.convert.iconstoicon");
		
		iconEmpty = (Icon) Outside.resource(this,"icon#PART_empty");
		iconSelected = (Icon) Outside.resource(this,"icon#PART_connected");
		
		panel = new JPanel(new GridLayout(0,1));
		items = new ArrayList();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		map = (Map) o[2];
		
		List l = paths();
		if(l!=null) for(int i=0;i<l.size();i++)
		{
			String path = (String) l.get(i);
			Item1 check = new Item1(path);
			
			panel.add(check);
			items.add(check);
		}
	}
	
	
	private String path()
	{return map!=null ? (String) map.get("path") : null;}
	
	
	private List paths()
	{return map!=null ? (List) map.get("paths") : null;}
	
	
	private boolean isDeduplicated()
	{return map!=null && map.containsKey("deduplicated");}
	
	
	private void selectPath(String path)
	{
		map.put("path",path);
		refreshAll();
		selected();
	}
	
	
	
	private void refreshAll()
	{
		for(int i=0;i<items.size();i++)
		{
			Item1 check = (Item1) items.get(i);
			check.refresh();
		}
	}
	
	
	
	private class Item1 extends JLabel implements MouseListener
	{
		private String path;
		private Icon iconS;
		private Icon iconC;
		private Icon iconD;
		private Font fontBold;
		private Font fontPlain;
		
		public Item1(String path) throws Exception
		{
			super();
			setFocusable(true);
			this.path = path;
			
			Icon icon = (Icon) findIcon.t(path);
			iconS = (Icon) iconsToIcon.t(new Icon[]{icon, iconSelected});
			iconC = (Icon) iconsToIcon.t(new Icon[]{icon, iconEmpty});
			
			fontBold = getFont().deriveFont(Font.BOLD);
			fontPlain = getFont().deriveFont(Font.PLAIN);
			
			setText(path);
			setToolTipText(path);
			setIcon(icon);
			
			addMouseListener(this);
			refresh();
		}
		
		public void mousePressed(MouseEvent e)
		{
			if(isDeduplicated()) return;
			
			selectPath(path);
			requestFocusInWindow();
		}
		
		public void mouseReleased(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		
		
		
		public void refresh()
		{
			setFont(findFont());
			setIcon(findIcon());
			setForeground(findForeground());
		}
		
		
		private Font findFont()
		{return isSelected() ? fontBold : fontPlain;}
		
		private Icon findIcon()
		{
			if(isSelected()) return iconS;
			return iconC;
		}
		
		private Color findForeground()
		{
			if(isSelected()) return COLOR_SELECTED;
			return COLOR_UNSELECTED;
		}
		
		public boolean isSelected()
		{return Objects.equals(path, path());}
	}
	
	
	private void selected()
	{send(this,"selected()");}
	
	private void deduplicated()
	{send(this,"deduplicated()");}
}