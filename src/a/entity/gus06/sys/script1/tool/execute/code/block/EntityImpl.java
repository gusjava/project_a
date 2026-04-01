package a.entity.gus06.sys.script1.tool.execute.code.block;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150906";}
	
	public static final String MODEVALUE_INDENT = "indent";
	public static final String MODEVALUE_END = "end";


	private Service execute;
	private Service formatCurly;
	private Service buildList;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.script1.engine.fromtext");
		formatCurly = Outside.service(this,"gus06.string.transform.format.brackets.curly");
		buildList = Outside.service(this,"gus06.sys.script1.tool.execute.code.block.buildlist");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String code = (String) o[1];
		String mode = (String) o[2];
		String indent = (String) o[3];
		
		if(mode==null) mode = MODEVALUE_INDENT;
		if(indent==null) indent = " ";
		
		perform(context,code,mode,indent);
	}
	
	
	
	
	private void perform(Map context, String code, String mode, String indent) throws Exception
	{
		String[] lines = code.split("\n");
		if(mode.equals(MODEVALUE_END)) indent = null;
		List list = (List) buildList.t(new Object[]{lines,indent});
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			String s = (String) list.get(i);
			b.append("{"+formatCurly.t(s)+"}");
		}
		
		String script = b.toString();
		execute.p(new Object[]{script,context});
	}
}
