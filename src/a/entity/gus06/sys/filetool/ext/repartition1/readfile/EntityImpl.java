package a.entity.gus06.sys.filetool.ext.repartition1.readfile;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151020";}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		int size = (int) file.length();
		
		int[] a = new int[size];
		boolean same = true;
		
		FileInputStream fis = new FileInputStream(file);
		for(int i=0;i<size;i++)
		{
			a[i] = ((int) fis.read())-48;
			if(i>0 && a[i]!=a[0]) same = false;
		}
		fis.close();
		
		Map m = new HashMap();
		m.put("values",a);
		m.put("same",Boolean.valueOf(same));
		
		return m;
	}
}
