package a.entity.gus06.swing.textcomp.cust.action.ctrl_j.high1.perform.all;

import a.framework.*;
import javax.swing.text.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160507";}

	public static final String PAINTER_KEY = "high1";
	

	private Service getPainter;
	private DefaultHighlighter.DefaultHighlightPainter painter;
	
	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		Highlighter high = comp.getHighlighter();
		
		
		int p = comp.getCaretPosition();
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element element = document.getParagraphElement(p);
		
		int start = element.getStartOffset();
		int end = element.getEndOffset();
		String line = comp.getText(start,end-start);
		
		String delim = findDelim(line);
		
		Element root = document.getDefaultRootElement();
		int number = root.getElementCount();
		int length = document.getLength();
		
		for(int i=0;i<number;i++)
		{
			Element element0 = root.getElement(i);
			
			int start0 = element0.getStartOffset();
			int end0 = element0.getEndOffset();
			String line0 = comp.getText(start0,end0-start0);
			
			List ranges = findRanges(line0,delim);
			for(int j=0;j<ranges.size();j++)
			{
				int[] r = (int[]) ranges.get(j);
				int a1 = Math.min(start0+r[0],end0);
				int a2 = Math.min(start0+r[1],end0);
				if(a1!=a2)  high.addHighlight(a1,a2,painter);
			}
		}
	}
	
	
	
	private String findDelim(String line)
	{
		if(line.contains("\t")) return "\t";
		if(line.contains(";")) return ";";
		if(line.contains(" ")) return " ";
		return null;
	}
	
	
	
	private List findRanges(String line, String delim)
	{
		
		List ranges = new ArrayList();
		
		if(delim==null)
		{
			ranges.add(new int[]{0,line.length()});
			return ranges;
		}
		
		int a = 0;
		int l = line.length();
		char c = delim.charAt(0);
		
		for(int i=0;i<l;i++)
		if(line.charAt(i)==c)
		{
			ranges.add(new int[]{a,i});
			a=i+1;
		}
		ranges.add(new int[]{a,l-1});
		
		return ranges;
	}
}
