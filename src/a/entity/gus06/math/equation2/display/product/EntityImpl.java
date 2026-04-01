package a.entity.gus06.math.equation2.display.product;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231103";}


	private Service resolve;


	public EntityImpl() throws Exception
	{
		resolve = Outside.service(this,"gus06.math.equation2.resolve");
	}


	
	public Object t(Object obj) throws Exception
	{
		double[] solutions = (double[]) resolve.t(obj);
		if(solutions.length==0) return null;
		if(solutions.length==1) return build(solutions[0]);
		if(solutions.length==2) return build(solutions[0], solutions[1]);
		throw new Exception("Invalid solution number: "+solutions.length);
	}
	
	
	private String build(double solution) throws Exception
	{
		return handle(solution)+"\u00B2 = 0";
	}
	
	private String build(double solution1, double solution2) throws Exception
	{
		if(solution1==0) return handle(solution1)+handle(solution2)+" = 0";
		if(solution2==0) return handle(solution2)+handle(solution1)+" = 0";
		if(solution1<solution2) return handle(solution1)+handle(solution2)+" = 0";
		return handle(solution2)+handle(solution1)+" = 0";
	}
	
	private String handle(double solution) throws Exception
	{
		if(solution==0) return "x";
		StringBuffer buff = new StringBuffer();
		buff.append("(x");
		if(solution>0) buff.append(" - "+toString(solution));
		else buff.append(" + "+toString(-solution));
		buff.append(")");
		return buff.toString();
	}
	
	private String toString(double n)
	{
		String s = ""+n;
		if(s.endsWith(".0")) return s.substring(0,s.length()-2);
		return s;
	}
}