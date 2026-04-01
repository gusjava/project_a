package a.entity.gus06.math.equation2.display.solutions;

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
		return "x = "+toString(solution);
	}
	
	private String build(double solution1, double solution2) throws Exception
	{
		if(solution1<solution2) return "x = "+toString(solution1)+" or x = "+toString(solution2);
		return "x = "+toString(solution2)+" or x = "+toString(solution1);
	}
	
	private String toString(double n)
	{
		String s = ""+n;
		if(s.endsWith(".0")) return s.substring(0,s.length()-2);
		return s;
	}
}