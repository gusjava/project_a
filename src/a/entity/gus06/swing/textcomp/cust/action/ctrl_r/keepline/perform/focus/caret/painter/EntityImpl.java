package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret.painter;

import a.framework.*;
import javax.swing.text.*;
import java.util.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service handleRangeMulti;
	private Service findHigh;
	
	public EntityImpl() throws Exception
	{
		handleRangeMulti = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange.multi");
		findHigh = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		
		List high = (List) findHigh.t(obj);
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int length = document.getLength();
		
		Set<Integer> indexes = new HashSet<>();
		
		for(int i=0;i<high.size();i++)
		{
			Highlighter.Highlight h = (Highlighter.Highlight) high.get(i);
			int start = h.getStartOffset();
			int end = h.getEndOffset();
			
			int startIndex = root.getElementIndex(start);
			int endIndex = root.getElementIndex(end);
			
			for(int j=startIndex;j<=endIndex;j++)
			indexes.add(Integer.valueOf(j));
		}
		
		
		List<Integer> indexes_ = new ArrayList<>(indexes);
		Collections.sort(indexes_);
		
		int[] range = new int[indexes_.size()*2];
		
		for(int i=0;i<indexes_.size();i++)
		{
			Integer index = indexes_.get(i);
			Element element = root.getElement(index.intValue());
			
			int start = element.getStartOffset();
			int end = element.getEndOffset();
			end--;
				
			range[2*i] = start;
			range[2*i+1] = end;
		}
		handleRangeMulti.p(new Object[]{comp,range});
	}
}
