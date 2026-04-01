package a.entity.gus06.string.transform.generate.random;

import a.framework.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}
	
	private Pattern randomWords = Pattern.compile("<([^|]+|[^>]+)>");
	private Pattern randomInt = Pattern.compile("<(-?[0-9]+)-(-?[0-9]+)d?([0-9]+)?>");
	private Pattern randomChar = Pattern.compile("<([a-zA-Z])-([a-zA-Z])>");
	
	private T randomLists;
	private T randomNumbers;
	private T randomChars;
	
	public EntityImpl()
	{
		randomLists = new RandomLists();
		randomNumbers = new RandomNumbers();
		randomChars = new RandomChars();
	}

	public Object t(Object obj) throws Exception
	{
		obj = randomLists.t(obj);
		obj = randomNumbers.t(obj);
		obj = randomChars.t(obj);
		return obj;
	}
	
	
	
	
	private class RandomLists implements T
	{
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			Matcher m = randomWords.matcher(s);
			while(m.find())
			{
				String s1 = m.group(1);
				String[] list = s1.split("\\|");   
				int value = (int)(Math.random()*list.length);
						
				String exp = Pattern.quote(m.group());
				String replacement = list[value];
				s = s.replaceFirst(exp,replacement);
			}
			return s;
		}
	}
	
	
	private class RandomNumbers implements T
	{
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			Matcher m = randomInt.matcher(s);
			while(m.find())
			{
				String s1 = m.group(1);
				String s2 = m.group(2);
				String s3 = m.group(3);
				
				int n1 = Integer.parseInt(s1);
				int n1_length = s1.length();
				int n2 = Integer.parseInt(s2);
				int n2_length = s2.length();
				
				int value = (int)(Math.random()*(Math.abs(n2-n1)+1))+Math.min(n1,n2);
				
				if(s3==null)
				{
					String val = format(value,n1_length,n2_length);
					s = s.replaceFirst(m.group(),val);
				}
				else
				{
					int d = Integer.parseInt(s3);
					double value2 = (double)value/(double)d;
					s = s.replaceFirst(m.group(),""+value2);
				}
			}
			return s;
		}
		
		private String format(int number, int length1, int length2) 
		{
			String num = ""+number;
			if(length1!=length2) return num;
			while(num.length()<length1) num = "0"+num;
			return num;
		}
	}
	
	
	
	private class RandomChars implements T
	{
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			Matcher m = randomChar.matcher(s);
			while(m.find())
			{
				String s1 = m.group(1);
				String s2 = m.group(2);
					
				int n1 = (int)s1.charAt(0);
				int n2 = (int)s2.charAt(0);
					
				int value = (int)(Math.random()*(Math.abs(n2-n1)+1))+Math.min(n1,n2);
				s = s.replaceFirst(m.group(),""+(char)value);
			}
			return s;
		}
	}
}
