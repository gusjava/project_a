package a.entity.gus06.data.perform.transferto;

import a.framework.*;
import java.io.InputStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180322";}


	private Service perform;
	private Service buildInput;
	private Service buildOutput;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.io.transfer");
		buildInput = Outside.service(this,"gus06.find.inputstream");
		buildOutput = Outside.service(this,"gus06.find.outputstream");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) buildInput.t(o[0]);
		OutputStream os = (OutputStream) buildOutput.t(o[1]);
		perform.p(new Object[]{is,os});
	}
}
