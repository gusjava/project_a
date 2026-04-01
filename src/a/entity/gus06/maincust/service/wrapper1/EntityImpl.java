package a.entity.gus06.maincust.service.wrapper1;

import a.framework.*;
import java.util.List;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140813";}

	private Service srcToString;
	private Service watcher;

	public EntityImpl() throws Exception
	{
		srcToString = Outside.service(this,"srctostring");
		watcher = Outside.service(this,"gus06.maincust.service.wrapper1.watcher");
	}

	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object target = o[0];
		Object src = o[1];
		
		String src_ = (String) srcToString.t(src);
		
		return new ServiceImpl(target,src_);
	}

	
	
	private class ServiceImpl implements Service
	{
	    private Object target;
	    private String src_;

	    private E e;
	    private F f;
	    private B b;
	    private H h;
	    private P p;
	    private I i;
	    private G g;
	    private V v;
	    private R r;
	    private S s;
	    private T t;
	    
	    
	    private void check(Object obj, Class c) throws Exception
	    {
	    	if(obj==null)
	    	throw new Exception("Feature "+c.getName()+" not available for "+src_+" (="+target.getClass().getName()+")");
		watcher.p(new String[]{src_,target.toString(),c.getSimpleName()});
	    }
	    
	    
	    public ServiceImpl(Object target, String src_)
	    {
	        this.src_ = src_;
	        this.target = target;
	        
	        if(target instanceof E)			e = (E) target;
	        if(target instanceof F)			f = (F) target;
	        if(target instanceof B)			b = (B) target;
	        if(target instanceof H)			h = (H) target;
	        if(target instanceof P)			p = (P) target;
	        if(target instanceof I)			i = (I) target;
	        if(target instanceof G)			g = (G) target;
	        if(target instanceof V)			v = (V) target;
	        if(target instanceof R)			r = (R) target;
	        if(target instanceof S)			s = (S) target;
	        if(target instanceof T)			t = (T) target;
	    }
	    
	    
	    
	    
	    public void e() throws Exception
	    {check(e,E.class); e.e();}

	    public boolean f(Object obj) throws Exception
	    {check(f,F.class); return f.f(obj);}

	    public boolean b() throws Exception
	    {check(b,B.class); return b.b();}
	    
	    public double h(double value) throws Exception
	    {check(h,H.class); return h.h(value);}

	    public void p(Object obj) throws Exception
	    {check(p,P.class); p.p(obj);}
	    
	    public Object g() throws Exception
	    {check(g,G.class); return g.g();}
	    
	    public void v(String key, Object obj) throws Exception
	    {check(v,V.class); v.v(key,obj);}
	    
	    public Object r(String key) throws Exception
	    {check(r,R.class); return r.r(key);}
	    
	    public Object t(Object obj) throws Exception
	    {check(t,T.class); return t.t(obj);}
	    
	    public Object i() throws Exception
	    {check(i,I.class); return i.i();}
	    
	    
	    public void addActionListener(ActionListener listener) throws Exception
	    {
	        if(s==null) return;
	        s.addActionListener(listener);
	    }
	    
	    public void removeActionListener(ActionListener listener) throws Exception
	    {
	        if(s==null) return;
	        s.removeActionListener(listener);
	    }
	    
	    public List listeners() throws Exception
	    {
	        if(s==null) return null;
	        return s.listeners();
	    }
	}
}
