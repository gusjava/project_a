package a.entity.gus.y.cust1.logger1;

import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P {
	public String creationDate() {return "20240111";}
	
	private StringBuffer b = new StringBuffer();

	public EntityImpl() throws Exception {

	}

	public Object g() throws Exception {
		return b.toString();
	}

	public void p(Object obj) throws Exception {
		Object[] infos = (Object[]) obj;
		println(infos[0], (String) infos[1]);
	}

	private void println(Object src, String m) {
		String timeStamp = timeStamp(new Date());
		String srcName = src.getClass().getName();
		println(timeStamp + "\t" + formatSrcName(srcName) + "\t" + m);
	}

	private void println(String line) {
		System.out.println(line);
		b.append(line + "\n");
		logged();
	}

	private String timeStamp(Date date) {
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date);
	}
	
	private String formatSrcName(String srcName) {
		if(srcName.startsWith("a.entity.") && srcName.endsWith(".EntityImpl")) 
			return srcName.substring(9, srcName.length()-11);
		return srcName;
	}
	
	private void logged() {
		send(this,"logged()");
	}
}
