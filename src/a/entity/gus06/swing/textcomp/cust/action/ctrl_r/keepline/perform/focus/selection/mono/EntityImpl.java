package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.selection.mono;

import a.framework.*;
import javax.swing.text.*;
import java.util.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service handleRangeMulti;
	private Service focusSelection;
	
	public EntityImpl() throws Exception
	{
		handleRangeMulti = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange.multi");
		focusSelection = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.focus.selection");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		Element root = document.getDefaultRootElement();
		int number = root.getElementCount();
		
		if(number==1)
		{
			focusSelection.p(comp);
			return;
		}
		
		String selection = comp.getSelectedText();
		Set<Integer> indexes = new HashSet<>();
		
		for(int i=0;i<number;i++)
		{
			Element element = root.getElement(i);
			int start = element.getStartOffset();
			int end = element.getEndOffset();
			if(end>length) end = length;
			
			String line = comp.getText(start,end-start);
			if(line.contains(selection))
			indexes.add(Integer.valueOf(i));
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
