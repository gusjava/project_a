package a.entity.gus06.sys.expression1.apply.op._inputstream;

import a.framework.*;
import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JTextField;
import java.util.zip.ZipEntry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}


	private Service convertProperties;
	private Service convertString;
	private Service convertJTextField;
	
	
	public EntityImpl() throws Exception
	{
		convertProperties = Outside.service(this,"gus06.convert.propertiestoinputstream");
		convertString = Outside.service(this,"gus06.convert.stringtoinputstream.utf8");
		convertJTextField = Outside.service(this,"gus06.io.inputstream.textfield");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Process) return ((Process) obj).getInputStream();
		if(obj instanceof Socket) return ((Socket) obj).getInputStream();
		if(obj instanceof File) return new FileInputStream((File) obj);
		if(obj instanceof byte[]) return new ByteArrayInputStream((byte[]) obj);
		if(obj instanceof Properties) return convertProperties.t(obj);
		if(obj instanceof String) return convertString.t(obj);
		if(obj instanceof JTextField) return convertJTextField.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}