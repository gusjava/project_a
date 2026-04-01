package a.entity.gus06.system.javalibrarypath.modify.perform2;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220312";}

	public static final String START = "-Djava.library.path=";
	
	private Service restart;

	public EntityImpl() throws Exception
	{
		restart = Outside.service(this,"gus06.app.restart");
	}

	public void p(Object obj) throws Exception
	{
		String options = START+obj;
		
		Map map = new HashMap();
		map.put("options",options);
//		restart.p(map);
	}
}