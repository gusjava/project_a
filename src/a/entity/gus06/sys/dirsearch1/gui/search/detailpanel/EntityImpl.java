package a.entity.gus06.sys.dirsearch1.gui.search.detailpanel;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;
import java.util.Map;
import java.awt.Color;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20200127";}

	private Service fileLabel;
	private Service editor;
	private Service findPainter;
	
	private JPanel panel;
	private JTextComponent comp;
	private Highlighter high;
	
	private R selection;
	private File file;
	private int pos;
	private Map map;

	public EntityImpl() throws Exception
	{
		fileLabel = Outside.service(this,"*gus06.swing.label.hold.file");
		editor = Outside.service(this,"*gus06.file.editor.ext.txt");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) fileLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) editor.i(),BorderLayout.CENTER);
		
		comp = (JTextComponent) editor.r("comp");
		high = comp.getHighlighter();
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void e() throws Exception
	{reset();}
	
	public void p(Object obj) throws Exception
	{
		selection = (R) obj;
		if(selection==null) {reset();return;}
		
		file = (File) selection.r("file");
		pos = (int) selection.r("pos");
		map = (Map) selection.r("pos_color_section");
		
		fileLabel.p(file);
		editor.p(file);
		
		
		high.removeAllHighlights();
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		int offset = 0;
		int i1 = 0;
		int[] selection = null;
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			if(map.containsKey(i))
			{
				Set set = (Set) map.get(i);
				if(!set.isEmpty())
				{
					int d = Math.abs(pos-i);
					int d1 = Math.abs(pos-i1);
					
					if(d<d1)
					{
						i1 = i;
						selection = null;
					}
					
					Iterator it = set.iterator();
					while(it.hasNext())
					{
						Object[] data = (Object[]) it.next();
						Color color = (Color) data[0];
						List sections = (List) data[1];
						
						Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) findPainter.t(color);
						for(int j=0;j<sections.size();j++)
						{
							int[] tt = (int[]) sections.get(j);
							int start = offset+tt[0];
							int end = offset+tt[1];
							high.addHighlight(start,end,painter);
							
							if(selection==null) selection = new int[]{start,end};
						}
					}
				}
			}
			
			offset += line.length()+1;
		}
		
		if(selection!=null)
		{
			comp.requestFocusInWindow();
			comp.select(selection[0],selection[1]);
		}
	}
	
	private void reset() throws Exception
	{
		file = null;
		pos = -1;
		fileLabel.p(null);
		editor.p(null);
	}
}