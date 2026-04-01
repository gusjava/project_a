package a.entity.gus06.mysql.socketfactory.wrapper;

import a.framework.*;
import java.net.Socket;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170207";}

	private Service wrapper;
	private T t_input;
	private T t_output;

	public EntityImpl() throws Exception
	{
		wrapper = Outside.service(this,"gus06.socket.wrapper.io");
	}
	
	public Object t(Object obj) throws Exception
	{
		Socket socket = (Socket) obj;
		return wrapper.t(new Object[]{socket,t_input,t_output});
	}
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			t_input = null;
			t_output = null;
			return;
		}
		
		T[] o = (T[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		t_input = o[0];
		t_output = o[1];
	}
}
