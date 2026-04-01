package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}
	
	
	public static final boolean DEV = true;
	


	private Service applyIf;
	private Service applyFor;
	private Service applyEach;
	private Service applyWhile;
	private Service applyFunc;
	private Service applyCall;
	private Service applyVar;
	private Service applyCode;
	private Service applyTemplate;
	private Service applyFile;
	private Service applyFile1;

	private Service print;
	private Service printErr;
	
	
	
	public EntityImpl() throws Exception
	{
		applyIf = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.if0");
		applyEach = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.each");
		applyFor = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.for0");
		applyWhile = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.while0");
		applyFunc = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.func");
		applyCall = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.call");
		applyVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.var");
		applyCode = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.code");
		applyTemplate = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.template");
		applyFile = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.file");
		applyFile1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.op.file1");
		
		print = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.print");
		printErr = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.printerr");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		
		try
		{
			String type = (String) span.get("type");
			if(type.equals("operator"))
			{
				String name = (String) span.get("name");
			
				if(name.equals("end")) return;
				if(name.equals("ignore")) return;
			
				if(name.equals("if")) {applyIf.p(span);return;}
				if(name.equals("each")) {applyEach.p(span);return;}
				if(name.equals("for")) {applyFor.p(span);return;}
				if(name.equals("while")) {applyWhile.p(span);return;}
				if(name.equals("func")) {applyFunc.p(span);return;}
				if(name.equals("call")) {applyCall.p(span);return;}
				if(name.equals("var")) {applyVar.p(span);return;}
				if(name.equals("code")) {applyCode.p(span);return;}
				if(name.equals("template")) {applyTemplate.p(span);return;}
				if(name.equals("file")) {applyFile.p(span);return;}
				if(name.equals("file1")) {applyFile1.p(span);return;}
			}
			print.p(span);
		}
		catch(Exception e)
		{
			if(!DEV) throw e;
			printErr.p(new Object[]{span,e});
		}
	}
}
