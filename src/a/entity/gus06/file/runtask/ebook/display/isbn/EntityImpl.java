package a.entity.gus06.file.runtask.ebook.display.isbn;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251123";}


	private Service findISBN;
	private Service showMessage;
	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		findISBN = Outside.service(this,"gus06.file.info.ebook.isbn");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		String isbn = (String) findISBN.t(file);
		
		if(progress!=null) ((E)progress).e();
		
		if(isbn!=null) toClipboard.p(isbn);
		
		String message = isbn!=null ? isbn : "not found";
		showMessage.p(new String[]{isbn,"ISBN"});
	}
}
