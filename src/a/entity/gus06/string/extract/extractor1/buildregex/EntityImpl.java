package a.entity.gus06.string.extract.extractor1.buildregex;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}
	
	public static final String RELUCTANT = "?";
	public static final String POSSESSIVE = "+";
	


	private Service quote;
	private Service diaLower;
	private Service diaUpper;
	
	private String[] diaLowerArray;
	private String[] diaUpperArray;
	

	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
		diaLower = Outside.service(this,"gus06.string.array.diacritics.lower");
		diaUpper = Outside.service(this,"gus06.string.array.diacritics.upper");
		
		diaLowerArray = (String[]) diaLower.g();
		diaUpperArray = (String[]) diaUpper.g();
	}
	

	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String rule = o[0];
		String options = o[1];
		
		boolean d_ = options.contains("d"); //variable digit
		boolean D_ = options.contains("D"); //variable number
		boolean w_ = options.contains("w"); //variable letter
		boolean W_ = options.contains("W"); //variable word
		boolean s_ = options.contains("s"); //variable space horizontal
		boolean S_ = options.contains("S"); //variable space
		
		boolean r_ = options.contains("r"); //reluctant
		boolean p_ = options.contains("p"); //possessive
		
		boolean n_ = options.contains("n"); //normalized
		
		String[] nn = rule.split("\\*");
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<nn.length;i++)
		{
			String n = nn[i];
			if(i>0)
			{
				b.append("([^");
				
				if(n.equals("")) b.append("*");
				else b.append(quote(n.substring(0,1)));
				
				b.append("]+");
				
				if(r_) b.append(RELUCTANT);
				else if(p_) b.append(POSSESSIVE);
				
				b.append(")");
			}
			if(!n.equals(""))
			{
				n = quote(n);
				
				if(n_)
				{
					
				}
				
				if(s_) n = n.replaceAll("\\h+","\\\\h+");
				else if(S_) n = n.replaceAll("\\s+","\\\\s+");
				
				if(D_) n = n.replaceAll("\\d+","\\\\d+");
				else if(d_) n = n.replaceAll("\\d","\\\\d");
				
				if(W_) n = n.replaceAll("[a-zA-Z]+","[a-zA-Z]+");
				else if(w_) n = n.replaceAll("[a-zA-Z]","[a-zA-Z]");
				
				b.append(n);
			}
		}
		return b.toString();
	}
	
	
	
	private String quote(String s) throws Exception
	{return (String) quote.t(s);}
}
