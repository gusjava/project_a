package a.entity.gus.y.debug1.print.handle.ste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {
	public String creationDate() {return "20240125";}
	
	public void e() throws Exception {
		handleThread(Thread.currentThread());
	}
	
	public void p(Object obj) throws Exception {
		if(obj instanceof Thread) {
			handleThread((Thread) obj);
			return;
		}
		if(obj instanceof StackTraceElement[]) {
			handleSteArray((StackTraceElement[]) obj);
			return;
		}
		throw new Exception("");
	}
	
	
	private void handleThread(Thread thread) throws Exception {
		handleSteArray(thread.getStackTrace());
	}
	
	private void handleSteArray(StackTraceElement[] steArray) throws Exception {
		for(int i=0;i<steArray.length;i++) {
			StackTraceElement ste = steArray[i];
			System.out.println(toString(ste));
		}
	}
	
	private String toString(StackTraceElement ste) {
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}
}
