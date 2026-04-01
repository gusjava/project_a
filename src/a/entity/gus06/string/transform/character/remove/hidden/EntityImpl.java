package a.entity.gus06.string.transform.character.remove.hidden;

import a.framework.*;
import java.awt.Font;
import javax.swing.JLabel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250522";}
	
	public static Font FONT = new JLabel().getFont();
	
	private Service isVisible;

	public EntityImpl() throws Exception
	{
		isVisible = Outside.service(this,"gus06.graphics.draw.string.isvisible");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		StringBuilder b = new StringBuilder();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\t')
			{
				boolean found = isVisible.f(new Object[]{""+c,FONT});
				if(!found) continue;
			}
			b.append(c);
		}
		return b.toString();
	}
}
