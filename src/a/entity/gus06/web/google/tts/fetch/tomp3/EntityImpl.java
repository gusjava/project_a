package a.entity.gus06.web.google.tts.fetch.tomp3;

import a.framework.*;
import java.net.URLEncoder;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() { return "20250911"; }
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);
		
		String text = (String) o[0];
		String lang = (String) o[1];
		Boolean slow = (Boolean) o[2];
		File mp3File = (File) o[3];
		
		String urlStr = "https://translate.google.com/translate_tts?ie=UTF-8&q="
		+ URLEncoder.encode(text, "UTF-8")
		+ "&tl=" + lang
		+ "&client=tw-ob"
		+ "&slow=" + (slow ? "1" : "0");
		
		// Télécharger le MP3
		try (InputStream in = new URL(urlStr).openStream();
		FileOutputStream fos = new FileOutputStream(mp3File))
		{
			byte[] buffer = new byte[4096];
			int read;
			while ((read = in.read(buffer)) != -1)
			{fos.write(buffer, 0, read);}
		}
	}
}