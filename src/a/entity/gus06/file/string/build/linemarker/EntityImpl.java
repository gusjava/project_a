package a.entity.gus06.file.string.build.linemarker;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190407";}


	private Service readFile;


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.array.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		F[] filters = (F[]) o[1];
		
		String[] lines = (String[]) readFile.t(file);
		int nb = lines.length;
		
		int[] m = new int[nb];
		for(int i=0;i<nb;i++)
		m[i] = compute(lines[i],filters);
		
		return m;
	}
	
	
	private int compute(String line, F[] filters) throws Exception
	{
		for(int i=0;i<filters.length;i++)
		if(filters[i].f(line)) return i;
		return -1;
	}
}
