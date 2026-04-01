package a.entity.gus06.sys.script1.tool.execute.wrapping;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160415";}

	public static final String K_MAIN = "main";
	public static final String K_ARGS = "args";
	public static final String K_IF = "if";
	public static final String K_DEBUG = "debug";
	public static final String K_REPEAT = "repeat";
	public static final String K_REDIRECT = "redirect";
	public static final String K_RETURN = "return";
	public static final String K_ALTER = "alter";
	public static final String K_OP = "op";
	public static final String K_BEFORE = "before";
	public static final String K_AFTER = "after";
	public static final String K_E = "e";
	

	private Service getOutput;
	private Service getPool;
	private Service getParentPool;
	private Service handleReturn1;
	private Service handleExecute1;
	private Service initOp;
	private Service initAlter;
	private Service debugTag;
	private Service prepareRedirect;
	private Service executeString;


	public EntityImpl() throws Exception
	{
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
		getParentPool = Outside.service(this,"gus06.sys.script1.access.tag.parent0.stack1.pool1");
		handleReturn1 = Outside.service(this,"gus06.sys.script1.tool.execute.handle.return1");
		handleExecute1 = Outside.service(this,"gus06.sys.script1.tool.execute.handle.execute1");
		initOp = Outside.service(this,"gus06.sys.script1.tool.execute.init.op");
		initAlter = Outside.service(this,"gus06.sys.script1.tool.execute.init.alter");
		debugTag = Outside.service(this,"gus06.sys.script1.tool.debug.tag");
		prepareRedirect = Outside.service(this,"gus06.sys.script1.executor.type.el.r.redirect.prepare");
		executeString = Outside.service(this,"gus06.sys.script1.tool.execute.op.execute");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		P wrap = (P) o[2];
		T buildData = (T) o[3];
		
		V output = (V) getOutput.t(context);
		Object p0 = ((R) output).r("p0");
		
		Map data = (Map) buildData.t(new Object[]{context,tag});
		
		Object main = get(data,K_MAIN);
		Map args = (Map) get(data,K_ARGS);
		Boolean if1 = (Boolean) get(data,K_IF);
		String debug = (String) get(data,K_DEBUG);
		Integer repeat = (Integer) get(data,K_REPEAT);
		Object redirect = get(data,K_REDIRECT);
		String return1 = (String) get(data,K_RETURN);
		String e1 = (String) get(data,K_E);
		Map alter = (Map) get(data,K_ALTER);
		Map op = (Map) get(data,K_OP);
		Object before = get(data,K_BEFORE);
		Object after = get(data,K_AFTER);
		
		debugTag(debug,tag);
		
		if(if1!=null && !if1.booleanValue()) return;
		
		executeObj(context,before);
		setRedirect(output,context,tag,redirect);
		
		E e_alter = initAlter(context,alter);
		E e_op = initOp(context,op);
		
		Map pool1 = pool1(tag);
		if(args!=null) pool1.putAll(args);
		
		int times = computeTimes(repeat);
		
		try
		{
			for(int i=0;i<times;i++)
			wrap.p(new Object[]{context,tag,pool1,main,data});
		}
		finally
		{
			e_op.e();
			e_alter.e();
		
			if(redirect!=null) output.v("redirect",p0);
			if(return1!=null) handleReturn1.p(new Object[]{tag,return1});
			if(e1!=null) handleExecute1.p(new Object[]{tag,e1});
		}
		
		executeObj(context,after);
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private E initOp(Map context, Map data) throws Exception
	{return (E) initOp.t(new Map[]{context,data});}
	
	private E initAlter(Map context, Map data) throws Exception
	{return (E) initAlter.t(new Map[]{context,data});}
	
	private Map pool1(Object tag) throws Exception
	{return (Map) getPool.t(tag);}
	
	
	
	
	private int computeTimes(Integer repeat)
	{
		if(repeat==null) return 1;
		int times = repeat.intValue();
		return times>0 ? times : 0;
	}
	
	private void debugTag(String debug, Map tag) throws Exception
	{
		if(debug==null) return;
		debugTag.v(debug,tag);
	}
	
	private void setRedirect(V output, Map context, Map tag, Object redirect) throws Exception
	{
		if(redirect==null) return;
		
		Map parentPool = (Map) getParentPool.t(tag);
		Object redirectObj = prepareRedirect.t(new Object[]{context,parentPool,redirect});
		output.v("redirect",redirectObj);
	}
	
	
	
	private void executeObj(Map context, Object obj) throws Exception
	{
		if(obj==null) return;
		
		if(obj instanceof E)
		{((E) obj).e();return;}
		
		if(obj instanceof String)
		{executeString.p(new Object[]{context,obj});return;}
		
		throw new Exception("Invalid data type for execute operation: "+obj.getClass().getName());
	}
}
