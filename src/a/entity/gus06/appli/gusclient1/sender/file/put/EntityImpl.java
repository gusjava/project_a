package a.entity.gus06.appli.gusclient1.sender.file.put;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20141017";}

	public static final int BLOCKSIZE = 10000; // 10 kb


	private Service sender;
	private Service signer;
	private Service generateId;
	private Service byteToBase64;
	
	private Object progress;
	


	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.gusclient1.sender");
		signer = Outside.service(this,"gus06.sys.crypto.pseudo.sign2");
		generateId = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		byteToBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("progress")) {progress = obj;return;}
		uploadFile(key,(File) obj);
	}
	
	
	
	
	private void uploadFile(String path, File file) throws Exception
	{
		String sign = (String) signer.t(file);
		if(sign==null) throw new Exception("Sign could not be generated");
		
		long size = file.length();
		String id = (String) generateId.g();
		
		int number = (int) (size/BLOCKSIZE);
		int tail = (int) (size - BLOCKSIZE*number);
		
		Map map = new HashMap();
		
		map.put("cmd","put:"+id);
		map.put("cmd1","init");
		map.put("file-path",path);
		map.put("file-sign",sign);
		map.put("file-size",""+size);
		map.put("block-nb",""+number);
		map.put("block-size",""+BLOCKSIZE);
		map.put("tail",""+tail);
		
		boolean isCancelled = send(map);
		if(isCancelled) return;
		
		
		FileInputStream fis = new FileInputStream(file);
		
		try
		{
			map = new HashMap();
			map.put("cmd","put:"+id);
		
			initProgress(number);
			for(int i=0;i<number;i++)
			{
				map.put("cmd1","send "+i+"/"+number);
				map.put("data",buildBlock(fis,BLOCKSIZE,i));
				map.put("index",""+i);
				send(map);
				executeProgress();
			}
		
			if(tail>0)
			{
				map.put("cmd1","send tail");
				map.put("data",buildBlock(fis,tail,number));
				map.put("index",""+number);
				send(map);
			}
		}
		finally{fis.close();}
	}
	
	
	
	
	
	private String buildBlock(FileInputStream fis, int size, int index) throws Exception
	{
		byte[] ba = new byte[size];
		int r = fis.read(ba);
		if(r!=size) throw new Exception("Failed to build block at index: "+index);
		return (String) byteToBase64.t(ba);
	}
	
	
	private boolean send(Map map) throws Exception
	{
		String res = (String) sender.t(map);
		if(res.startsWith("error:")) throw new Exception("File sending failed: "+res.substring(6));
		return res.equals("cancelled");
	}
	
	
	
	private void initProgress(int size) throws Exception
	{if(progress!=null) ((V) progress).v("size",""+size);}
	
	
	private void executeProgress() throws Exception
	{if(progress!=null) ((E) progress).e();}
}
