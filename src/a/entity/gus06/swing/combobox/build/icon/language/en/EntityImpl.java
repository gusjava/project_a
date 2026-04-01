package a.entity.gus06.swing.combobox.build.icon.language.en;

import a.framework.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import java.util.Map;
import java.util.Locale;
import java.awt.Component;
import java.util.Collections;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.Icon;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20250531";}


	private Service buildMap;
	private Service iconProvider;

	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.locale.language.buildmap");
		iconProvider = Outside.service(this,"gus06.icon.provider.flag.language");
	}
	
	public Object i() throws Exception
	{
		Map map = (Map) buildMap.t(Locale.ENGLISH);
		return new JComboBox0(map);
	}
	
	private Icon icon(String code)
	{
		try{return (Icon) iconProvider.t(code);}
		catch(Exception e)
		{Outside.err(this,"icon(String)",e);}
		return null;
	}

	
	private class JComboBox0 extends JComboBox
	{
		private Map map;
		
		public JComboBox0(Map map)
		{
			super();
			this.map = map;
			setRenderer(new Renderer(map));
			
			setFont(getFont().deriveFont(Font.PLAIN));
			setBackground(Color.WHITE);
			
			List keys = new ArrayList(map.keySet());
    			Collections.sort(keys, new Comparator(){
				public int compare(Object o1, Object o2)
				{
					String s1 = (String) map.get(o1);
					String s2 = (String) map.get(o2);
					return s1.compareTo(s2);
				}
			});
			
			for(Object key : keys) addItem(key);
		}
	}
	
	private class Renderer extends JLabel implements ListCellRenderer
	{
		private Map map;
		
		public Renderer(Map map)
		{
			super();
			this.map = map;
			setOpaque(true);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText(" "+map.get(value));
			setIcon(icon(""+value));
			if(isSelected) setBackground(Color.LIGHT_GRAY);
			else setBackground(Color.WHITE);
			return this;
		}
	}
}