package a.entity.gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q2;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200308";}


	private Service performZ2;

	public EntityImpl() throws Exception
	{
		performZ2 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q3");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getText();
		int length = text.length();
		int start0 = comp.getSelectionStart()-1;
		int end0 = comp.getSelectionEnd();
		
		int start1 = start0;
		int end1 = end0;
		
		int start2 = start0;
		int end2 = end0;
		
		while(start2>=0 && !isLineDelim(text.charAt(start2)))
		{
			if(!isWhiteChar(text.charAt(start2))) start1 = start2-1;
			start2--;
		}
		while(end2<length && !isLineDelim(text.charAt(end2)))
		{
			if(!isWhiteChar(text.charAt(end2))) end1 = end2+1;
			end2++;
		}
		
		if(start0==start2 && end0==end2)
		{performZ2.p(comp);return;}
		
		if(start1<start0 || end1>end0)
			comp.select(start1+1,end1);
		else comp.select(start2+1,end2);
	}
	
	
	
	private boolean isLineDelim(char c)
	{return c=='\n' || c=='\r';}
	
	private boolean isWhiteChar(char c)
	{return c==' ' || c=='\t';}
}