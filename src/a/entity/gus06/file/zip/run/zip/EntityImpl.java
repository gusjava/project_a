package a.entity.gus06.file.zip.run.zip;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150605";}
	
	public static final Charset CHARSET = Charset.forName("Cp437");
	public static int BUFFER = 2048;
	public static int LEVEL = 7;


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.file.zip.run.zip.buildmap");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		File zipFile = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		Map map = (Map) buildMap.t(input);
		List entryList = new ArrayList(map.keySet());
		int number = entryList.size();
		
		ZipOutputStream zos = null;
		
		try
		{
			zipFile.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(zipFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos,CHARSET);
        
			zos.setMethod(ZipOutputStream.DEFLATED);
			zos.setLevel(LEVEL);
		
			if(progress!=null) ((V)progress).v("size",""+number);
			for(int i=0;i<number;i++)
			{
				String name = (String) entryList.get(i);
				File f = (File) map.get(name);
			
				ZipEntry entry = new ZipEntry(name);
				zos.putNextEntry(entry);
				
				if(f.isFile())
				{
					int b;
					byte data[] = new byte[BUFFER];
					FileInputStream fis = new FileInputStream(f);
					while((b = fis.read(data,0,BUFFER))!=-1)
					{zos.write(data,0,b);}
					fis.close();
				}
            
				zos.closeEntry();
				
				if(progress!=null) ((E)progress).e();
				if(interrupt!=null && !interrupt.isEmpty()) break;
			}
		}
		finally
		{
			if(zos!=null)
			{
				zos.finish();
				zos.close();
			}
		}
	}
}