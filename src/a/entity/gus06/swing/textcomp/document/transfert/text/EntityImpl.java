package a.entity.gus06.swing.textcomp.document.transfert.text;

import a.framework.*;
import javax.swing.text.Document;
import javax.swing.text.AbstractDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200103";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Document doc1 = (Document) o[0];
		Document doc2 = (Document) o[1];
		
		String text = doc1.getText(0,doc1.getLength());
		setText(doc2,text);
	}

	private void setText(Document doc, String text) throws Exception
	{
		if(doc instanceof AbstractDocument)
                ((AbstractDocument)doc).replace(0,doc.getLength(),text,null);
		else
		{
			doc.remove(0,doc.getLength());
			doc.insertString(0,text,null);
		}
	}
}
