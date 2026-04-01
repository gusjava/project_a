package a.entity.gus06.sys.webserver1.main.process;

import a.framework.*;
import java.util.Map;
import java.nio.channels.SocketChannel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141012";}

	public static final String KEY_CHANNEL = "channel";
	
	
	private Service formatOutput;
	private Service processor;
	
	
	public EntityImpl() throws Exception
	{
		formatOutput = Outside.service(this,"gus06.sys.webserver1.format.output");
		processor = Outside.service(this,"gus06.sys.webserver1.processor");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		Map output = (Map) processor.t(input);
		
		SocketChannel channel = (SocketChannel) input.get(KEY_CHANNEL);
		byte[] ba = (byte[]) formatOutput.t(output);
		return new Object[]{channel,ba};
	}
}
