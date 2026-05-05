package a.entity.gus06.file.runtask.string.smartreplace.duplicate;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190717";}


	private Service replace;
	private Service input;
	private Service isTextFile;
	private Service transformFile;
	private Service copyFile;
	private Service getName0Ext;
	
	
	public EntityImpl() throws Exception
	{
		replace = Outside.service(this,"gus06.data.string.replace1.smart");
		input = Outside.service(this,"gus06.input.text.dialog");
		isTextFile = Outside.service(this,"gus06.file.string.check");
		transformFile = Outside.service(this,"gus06.file.string.perform2.apply.t");
		copyFile = Outside.service(this,"gus06.file.op.copy.replace");
		getName0Ext = Outside.service(this,"gus.x.file.getname0ext");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String search = (String) input.t("Search string:");
		if(search==null) return;
		
		String replacement = (String) input.t("Replacement string:");
		if(replacement==null) return;
		
		String[] kk = (String[]) getName0Ext.t(file);
		String name = kk[0];
		String ext = kk[1];
		
		String[] nn = replacement.split(";");
		int nb = nn.length;
		
		T[] t = new T[nb];
		for(int i=0;i<nb;i++)
		t[i] = new T1(search,nn[i]);
		
		File[] file1 = new File[nb];
		for(int i=0;i<nb;i++)
		{
			String name1 = (String) t[i].t(name);
			file1[i] = new File(file.getParentFile(),name1+"."+ext);
		}
		
		
		if(progress!=null) ((V)progress).v("size",""+nb);
		
		for(int i=0;i<nb;i++)
		{
			handleFile(file,file1[i],t[i]);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private void handleFile(File f0, File f1, T t) throws Exception
	{transformFile.f(new Object[]{f0,f1,t});}
	
	
	
	
	private class T1 implements T
	{
		private String search;
		private String replacement;
		
		public T1(String search, String replacement)
		{
			this.search = search;
			this.replacement = replacement;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			return replace.t(new String[]{s,search,replacement});
		}
	}
}
