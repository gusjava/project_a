package a.entity.gus06.swing.list.build.renderer1;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250525";}
	
	public static final Color DEFAULT_SELECTION_COLOR = new Color(153,204,255);
	
	public Object t(Object obj) throws Exception
	{
		JList list = (JList) obj;
		ListRenderer1 renderer = new ListRenderer1();
		list.setCellRenderer(renderer);
		return renderer;
	}
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer, V, R
	{
		private Icon icon;
		private Font font;
		private Color background;
		private Color foreground;
		
		private Icon selectedIcon;
		private Font selectedFont;
		private Color selectedBackground;
		private Color selectedForeground;
		
		private T iconT;
		private T fontT;
		private T textT;
		private T backgroundT;
		private T foregroundT;
		
		private T selectedIconT;
		private T selectedFontT;
		private T selectedTextT;
		private T selectedBackgroundT;
		private T selectedForegroundT;
		
		private boolean err = false;
	
		public ListRenderer1()
		{
			setOpaque(true);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("icon")) return icon;
			if(key.equals("font")) return font;
			if(key.equals("background")) return background;
			if(key.equals("foreground")) return foreground;
			
			if(key.equals("selectedIcon")) return selectedIcon;
			if(key.equals("selectedFont")) return selectedFont;
			if(key.equals("selectedBackground")) return selectedBackground;
			if(key.equals("selectedForeground")) return selectedForeground;
			
			if(key.equals("iconT")) return iconT;
			if(key.equals("textT")) return textT;
			if(key.equals("backgroundT")) return backgroundT;
			if(key.equals("foregroundT")) return foregroundT;
			
			if(key.equals("selectedIconT")) return selectedIconT;
			if(key.equals("selectedFontT")) return selectedFontT;
			if(key.equals("selectedBackgroundT")) return selectedBackgroundT;
			if(key.equals("selectedForegroundT")) return selectedForegroundT;
			
			if(key.equals("keys")) return new String[]{
				"icon",
				"font",
				"background",
				"foreground",
				"selectedIcon",
				"selectedFont",
				"selectedBackground",
				"selectedForeground",
				"iconT",
				"textT",
				"backgroundT",
				"foregroundT",
				"selectedIconT",
				"selectedFontT",
				"selectedBackgroundT",
				"selectedForegroundT"
			};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("icon")) {icon = (Icon) obj;return;}
			if(key.equals("font")) {font = (Font) obj;return;}
			if(key.equals("background")) {background = (Color) obj;return;}
			if(key.equals("foreground")) {foreground = (Color) obj;return;}
			
			if(key.equals("selectedIcon")) {selectedIcon = (Icon) obj;return;}
			if(key.equals("selectedFont")) {selectedFont = (Font) obj;return;}
			if(key.equals("selectedBackground")) {selectedBackground = (Color) obj;return;}
			if(key.equals("selectedForeground")) {selectedForeground = (Color) obj;return;}
			
			if(key.equals("iconT")) {iconT = (T) obj;return;}
			if(key.equals("textT")) {textT = (T) obj;return;}
			if(key.equals("backgroundT")) {backgroundT = (T) obj;return;}
			if(key.equals("foregroundT")) {foregroundT = (T) obj;return;}
			
			if(key.equals("selectedIconT")) {selectedIconT = (T) obj;return;}
			if(key.equals("selectedFontT")) {selectedFontT = (T) obj;return;}
			if(key.equals("selectedBackgroundT")) {selectedBackgroundT = (T) obj;return;}
			if(key.equals("selectedForegroundT")) {selectedForegroundT = (T) obj;return;}
			
			throw new Exception("Unknown key: "+key);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setIcon(buildIcon(value, isSelected));
			setFont(buildFont(value, isSelected));
			setText(buildText(value, isSelected));
			setBackground(buildBackground(value, isSelected));
			setForeground(buildForeground(value, isSelected));
			return this;
		}
		
		private Icon buildIcon(Object value, boolean isSelected)
		{
			if(isSelected)
			{
				if(selectedIconT!=null) return (Icon) apply(selectedIconT, value);
				if(selectedIcon!=null) return selectedIcon;
			}
			if(iconT!=null) return (Icon) apply(iconT, value);
			return icon;
		}
		
		private Font buildFont(Object value, boolean isSelected)
		{
			if(isSelected)
			{
				if(selectedFontT!=null) return (Font) apply(selectedFontT, value);
				if(selectedFont!=null) return selectedFont;
			}
			if(fontT!=null) return (Font) apply(fontT, value);
			return font;
		}
		
		private String buildText(Object value, boolean isSelected)
		{
			if(isSelected)
			{
				if(selectedTextT!=null) return (String) apply(selectedTextT, value);
			}
			if(textT!=null) return (String) apply(textT, value);
			return ""+value;
		}
		
		private Color buildBackground(Object value, boolean isSelected)
		{
			if(isSelected)
			{
				if(selectedBackgroundT!=null) return (Color) apply(selectedBackgroundT, value);
				if(selectedBackground!=null) return selectedBackground;
				return DEFAULT_SELECTION_COLOR;
			}
			if(backgroundT!=null) return (Color) apply(backgroundT, value);
			return background;
		}
		
		private Color buildForeground(Object value, boolean isSelected)
		{
			if(isSelected)
			{
				if(selectedForegroundT!=null) return (Color) apply(selectedForegroundT, value);
				if(selectedForeground!=null) return selectedForeground;
			}
			if(foregroundT!=null) return (Color) apply(foregroundT, value);
			return foreground;
		}
	
		private Object apply(T t, Object input)
		{
			if(err) return null;
			try{return t.t(input);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"apply(T,Object)",e);}
			err = true;
			return null;
		}
	}
}