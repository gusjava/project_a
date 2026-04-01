package a.entity.gus06.sys.expression1.apply.op._outputstream;

import a.framework.*;
import java.net.Socket;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}


	private Service textpaneToOutputstream;
	private Service textareaToOutputstream;
	private Service stringBuilderToOutputStream;
	private Service stringBufferToOutputStream;

	public EntityImpl() throws Exception
	{
		textpaneToOutputstream = Outside.service(this,"gus06.io.outputstream.textpane1.shell");
		textareaToOutputstream = Outside.service(this,"gus06.io.outputstream.textarea1");
		stringBuilderToOutputStream = Outside.service(this,"gus06.io.outputstream.stringbuilder");
		stringBufferToOutputStream = Outside.service(this,"gus06.io.outputstream.stringbuffer");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JTextPane) return textpaneToOutputstream.t(obj);
		if(obj instanceof JTextArea) return textareaToOutputstream.t(obj);
		if(obj instanceof StringBuilder) return stringBuilderToOutputStream.t(obj);
		if(obj instanceof StringBuffer) return stringBufferToOutputStream.t(obj);
		if(obj instanceof Process) return ((Process) obj).getOutputStream();
		if(obj instanceof Socket) return ((Socket) obj).getOutputStream();
		if(obj instanceof File) return new FileOutputStream((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
