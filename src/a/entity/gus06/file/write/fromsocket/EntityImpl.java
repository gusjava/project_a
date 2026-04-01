package a.entity.gus06.file.write.fromsocket;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180321";}


	private Service transfert;
	private Service findSocket;
	
	public EntityImpl() throws Exception
	{
		transfert = Outside.service(this,"gus06.io.transfer");
		findSocket = Outside.service(this,"gus06.find.socket");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Socket socket = (Socket) findSocket.t(o[1]);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		InputStream is = socket.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
	}
}
