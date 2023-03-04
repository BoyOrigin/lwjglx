package org.lwjgl.opengl;


import org.lwjgl.LWJGLException;

public class GLContext {
	
	private static final ContextCapabilities contextCapabilities = new ContextCapabilities();
	
	public static GLContext createFromCurrent() {
		return new GLContext();
	}
	
	public static ContextCapabilities getCapabilities() {
		return contextCapabilities;
	}

	public static synchronized void loadOpenGLLibrary() throws LWJGLException {
		try {
			GL.initialize();
		} catch (Throwable t) {
			throw new LWJGLException(t);
		}
	}

	public static synchronized void unloadOpenGLLibrary() {}
}
