package a.entity.gus06.math.equation2.display;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[])
		{
			double[] coef = (double[]) obj;
			if(coef.length!=3) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			double a = coef[0];
			double b = coef[1];
			double c = coef[2];
			
			if(a<0)
			{
				a *= -1;
				b *= -1;
				c *= -1;
			}
			
			StringBuffer buff = new StringBuffer();
			{
				String a_ = toString(a);
				if(!a_.equals("1")) buff.append(a_);
				buff.append("x\u00B2");
			}
			if(b!=0)
			{
				if(b>0) buff.append(" + ");
				else {buff.append(" - "); b*=-1;}
				
				String b_ = toString(b);
				if(!b_.equals("1")) buff.append(b_);
				buff.append("x");
			}
			if(c!=0)
			{
				if(c>0) buff.append(" + ");
				else {buff.append(" - "); c*=-1;}
				
				String c_ = toString(c);
				buff.append(c_);
			}
			buff.append(" = 0");
			return buff.toString();
		}
		if(obj instanceof int[])
		{
			int[] coef = (int[]) obj;
			if(coef.length!=3) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			int a = coef[0];
			int b = coef[1];
			int c = coef[2];
			
			if(a<0)
			{
				a *= -1;
				b *= -1;
				c *= -1;
			}
			
			StringBuffer buff = new StringBuffer();
			{
				String a_ = toString(a);
				if(!a_.equals("1")) buff.append(a_);
				buff.append("x\u00B2");
			}
			if(b!=0)
			{
				if(b>0) buff.append(" + ");
				else {buff.append(" - "); b*=-1;}
				
				String b_ = toString(b);
				if(!b_.equals("1")) buff.append(b_);
				buff.append("x");
			}
			if(c!=0)
			{
				if(c>0) buff.append(" + ");
				else {buff.append(" - "); c*=-1;}
				
				String c_ = toString(c);
				buff.append(c_);
			}
			buff.append(" = 0");
			return buff.toString();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String toString(double n)
	{
		String s = ""+n;
		if(s.endsWith(".0")) return s.substring(0,s.length()-2);
		return s;
	}
	
	private String toString(int n)
	{
		return ""+n;
	}
	
	
}