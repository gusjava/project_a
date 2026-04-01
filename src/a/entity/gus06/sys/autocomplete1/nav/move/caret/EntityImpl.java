package a.entity.gus06.sys.autocomplete1.nav.move.caret;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.*;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160516";}
	
	

	
	public EntityImpl() throws Exception
	{
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String[] n = key.split(" ");
		
		if(n.length==1) move(comp,n[0]);
		else if(n.length==2) move(comp,n[0],n[1]);
		else throw new Exception("Invalid key: "+key);
	}
	
	
	
	
	private void move(JTextComponent comp, String v)
	{
		int length = comp.getDocument().getLength();
		int pos = findPos(v,length);
		comp.setCaretPosition(pos);
	}

	
	
	private void move(JTextComponent comp, String v1, String v2)
	{
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int count = root.getElementCount();
		
		int lineNb = findLineNb(v1,count);
		
		Element element = root.getElement(lineNb);
		int start = element.getStartOffset();
		int end = element.getEndOffset();
		
		int pos = findPos(v2,end-start-1);
		comp.setCaretPosition(start+pos);
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
	
	
	private int findPos(String key, int count)
	{
		if(key.equals("?")) return rand(count);
		if(key.equals("end")) return count;
		
		int n = Integer.parseInt(key);
		if(n<0 && n>=-count) return n+count;
		return Math.min(n,count);
	}
	
	private int rand(int n)
	{return (int)(Math.random()*n);}
}
