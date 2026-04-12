package a.entity.gus06.file.convert.csv3.parser;

public class Parser1 {
	
	
	public static String delim_line = "@@<@@LINE@@>@@";
	public static String delim_elem = "@@<@@ELEM@@>@@";
	

	private char delim;
	
	public Parser1(char delim)
	{
		this.delim = delim;
	}
	
	
	
	public String[][] parse(String input) throws Exception
	{
		String in = formatInput(input);

		String[] a = in.split(delim_line,-1);
		int nb_lines = a.length;

		int nb_elems = 0;
		for(int i=0;i<nb_lines;i++)
		{
			int w = a[i].split(delim_elem,-1).length;
			nb_elems = Math.max(nb_elems,w);
		}

		if(nb_lines==0)throw new Exception("no line delimiters found");
		if(nb_elems==0)throw new Exception("no elements delimiters found");

		String[][] result = new String[nb_lines][nb_elems];
		for(int i=0;i<nb_lines;i++)
			for(int j=0;j<nb_elems;j++)
				result[i][j] = "";

		for(int i=0;i<nb_lines;i++)
		{
			String[] n = a[i].split(delim_elem,-1);
			for(int j=0;j<n.length;j++)
				result[i][j] = n[j];
		}
		return result;
	}



	
	// parsing code
	
	private int situation;
	
	private String formatInput(String in)
	{
		StringBuffer out = new StringBuffer();
		situation = 0;
		
		for(int i=0;i<in.length();i++)
		perform_FormatInput(out,in.charAt(i));
		return out.toString();
	}
	
	
	private void perform_FormatInput(StringBuffer out, char c)
	{
		switch(situation)
		{
		case 0:  
			if(c=='"')			{situation=1;break;}
			if(c==delim)		{out.append(delim_elem);break;}
			if(c=='\n')			{out.append(delim_line);break;}
			if(c=='\r')			{break;}
			situation=3;
			out.append(c);
			break;

		case 1://cell inside "
			if(c=='"')			{situation=2;break;}
			out.append(c);
			break;

		case 2://cell inside " meeting "
			if(c=='"')			{situation=1;out.append('"');break;}
			if(c==delim)		{situation=0;out.append(delim_elem);break;}
			if(c=='\n')			{situation=0;out.append(delim_line);break;}
			if(c=='\r')			{break;}
			situation=1;
			out.append('"');
			out.append(c);
			break;

		case 3://cell without "
			if(c==delim)		{situation=0;out.append(delim_elem);break;}
			if(c=='\n')			{situation=0;out.append(delim_line);break;}
			if(c=='\r')			{break;}
			out.append(c);
			break;
		}
	}
}
