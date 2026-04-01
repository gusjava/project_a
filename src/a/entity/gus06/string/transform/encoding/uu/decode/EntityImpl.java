package a.entity.gus06.string.transform.encoding.uu.decode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		StringBuffer b = new StringBuffer();
		
		int state = 0;
		StringBuffer hexCode = new StringBuffer(4);
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			switch(state)
			{
			case 0:
				if(c=='\\') state=1;
				else b.append(c);
				break;
			case 1:
				if(c=='u'){state=2;}
				else {b.append("\\"+c);state=0;}
				break;
			case 2:
				hexCode.append(c);
				if(hexCode.length()==4)
				{
					int codePoint = Integer.parseInt(hexCode.toString(),16);
					b.append(Character.toChars(codePoint));
					hexCode = new StringBuffer(4);
					state=0;
				}
			}
		}
		return b.toString();
	}
}
