package a.entity.gus06.string.transform.generate.lookups;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T, R
{
	public String creationDate() {return "20151014";}


	public static String letter = "[a-zA-Z]";
	public static String intNumber = "-?[0-9]+";
	public static String intNumber2 = "[0-9]+";
	public static String date = "[0-9]+\\.[0-9][0-9]";
	public static String date2 = "[0-9]+\\.[0-9][0-9]\\.[0-9][0-9]";
	public static String position = "\\[\\]";
	public static String delim = "\\([^\\)]*\\)";
	
	
	public static Pattern lookup = Pattern.compile("\\[([^\\]]+)\\]");
	
	public static Pattern lookupInt1 = Pattern.compile("("+intNumber+")-("+intNumber+")");
	public static Pattern lookupInt2 = Pattern.compile("("+intNumber+")-("+intNumber+"):("+intNumber2+")");
	
	public static Pattern lookupChar1 = Pattern.compile("("+letter+")-("+letter+")");
	public static Pattern lookupChar2 = Pattern.compile("("+letter+")-("+letter+"):("+intNumber2+")");
	
	public static Pattern lookupDate1 = Pattern.compile("("+date+")-("+date+")("+delim+")?");
	public static Pattern lookupDate2 = Pattern.compile("("+date+")-("+date+"):("+intNumber2+")("+delim+")?");
	public static Pattern lookupDate3 = Pattern.compile("("+date2+")-("+date2+")("+delim+")?");
	public static Pattern lookupDate4 = Pattern.compile("("+date2+")-("+date2+"):("+intNumber2+")("+delim+")?");
	
	
	
	
	
	
	
	public Object t(Object obj) throws Exception
	{	
		String[] lines = obj.toString().split("[\n\r]",-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<lines.length;i++)
		{
			Matcher m = lookup.matcher(lines[i]);
			if(m.find())
			{
				String rule = m.group(1);
				String newLine = m.replaceFirst(position);
				computeLookup(b,newLine,rule);
			}
			else b.append(lines[i]+"\n");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	
	
	
	private void computeLookup(StringBuffer b, String newLine, String rule) {
		
		if(rule.matches("[^\\|]+\\|[^\\]]+"))
			computeLookupWord(b,newLine,rule);
		
		else if(rule.matches(intNumber+"-"+intNumber))
			computeLookupInt1(b,newLine,rule);
		
		else if(rule.matches(intNumber+"-"+intNumber+":"+intNumber2))
			computeLookupInt2(b,newLine,rule);
		
		else if(rule.matches(letter+"-"+letter))
			computeLookupChar1(b,newLine,rule);
		
		else if(rule.matches(letter+"-"+letter+":"+intNumber2))
			computeLookupChar2(b,newLine,rule);
		
		else if(rule.matches(date+"-"+date+"("+delim+")?"))
			computeLookupDate1(b,newLine,rule);
		
		else if(rule.matches(date+"-"+date+":"+intNumber2+"("+delim+")?"))
			computeLookupDate2(b,newLine,rule);
		
		else if(rule.matches(date2+"-"+date2+"("+delim+")?"))
			computeLookupDate3(b,newLine,rule);
		
		else if(rule.matches(date2+"-"+date2+":"+intNumber2+"("+delim+")?"))
			computeLookupDate4(b,newLine,rule);
	}
	
	
	
	
	
	private void computeLookupWord(StringBuffer b, String newLine, String rule)
	{
		String[] list = rule.split("\\|");
		for(int j=0;j<list.length;j++)
		b.append(newLine.replaceAll(position,list[j]) + "\n");
	}
	
	
	
	
	private void computeLookupInt1(StringBuffer b, String newLine, String rule)
	{
		Matcher m = lookupInt1.matcher(rule);
		m.find();
		
		String s1 = m.group(1);
		String s2 = m.group(2);
		
		int start = Integer.parseInt(s1);
		int end = Integer.parseInt(s2);
		
		if(start<=end)
		for(int j=start;j<=end;j++)
		{
			String num = format(j,s1,s2);
			b.append(newLine.replaceAll(position,num) + "\n");
		}
		else
		for(int j=start;j>=end;j--)
		{
			String num = format(j,s1,s2);
			b.append(newLine.replaceAll(position,num) + "\n");
		}
	}
	
	
	
	
	
	private void computeLookupInt2(StringBuffer b, String newLine, String rule)
	{
		Matcher m = lookupInt2.matcher(rule);
		m.find();
		
		String s1 = m.group(1);
		String s2 = m.group(2);
		String s3 = m.group(3);
		
		int start = Integer.parseInt(s1);
		int end = Integer.parseInt(s2);
		int step = Integer.parseInt(s3);
		
		if(start<=end)
		for(int j=start;j<=end;j+=step)
		{
			String num = format(j,s1,s2);
			b.append(newLine.replaceAll(position,num) + "\n");
		}
		else
		for(int j=start;j>=end;j-=step)
		{
			String num = format(j,s1,s2);
			b.append(newLine.replaceAll(position,num) + "\n");
		}
	}
		
	
	
	
	
	private void computeLookupChar1(StringBuffer b, String newLine, String rule)
	{
		Matcher m = lookupChar1.matcher(rule);
		m.find();
		
		String s1 = m.group(1);
		String s2 = m.group(2);
		
		char start = s1.charAt(0);
		char end = s2.charAt(0);
		
		if(start<=end)
		for(char j=start;j<=end;j++)
		{
			b.append(newLine.replaceAll(position,""+j) + "\n");
		}
		else
		for(char j=start;j>=end;j--)
		{
			b.append(newLine.replaceAll(position,""+j) + "\n");
		}
	}
	
	
	
	
	
	
	private void computeLookupChar2(StringBuffer b, String newLine, String rule)
	{
		Matcher m = lookupChar2.matcher(rule);
		m.find();
		
		String s1 = m.group(1);
		String s2 = m.group(2);
		String s3 = m.group(3);
		
		char start = s1.charAt(0);
		char end = s2.charAt(0);
		int step = Integer.parseInt(s3);
		
		if(start<=end)
		for(char j=start;j<=end;j+=step)
		{
			b.append(newLine.replaceAll(position,""+j) + "\n");
		}
		else
		for(char j=start;j>=end;j-=step)
		{
			b.append(newLine.replaceAll(position,""+j) + "\n");
		}
	}

	
	
	
	
	private void computeLookupDate1(StringBuffer b, String newLine, String rule)
	{
		Matcher matcher = lookupDate1.matcher(rule);
		matcher.find();
		
		String s1 = matcher.group(1);
		String s2 = matcher.group(2);
		String delim = matcher.group(3);
		
		String[] ss1 = s1.split("\\.");
		String[] ss2 = s2.split("\\.");
		
		int y1 = Integer.parseInt(ss1[0]);
		int m1 = Integer.parseInt(ss1[1]);
		int y2 = Integer.parseInt(ss2[0]);
		int m2 = Integer.parseInt(ss2[1]);
		int y1_length = ss1[0].length();
		
		int y = y1;
		int m = m1;
		
		b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m)) + "\n");
		while(y<y2 || m<m2)
		{
			m++;
			if(m>12){m-=12;y++;}
			b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m)) + "\n");
		} 
	}
	
	
	
	
	
	private void computeLookupDate2(StringBuffer b, String newLine, String rule)
	{
		Matcher matcher = lookupDate2.matcher(rule);
		matcher.find();
		
		String s1 = matcher.group(1);
		String s2 = matcher.group(2);
		String s3 = matcher.group(3);
		String delim = matcher.group(4);
		
		String[] ss1 = s1.split("\\.");
		String[] ss2 = s2.split("\\.");
		
		int y1 = Integer.parseInt(ss1[0]);
		int m1 = Integer.parseInt(ss1[1]);
		int y2 = Integer.parseInt(ss2[0]);
		int m2 = Integer.parseInt(ss2[1]);
		int y1_length = ss1[0].length();
		int step = Integer.parseInt(s3);

		int y = y1;
		int m = m1;
		
		b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m)) + "\n");
		while(y<y2 || m<m2)
		{
			m+=step;
			if(m>12){m-=12;y++;}
			b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m)) + "\n");
		} 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void computeLookupDate3(StringBuffer b, String newLine, String rule)
	{
		Matcher matcher = lookupDate3.matcher(rule);
		matcher.find();
		
		String s1 = matcher.group(1);
		String s2 = matcher.group(2);
		String delim = matcher.group(3);
		
		String[] ss1 = s1.split("\\.");
		String[] ss2 = s2.split("\\.");
		
		int y1 = Integer.parseInt(ss1[0]);
		int m1 = Integer.parseInt(ss1[1]);
		int j1 = Integer.parseInt(ss1[2]);
		int y2 = Integer.parseInt(ss2[0]);
		int m2 = Integer.parseInt(ss2[1]);
		int j2 = Integer.parseInt(ss2[2]);
		int y1_length = ss1[0].length();
		
		int y = y1;
		int m = m1;
		int j = j1;
		int endMonth = endMonth(m);
		
		b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m,j)) + "\n");
		while(y<y2 || m<m2 || j<j2)
		{
			j++;
			if(j>endMonth)
			{
				j-=endMonth; m++;
				endMonth = endMonth(m);
			}
			if(m>12){m-=12;y++;}
			b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m,j)) + "\n");
		} 
	}
	
	
	
	
	
	private void computeLookupDate4(StringBuffer b, String newLine, String rule)
	{
		Matcher matcher = lookupDate4.matcher(rule);
		matcher.find();
		
		String s1 = matcher.group(1);
		String s2 = matcher.group(2);
		String s3 = matcher.group(3);
		String delim = matcher.group(4);
		
		String[] ss1 = s1.split("\\.");
		String[] ss2 = s2.split("\\.");
		
		int y1 = Integer.parseInt(ss1[0]);
		int m1 = Integer.parseInt(ss1[1]);
		int j1 = Integer.parseInt(ss1[2]);
		int y2 = Integer.parseInt(ss2[0]);
		int m2 = Integer.parseInt(ss2[1]);
		int j2 = Integer.parseInt(ss2[2]);
		int y1_length = ss1[0].length();
		int step = Integer.parseInt(s3);

		int y = y1;
		int m = m1;
		int j = j1;
		int endMonth = endMonth(m);
		
		b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m,j)) + "\n");
		while(y<y2 || m<m2)
		{
			j+=step;
			if(j>endMonth)
			{
				j-=endMonth; m++;
				endMonth = endMonth(m);
			}
			if(m>12){m-=12;y++;}
			b.append(newLine.replaceAll(position,toDate(delim,y1_length,y,m,j)) + "\n");
		} 
	}
	
	
	
	
	
	
	private String format(int number, String element1, String element2) {
		
		String num = ""+number;
		
		int length1 = element1.length();
		int length2 = element2.length();
		
		if(length1!=length2)
			return num;
		
		int number1 = Integer.parseInt(element1);
		int number2 = Integer.parseInt(element2);
		
		if(number1*number2<0)
			return num;
		
		boolean isNegatif = false;
		if(number<0)
		{
			isNegatif = true;
			num = num.substring(1,num.length());
		}

		while(num.length()<length1) num = "0"+num;
		if(isNegatif) num = "-"+num;
		
		return num;
	}
	
	
	
	
	
	
	private String toDate(String delim, int year_length,int y,int m)
	{
		String d = ".";
		if(delim!=null)
			d = delim.substring(1,delim.length()-1);
		
		String year = ""+y;
		while(year.length()<year_length) year = "0"+year;
		
		StringBuffer b = new StringBuffer(year);
		
		if(m<10) b.append(d+"0"+m);
		else b.append(d+m);
		
		return b.toString();
	}
	
	
	
	
	
	private String toDate(String delim, int year_length,int y,int m, int j)
	{
		StringBuffer b = new StringBuffer(toDate(delim,year_length,y,m));
		
		String d = ".";
		if(delim!=null)
			d = delim.substring(1,delim.length()-1);
		
		if(j<10) b.append(d+"0"+j);
		else b.append(d+j);
		
		return b.toString();
	}
   
	
	
	
	
	private int endMonth(int month)
	{
		if(month==1)return 31;
		if(month==2)return 29;
		if(month==3)return 31;
		if(month==4)return 30;
		if(month==5)return 31;
		if(month==6)return 30;
		if(month==7)return 31;
		if(month==8)return 31;
		if(month==9)return 30;
		if(month==10)return 31;
		if(month==11)return 30;
		if(month==12)return 31;
		return -1;
	}
	
	
	
	
	
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("help")) return help();
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String help() throws Exception
	{
		StringBuffer b = new StringBuffer("Aide pour GENERATE LOOKUP\n\n");
		b.append("Pour chaque ligne contenant une expression entre crochets, si la premi�re occurrence correspond � un motif de r�gles suivant\n");
		b.append("La ligne est remplac�e par un ensemble de lignes d�riv�es de la ligne initiale dans laquelle le motif a �t� remplac�\n\n");
		b.append("Motif 1: [Beb|Dod|Gus]\titerator sur les valeurs de la liste\n");
		b.append("Motif 2: [2-8]\titerator sur un entier de 2 � 8 avec un pas de 1\n");
		b.append("Motif 3: [2-8:3]\titerator sur un entier de 2 � 8 avec un pas de 3\n");
		b.append("Motif 4: [a-g]\titerator sur un entier de a � g avec un pas de 1\n");
		b.append("Motif 5: [a-g:3]\titerator sur un entier de a � g avec un pas de 3\n");
		b.append("Motif 6: date...\n");
		b.append("Motif 7: date...\n");
		return b.toString();
	}
}
