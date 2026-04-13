package a.entity.gus.y.server1.parser.args;

import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260409";}

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return parseArgs(s);
	}
	
	private List parseArgs(String args)
	{
		List list = new ArrayList();
		if(args==null) return list;

		StringBuffer b = new StringBuffer();
		int i = 0;
		int len = args.length();

		while(i < len)
		{
			char c = args.charAt(i);

			if(c == '"' || c == '\'')
			{
				char quote = c;
				i++;
				while(i < len)
				{
					char q = args.charAt(i);
					if(q == '\\' && i+1 < len)
					{
						char next = args.charAt(i+1);
						if(next == quote || next == '\\') { b.append(next); i += 2; }
						else if(next == 'n') { b.append('\n'); i += 2; }
						else if(next == 't') { b.append('\t'); i += 2; }
						else if(next == 'r') { b.append('\r'); i += 2; }
						else { b.append(q); i++; }
					}
					else if(q == quote) { i++; break; }
					else { b.append(q); i++; }
				}
			}
			else if(c == ' ')
			{
				if(b.length() > 0) { list.add(b.toString()); b.delete(0,b.length()); }
				i++;
			}
			else
			{
				b.append(c);
				i++;
			}
		}

		if(b.length() > 0) list.add(b.toString());
		return list;
	}
}
