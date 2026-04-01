package a.entity.gus06.io.inputstream.string.info.charset;

import a.framework.*;

import java.io.InputStream;
import java.nio.charset.Charset;
import org.mozilla.universalchardet.UniversalDetector;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250509";}
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		if(is==null) return null;
		
		UniversalDetector detector = new UniversalDetector(null);

		byte[] buf = new byte[4096];
		int nread;
		
		while((nread = is.read(buf)) > 0 && !detector.isDone())
		detector.handleData(buf, 0, nread);
		
		detector.dataEnd();
		is.close();

		String name = detector.getDetectedCharset();
		
		detector.reset();
		if(name==null) return Charset.defaultCharset();
		
		// BUG de d�tection quand on a un fichier texte windows-1252 avec des ��
		if(name.equals("WINDOWS-1255")) name = "WINDOWS-1252";
		
		try{return Charset.forName(name);}
		catch(Exception e)
		{
    			String message = "Charset not found for name: "+name;
    			Outside.err(this,"t(Object)",new Exception(message,e));
    			return Charset.defaultCharset();
		}
	}
}