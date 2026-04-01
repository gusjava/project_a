package a.entity.gus06.sys.xhtml1.ids.gui.detail.viewer;

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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20220909";}
	
	public static final Color COLOR1 = new Color(191,127,255);
	public static final Color COLOR2 = new Color(242,229,255);


	private Service editor;
	private Service labelHolder;
	private Service findPainter;
	
	private JPanel panel;
	private JTextComponent comp;
	private Highlighter high;
	
	private File file;
	private String location;
	private String id;
	



	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.ext.txt");
		labelHolder = Outside.service(this,"*gus06.sys.xhtml1.ids.gui.detail.label");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) labelHolder.i(),BorderLayout.NORTH);
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
		Object[] info = (Object[]) obj;
		if(info==null) {reset();return;}
		
		file = (File) info[0];
		location = (String) info[1];
		id = (String) info[2];
		
		labelHolder.p(location);
		editor.p(file);
		
		String regex = "id=\"([^\"]+)\"";
		Pattern p = Pattern.compile(regex,Pattern.DOTALL);
		
		Highlighter.HighlightPainter painter1 = (Highlighter.HighlightPainter) findPainter.t(COLOR1);
		Highlighter.HighlightPainter painter2 = (Highlighter.HighlightPainter) findPainter.t(COLOR2);
		
		high.removeAllHighlights();
		String text = comp.getText();
		
		int start1 = 0;
		int end1 = 0;
		
		Matcher m = p.matcher(text);
		while(m.find())
		{
			int start = m.start(1);
			int end = m.end(1);
			String group = m.group(1);
			
			boolean isID = group.equals(id);
			if(isID)
			{
				start1 = start;
				end1 = end;
			}
		
			high.addHighlight(start, end, isID ? painter1 : painter2);
		}
		
		if(end1!=0)
		{
			final int start1_ = start1;
			final int end1_ = end1;
			
			SwingUtilities.invokeLater(()->{
				comp.select(start1_,end1_);
			});
		}
	}
	
	
	
	private void reset() throws Exception
	{
		file = null;
		location = null;
		id = null;
		
		labelHolder.p(null);
		editor.p(null);
		high.removeAllHighlights();
	}
}