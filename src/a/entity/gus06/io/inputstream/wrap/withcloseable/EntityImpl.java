package a.entity.gus06.io.inputstream.wrap.withcloseable;

import a.framework.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.Closeable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250510";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) o[0];
		Closeable c = (Closeable) o[1];
		return new InputStreamWrapper(is, c);
	}
	

	private class InputStreamWrapper extends InputStream
	{
		private InputStream is;
		private Closeable c;
		
		public InputStreamWrapper(InputStream is, Closeable c) {
			this.is = is;
			this.c = c;
		}
		public int read() throws IOException {
			return is.read();
		}
		public int read(byte[] b) throws IOException {
			return is.read(b);
		}
		public int read(byte[] b, int off, int len) throws IOException {
			return is.read(b, off, len);
		}
		public long skip(long n) throws IOException {
			return is.skip(n);
		}
		public int available() throws IOException {
			return is.available();
		}
		public void close() throws IOException {
			is.close();
			c.close();
		}
		public synchronized void mark(int readlimit) {
			is.mark(readlimit);
		}
		public synchronized void reset() throws IOException {
			is.reset();
		}
		public boolean markSupported() {
			return is.markSupported();
		}
	}
}
