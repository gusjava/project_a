package a.entity.gus06.sys.editor16x16.select.bycolor.clipboard;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import javax.swing.JColorChooser;
import java.awt.Color;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250314";}

	public final static int NB = 16;
	public final static int MAX = NB*NB;


	private Service getColors;
	private Service encodeColor;
	
	public EntityImpl() throws Exception
	{
		getColors = Outside.service(this,"gus06.sys.editor16x16.clipboard.g.colors");
		encodeColor = Outside.service(this,"gus06.sys.editor16x16.c.encode1");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[][] data = (String[][]) o[0];
		Set selection = (Set) o[1];
		
		if(selection.size()==MAX) return false;
		
		Set colors = (Set) getColors.g();
		if(colors!=null && colors.size()>0)
		return selectByColors(data, selection, colors);
		
		Color color = JColorChooser.showDialog(null,"Choose color", Color.BLACK);
		if(color!=null) return selectByColor(data, selection, color);
		
		return false;
	}
	
	
	private boolean selectByColors(String[][] data, Set selection, Set colors)
	{
		boolean changed = false;
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		{
			String key = data[i][j];
			if(colors.contains(key))
			{
				selection.add(i+"-"+j);
				changed = true;
			}
		}
		return changed;
	}
	
	private boolean selectByColor(String[][] data, Set selection, Color color) throws Exception
	{
		boolean changed = false;
		String key = (String) encodeColor.t(color);
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		{
			if(data[i][j].equals(key))
			{
				selection.add(i+"-"+j);
				changed = true;
			}
		}
		return changed;
	}
}