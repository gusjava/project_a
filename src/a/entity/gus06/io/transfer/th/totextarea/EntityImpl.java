package a.entity.gus06.io.transfer.th.totextarea;

import a.framework.*;
import javax.swing.JTextArea;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180220";}


	private Service areaToOutput;
	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		areaToOutput = Outside.service(this,"gus06.io.outputstream.textarea1");
		transfert = Outside.service(this,"gus06.io.transfer.th.toprintstream");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		JTextArea area = new JTextArea();
		
		link(is,area);
		return area;
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) o[0];
		JTextArea area = (JTextArea) o[1];
		
		link(is,area);
	}
	
	
	private void link(InputStream is, JTextArea area) throws Exception
	{
		OutputStream os = (OutputStream) areaToOutput.t(area);
		PrintStream p = new PrintStream(os);
		Thread th = (Thread) transfert.t(new Object[]{is,p});
		
		// link th to area ?
	}
}
