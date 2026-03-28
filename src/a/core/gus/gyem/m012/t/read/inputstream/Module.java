package a.core.gus.gyem.m012.t.read.inputstream;

import java.net.URL;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String path = (String) obj;
		if(!path.startsWith("/")) path = "/"+path;
		
		if(!checkDone) check(path);
		return getClass().getResourceAsStream(path);
	}
	
	
	
    /*
     * If the application path contains a exclamation marks '!',
     * the following sun class: sun.net.www.protocol.jar.JarURLConnection$JarURLInputStream
     * will not be successfully instantiated during the getResourceAsStream call
     */
    private boolean checkDone = false;
    
    private void check(String path) throws Exception
    {
    	URL url = getClass().getResource(path);
    	if(url==null) return;
    	if(getClass().getResourceAsStream(path)==null)
    		throw new Exception("Unable to handle jar internal URLs due to exclamation mark confusion :"+
    				"\ntroubling url example: ["+url+"]"+
    				"\nPlease remove all exclamation marks '!' from the application path");
    	checkDone = true;
    }
}
