package a.entity.gus06.string.transform.sequence.length.variance;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		double count = (double) nn.length;
		
		double sum1 = sum1(nn);
		double avg = sum1/count;
		
		double sum2 = sum2(nn,avg);
		double variance = sum2/count;
		
		return ""+variance;
	}
	
	
	
	private double sum1(String[] nn)
	{
		double sum = 0;
		for(String n:nn) sum += n.length();
		return sum;
	}
	
	private double sum2(String[] nn, double avg)
	{
		double sum = 0;
		for(String n:nn)
		{
			int length = n.length();
			sum =+ (length-avg)*(length-avg);
		}
		return sum;
	}
}
