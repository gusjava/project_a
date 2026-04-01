package a.entity.gus06.sys.webserver1.web2.site.pseudo.execute.op.put;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141017";}


	private Service stringToBA;
	private Service verify2;
	private PrintStream out;



	public EntityImpl() throws Exception
	{
		stringToBA = Outside.service(this,"gus06.convert.stringtobytearray.base64");
		verify2 = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.verify2.hexa");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		String cmd1 = (String) mr.r("params_post cmd1");
		if(cmd1==null) throw new Exception("Cmd1 not found inside POST");
		
		if(cmd1.equals("init")) init(mr,info);
		else receive(mr,info);
	}
	
	
	
	
	private void init(R mr, String info) throws Exception
	{
		V mv = (V) mr;
		Map map = new HashMap();
		mv.v("session "+info,map);
		
		File dir0 = (File) mr.r("data dirs resource0");
		String pseudo = (String) mr.r("session auth pseudo");
		String path = (String) mr.r("params_post file-path");
		String size = (String) mr.r("params_post file-size");
		String sign = (String) mr.r("params_post file-sign");
		
		File dir1 = new File(dir0,"pseudo");
		File dir2 = new File(dir1,pseudo);
		File file = new File(dir2,format(path));
		
		map.put("file",file);
		map.put("size",size);
		map.put("sign",sign);
		
		if(file.exists())
		{
			boolean ok = checkFile(mr,file,sign);
			if(ok)
			{
				print(mr,"cancelled");
				out.println("File cancelled: "+file); 
			}
			else delete(file);
		}
		else file.getParentFile().mkdirs();
	}
	
	
	
	
	private void receive(R mr, String info) throws Exception
	{
		File file = (File) mr.r("session "+info+" file");
		String size = (String) mr.r("session "+info+" size");
		String sign = (String) mr.r("session "+info+" sign");
		
		String data = (String) mr.r("params_post data");
		byte[] b = (byte[]) stringToBA.t(data);
		FileOutputStream fos = new FileOutputStream(file,true);
		try{fos.write(b);}
		finally{fos.close();}
		
		long size_ = Long.parseLong(size);
		long size1 = file.length();
		
		if(size1>size_) failed(file,"size over reached");
		
		if(size1==size_)
		{
			boolean ok = checkFile(mr,file,sign);
			if(ok) out.println("File received: "+file);
			else failed(file,"wrong sign");
		}
	}
	
	
	
	
	
	
	
	
	private boolean checkFile(R mr, File file, String sign) throws Exception
	{
		PublicKey key = (PublicKey) mr.r("session auth key");
		return verify2.f(new Object[]{key,file,sign});
	}
	
	
	private void failed(File file, String message) throws Exception
	{
		delete(file);
		throw new Exception("Failed to put file: "+file+" ("+message+")");
	}
	
	
	
	private String format(String path)
	{return path.replace("\\",File.separator).replace("/",File.separator);}
	
	
	
	private void delete(File file) throws Exception
	{
		boolean r = file.delete();
		if(!r) throw new Exception("File could not be deleted: "+file);
	}
	
	
	private void print(R mr, String res) throws Exception
	{((P) mr.r("data h")).p(res);}
}
