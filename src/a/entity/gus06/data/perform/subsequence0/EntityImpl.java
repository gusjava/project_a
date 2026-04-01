package a.entity.gus06.data.perform.subsequence0;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20151119";}


	private Service stringSub;
	private Service listSub;
	private Service fileToList;
	private Service writeList;


	public EntityImpl() throws Exception
	{
		stringSub = Outside.service(this,"gus06.data.string.subsequence0");
		listSub = Outside.service(this,"gus06.data.list.subsequence0");
		fileToList = Outside.service(this,"gus06.file.read.string.list.autodetect");
		writeList = Outside.service(this,"gus06.file.write.string.list.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof String) return stringSub.t(obj);
		if(input instanceof List) return listSub.t(obj);
		if(input instanceof File) return listSub.t(fileToList(o[0],o[1]));
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	private Object fileToList(Object input, Object rule) throws Exception
	{
		input = fileToList.t(input);
		return new Object[]{input,rule};
	}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String rule = (String) o[1];
		
		if(input instanceof StringBuffer)
		{
			StringBuffer sb = (StringBuffer) input;
			String output = (String) stringSub.t(new Object[]{sb.toString(),rule});
			
			sb.delete(0, sb.length());
			sb.append(output);
			return;
		}
		if(input instanceof List)
		{
			List list = (List) input;
			List output = (List) listSub.t(new Object[]{list,rule});
			
			list.clear();
			list.addAll(output);
			return;
		}
		if(input instanceof File)
		{
			File file = (File) input;
			List list = (List) fileToList.t(file);
			List output = (List) listSub.t(new Object[]{list,rule});
			
			writeList.p(new Object[]{file,output});
			return;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
