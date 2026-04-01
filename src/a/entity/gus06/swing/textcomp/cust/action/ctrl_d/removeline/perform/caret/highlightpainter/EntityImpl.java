package a.entity.gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.caret.highlightpainter;

import a.framework.*;
import javax.swing.text.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}


	private Service findForPainter;

	public EntityImpl() throws Exception
	{
		findForPainter = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) o[1];
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int length = document.getLength();
		int number = root.getElementCount();
		
		Set<Integer> indexes = new HashSet<>();
		List l = (List) findForPainter.t(new Object[]{comp,painter});
		
		for(int i=l.size()-1;i>=0;i--)
		{
			Highlighter.Highlight ht = (Highlighter.Highlight) l.get(i);
			
			int start = ht.getStartOffset();
			int end = ht.getEndOffset();
			
			int startLine = root.getElementIndex(start);
			int endLine = root.getElementIndex(end);
			
			for(int j=startLine;j<=endLine;j++)
			indexes.add(Integer.valueOf(j));
		}
		
		((V) comp).v("undoable","true");
		
		for(int i=number-1;i>=0;i--)
		{
			Element element = root.getElement(i);
			if(indexes.contains(Integer.valueOf(i)))
			{
				int start = element.getStartOffset();
				int end = element.getEndOffset();
				if(end>length) end = length;
				
				document.remove(start,end-start);
			}
		}
		
		((V) comp).v("undoable","false");
	}
}
