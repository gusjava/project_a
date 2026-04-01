package a.entity.gus06.sys.expert1.analyze.bool.iftrue.equality;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160811";}


	private Service eval;
	private Service rewrite;
	private Service checkNum;

	public EntityImpl() throws Exception
	{
		eval = Outside.service(this,"gus06.sys.parser3.tool.evalwith.a");
		rewrite = Outside.service(this,"gus06.sys.parser3.analyzer1.rewrite");
		checkNum = Outside.service(this,"gus06.sys.parser3.analyzer1.op.num.check");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map m = (Map) o[0];
		Object part1 = o[1];
		Object part2 = o[2];
		
		Map pool = (Map) m.get("pool");
		String exp = (String) m.get("exp");
		Map opMap = (Map) m.get("opMap");
		Map output = (Map) m.get("output");
		
		String exp1 = (String) rewrite.t(part1);
		String exp2 = (String) rewrite.t(part2);
		
		Object r1 = eval.t(new Object[]{exp1,opMap,output});
		Object r2 = eval.t(new Object[]{exp2,opMap,output});
		
		if(r1!=null && r2!=null)
		{
			if(r1.equals(r2))
			{
				pool.remove(exp);
				return true;
			}
			else throw new Exception("False equality detected: ("+exp1+")==("+exp2+")");
		}
		
		else if(r1==null && r2==null)
		{
			if(checkNum.f(part1) && checkNum.f(part2))
			{
				String newExp = "("+exp1+")-("+exp2+")";
				pool.put(newExp,Integer.valueOf(0));
				pool.remove(exp);
				return true;
			}
		}
		
		else if(r1!=null)
		{
			pool.put(exp2,r1);
			pool.remove(exp);
			return true;
		}
		
		else if(r2!=null)
		{
			pool.put(exp1,r2);
			pool.remove(exp);
			return true;
		}
		
		return false;
	}
}
