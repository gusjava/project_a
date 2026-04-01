package a.entity.gus06.sys.script1.context.builder1.a;

import a.framework.*;
import java.util.Map;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160504";}
	
	public static final String C_ID = "id";
	public static final String C_ALIAS = "alias";
	public static final String C_INPUT = "input";
	public static final String C_OUTPUT = "output";
	public static final String C_APP = "app";
	public static final String C_OPERATORS = "operators";
	public static final String C_EXECUTION = "execution";
	
	public static final String C_TAG_BUILDER = "tag_builder";
	public static final String C_TAG_EXECUTOR = "tag_executor";
	public static final String C_EXP_EVALUATOR = "exp_evaluator";
	public static final String C_FILE_MAPPER = "file_mapper";
	public static final String C_START_DATE = "start_date";
	
	


	private Service buildOutput;
	private Service buildInput;
	private Service buildExecution;
	private Service buildAppMap;
	private Service buildMapper;
	private Service custOpMap;
	
	private Service tagBuilder;
	private Service tagExecutor;
	private Service expEvaluator;
	
	private Service buildMap;
	private Service randomId;

	

	public EntityImpl() throws Exception
	{
		buildOutput = Outside.service(this,"gus06.sys.script1.context.builder1.a.output");
		buildInput = Outside.service(this,"gus06.sys.script1.context.builder1.a.input");
		buildExecution = Outside.service(this,"gus06.sys.script1.context.builder1.a.execution");
		buildAppMap = Outside.service(this,"gus06.sys.script1.context.builder1.a.app");
		buildMapper = Outside.service(this,"gus06.sys.script1.context.builder1.a.mapper");
		custOpMap = Outside.service(this,"gus06.sys.script1.context.builder1.a.opmap.cust");
		
		tagBuilder = Outside.service(this,"gus06.sys.script1.tool.build.tag");
		tagExecutor = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		expEvaluator = Outside.service(this,"gus06.sys.expression1.evaluate");
		
		buildMap = Outside.service(this,"gus06.map.map1");
		randomId = Outside.service(this,"gus06.data.generate.string.random.number14");
	}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
			
		Object input1 = o[0];
		Object output1 = o[1];
		Map opMap1 = (Map) o[2];
		
		
		Map context = map("context");
		
		String id = (String) randomId.g();
		Map alias = map("alias");
		Object input = buildInput.t(input1);
		Object output = buildOutput.t(output1);
		Map opMap = (Map) custOpMap.t(new Map[]{context,opMap1});
		
		Object appMap = buildAppMap.t(context);
		Object execution = buildExecution.t(context);
		Object mapper = buildMapper.t(context);
		Date startDate = new Date();
		
		context.put(C_ID,id);
		context.put(C_ALIAS,alias);
		context.put(C_INPUT,input);
		context.put(C_OUTPUT,output);
		
		context.put(C_APP,appMap);
		context.put(C_OPERATORS,opMap);
		context.put(C_EXECUTION,execution);
		context.put(C_FILE_MAPPER,mapper);
		context.put(C_START_DATE,startDate);
		
		context.put(C_TAG_BUILDER,tagBuilder);
		context.put(C_TAG_EXECUTOR,tagExecutor);
		context.put(C_EXP_EVALUATOR,expEvaluator);
		
		return context;
	}
	
	
	
	private Map map(String name) throws Exception
	{return (Map) buildMap.t(name);}
}
