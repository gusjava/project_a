package a.entity.gus06.data.perform.transfer;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180319";}


	private Service perform;
	private Service buildInput;
	private Service buildOutput;
	private Service buildInputP;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.io.transfer");
		buildInput = Outside.service(this,"gus06.find.inputstream");
		buildOutput = Outside.service(this,"gus06.find.outputstream");
		buildInputP = Outside.service(this,"gus06.io.inputstream.build.withprogress");
	}

	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map) {handleMap((Map) obj);return;}
		if(obj instanceof List) {handleList((List) obj);return;}
		if(obj instanceof Object[]) {handleArray((Object[]) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void handleMap(Map m) throws Exception
	{
		Object input = get(m,"input");
		Object output = get(m,"output");
		Object progress = get0(m,"progress");
		Object size = get0(m,"size");
		
		InputStream is = null;
		if(progress!=null) is = (InputStream) buildInputP.t(new Object[]{input, progress, size});
		else is = (InputStream) buildInput.t(input);
		
		OutputStream os = (OutputStream) buildOutput.t(output);
		
		perform.p(new Object[]{is,os});
	}
	
	private void handleList(List l) throws Exception
	{
		if(l.size()!=2) throw new Exception("Invalid element number: "+l.size());
		Object o1 = l.get(0);
		Object o2 = l.get(1);
		
		InputStream is = (InputStream) (o1 instanceof InputStream ? o1 : o2);
		OutputStream os = (OutputStream) (o1 instanceof OutputStream ? o1 : o2);
		perform.p(new Object[]{is,os});
	}
	
	private void handleArray(Object[] a) throws Exception
	{
		if(a.length!=2) throw new Exception("Invalid element number: "+a.length);
		Object o1 = a[0];
		Object o2 = a[1];
		
		InputStream is = (InputStream) (o1 instanceof InputStream ? o1 : o2);
		OutputStream os = (OutputStream) (o1 instanceof OutputStream ? o1 : o2);
		perform.p(new Object[]{is,os});
	}
	
	
	private Object get(Map m, String key) throws Exception
	{
		if(!m.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return m.get(key);
	}
	
	private Object get0(Map m, String key)
	{
		if(!m.containsKey(key)) return null;
		return m.get(key);
	}
}