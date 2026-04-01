package a.entity.gus06.file.perform.copydown;

import a.framework.*;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20160715";}


	private Service copyOp;


	public EntityImpl() throws Exception
	{
		copyOp = Outside.service(this,"gus06.file.op.copy.autorename");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String sub = (String) o[1];
		
		File parent = file.getParentFile();
		File dir = new File(parent,sub);
		if(dir==null) return null;
		
		File out = new File(dir,file.getName());
		copyOp.p(new File[]{file,out});
		return out;
	}
}
