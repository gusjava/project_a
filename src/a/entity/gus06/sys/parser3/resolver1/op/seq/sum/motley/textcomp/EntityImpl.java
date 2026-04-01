package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.textcomp;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170317";}


	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		JTextComponent comp = (JTextComponent) oo[0];
		
		String text = buildText(oo);
		
		Document doc = comp.getDocument();
		int len = doc.getLength();
		doc.insertString(len,text,null);
		
		return doc;
	}
	
	
	private String buildText(Object[] oo) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=1;i<oo.length;i++)
		b.append(toString(oo[i]));
		return b.toString();
	}
	
	private String toString(Object o) throws Exception
	{
		if(o==null) return "null";
		
		String s = o.toString();
		String h = Integer.toHexString(o.hashCode());
		
		if(s.endsWith("@"+h)) throw new Exception("Object not compatible with String: "+o);
		return s;
	}
}
