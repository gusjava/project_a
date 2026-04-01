package a.entity.gus06.file.string.info.charset;

import a.framework.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import org.mozilla.universalchardet.UniversalDetector;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150822";}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile()) return null;
		
		// 1. Check BOM
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)))
		{
			bis.mark(4);
			int b1 = bis.read();
			int b2 = bis.read();
			int b3 = bis.read();
			bis.reset();
			
			// UTF-8 with BOM
			if (b1 == 0xEF && b2 == 0xBB && b3 == 0xBF)
			return StandardCharsets.UTF_8; 
		}

//		// 2. Try reading with UTF-8
//		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
//		{
//			char[] buffer = new char[1024];
//			while (reader.read(buffer) != -1){}
//			return StandardCharsets.UTF_8;
//		}
//		catch (Exception e) {}
		
		// 3. Use UniversalDetector
		FileInputStream fis = new FileInputStream(file);
		UniversalDetector detector = new UniversalDetector(null);

		byte[] buf = new byte[4096];
		int nread;
		
		while((nread = fis.read(buf)) > 0 && !detector.isDone())
		detector.handleData(buf, 0, nread);
		
		detector.dataEnd();
		fis.close();

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