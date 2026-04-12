package a.entity.gus06.io.transfer.tostring.autodetect;

import a.framework.*;
import java.nio.charset.Charset;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.mozilla.universalchardet.UniversalDetector;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250510";}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[4096];
		int nread;
		while ((nread = is.read(buf)) > 0)
		{baos.write(buf, 0, nread);}
		
		is.close();
		byte[] data = baos.toByteArray();
		
		int sampleSize = Math.min(data.length, 50000);
		byte[] sample = java.util.Arrays.copyOfRange(data, 0, sampleSize);
		
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(sample, 0, sample.length);
		detector.dataEnd();
		
		String name = detector.getDetectedCharset();
		Charset charset = name!=null ? Charset.forName(name) : Charset.defaultCharset();
		
		return new String(data, charset);
	}
}