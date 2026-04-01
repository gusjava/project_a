package a.entity.gus06.sys.expression1.apply;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}
	
	

	private Service op2Int;
	private Service op2Long;
	private Service op2Double;
	private Service op2String;
	private Service pipe;


	public EntityImpl() throws Exception
	{
		op2Int = Outside.service(this,"gus06.sys.expression1.apply.op2.integer");
		op2Long = Outside.service(this,"gus06.sys.expression1.apply.op2.long1");
		op2Double = Outside.service(this,"gus06.sys.expression1.apply.op2.double1");
		op2String = Outside.service(this,"gus06.sys.expression1.apply.op2.string");
		pipe = Outside.service(this,"gus06.sys.expression1.pipe.op");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
				
		Map opMap = (Map) o[0];
		Object value = o[1];
		String op = findOp(o[2]);
		
		if(value instanceof String)
		op = formatOpForString(op);
		
		boolean isOperator = opMap.containsKey(op);
		T opT = findOpT(op,opMap);
		Object[] params = new Object[]{value,opMap};
		
		try
		{
			return opT.t(params);
		}
		catch(Exception e)
		{
			if(e.getMessage()!=null && e.getMessage().startsWith("Invalid data type"))
			if(value instanceof T || value instanceof F || value instanceof H || value instanceof G || value instanceof I)
				return pipe.t(new Object[]{opT,params});
			
			String message = isOperator ? "failed to apply operator "+op : "failed to apply "+op;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	private T findOpT(String op, Map opMap) throws Exception
	{
		if(opMap.containsKey(op)) return (T) opMap.get(op);
		
		if(isInt(op))		return (T) op2Int.t(op);
		if(isLong(op))		return (T) op2Long.t(op);
		if(isDouble(op))	return (T) op2Double.t(op);
		
		return (T) op2String.t(op);
	}
	
	
	
	public boolean isInt(String op)
	{
		try{Integer.parseInt(op);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	public boolean isLong(String op)
	{
		try{Long.parseLong(op);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	public boolean isDouble(String op)
	{
		try{Double.parseDouble(op);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	
	
	
	private String findOp(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return obj.toString();
		if(obj instanceof Collection) return colToOp((Collection)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String colToOp(Collection col) throws Exception
	{
		if(col.size()!=1) throw new Exception("Could not convert collection to operator");
		return findOp(col.iterator().next());
	}
	
	
	
	
	private String formatOpForString(String op)
	{
		if(op.equals("t")) return "_to_t";
		if(op.equals("h")) return "_to_h";
		if(op.equals("f")) return "_to_f";
		if(op.equals("r")) return "_to_r";
		if(op.equals("p")) return "_to_p";
		return op;
	}
}