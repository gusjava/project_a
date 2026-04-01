package a.entity.gus06.crypto.checksum.crc32.flyway;

import a.framework.*;
import java.util.zip.CRC32;
import java.io.BufferedReader;
import java.io.StringReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190328";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return crc((String) obj);
		throw new Exception("Invalid input: "+obj.getClass().getName());
	}
	
	private String crc(String str) throws Exception
	{
		final CRC32 crc32 = new CRC32();
		BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
		String line;
	    	while ((line = bufferedReader.readLine()) != null)
		crc32.update(line.getBytes("UTF-8"));
		return ""+(int) crc32.getValue();
	}
}
