package a.entity.gus06.sys.autocomplete1.nav.move.line;

import a.framework.*;
import javax.swing.text.*;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160516";}
	
	

	
	public EntityImpl() throws Exception
	{
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int count = root.getElementCount();
		
		int lineNb = findLineNb(key,count);
		
		Element element = root.getElement(lineNb);
		int start = element.getStartOffset();
		comp.setCaretPosition(start);
	}
	
	
	
	private int findLineNb(String key, int count)
	{
		if(key.equals("?")) return rand(count);
		if(key.equals("end")) return count-1;
		
		int n = Integer.parseInt(key);
		if(n>0) return Math.min(n,count)-1;
		if(n<0 && n>=-count) return n+count;
		
		return 0;
	}
	
	private int rand(int n)
	{return (int)(Math.random()*n);}
}
