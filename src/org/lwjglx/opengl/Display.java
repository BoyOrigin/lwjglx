package org.lwjglx.opengl;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;
import org.lwjglx.LWJGLException;
import org.lwjglx.Sys;
import org.lwjglx.input.Cursor;
import org.lwjglx.input.Keyboard;
import org.lwjglx.input.Mouse;

public class Display {
	
	private static String windowTitle = "Game";
	
	private static org.lwjglx.opengl.GLContext context;
	
	private static boolean displayCreated = false;
	private static boolean displayFocused = false;
	private static boolean displayVisible = true;
	private static boolean displayDirty = false;
	private static boolean displayResizable = false;
	
	private static DisplayMode mode = new DisplayMode(640, 480);
	private static DisplayMode desktopDisplayMode = new DisplayMode(640, 480);
	
	private static int latestEventKey = 0;
	
	private static int displayX = 0;
	private static int displayY = 0;
	
	private static boolean displayResized = false;
	private static int displayWidth = 0;
	private static int displayHeight = 0;
	private static int displayFramebufferWidth = 0;
	private static int displayFramebufferHeight = 0;
	
	private static boolean latestResized = false;
	private static int latestWidth = 0;
	private static int latestHeight = 0;
	
	private static boolean vsyncEnabled = true;
	private static boolean displayFullscreen = false;
	
	static {
		Sys.initialize(); // init using dummy sys method
		
		long monitor = glfwGetPrimaryMonitor();
		GLFWVidMode vidmode = glfwGetVideoMode(monitor);

		int monitorWidth = vidmode.width();
		int monitorHeight = vidmode.height();
		int monitorBitPerPixel = vidmode.redBits() + vidmode.greenBits() + vidmode.blueBits();
		int monitorRefreshRate = vidmode.refreshRate();
		
		desktopDisplayMode = new DisplayMode(monitorWidth, monitorHeight, monitorBitPerPixel, monitorRefreshRate);
	}
	
	public static void create(PixelFormat pixel_format, Drawable shared_drawable) throws LWJGLException {
		System.out.println("TODO: Implement Display.create(PixelFormat, Drawable)"); // TODO
		create();
	}
	
	public static void create(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		System.out.println("TODO: Implement Display.create(PixelFormat, ContextAttribs)"); // TODO
		create();
	}
	
	public static void create(PixelFormat pixel_format) throws LWJGLException {
		System.out.println("TODO: Implement Display.create(PixelFormat)"); // TODO
		create();
	}
	
	public static void create() throws LWJGLException {
		long monitor = glfwGetPrimaryMonitor();
		GLFWVidMode vidmode = glfwGetVideoMode(monitor);

		int monitorWidth = vidmode.width();
		int monitorHeight = vidmode.height();
		int monitorBitPerPixel = vidmode.redBits() + vidmode.greenBits() + vidmode.blueBits();
		int monitorRefreshRate = vidmode.refreshRate();
		
		desktopDisplayMode = new DisplayMode(monitorWidth, monitorHeight, monitorBitPerPixel, monitorRefreshRate);

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, displayResizable ? GL_TRUE : GL_FALSE);
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL_TRUE);

		
		Window.handle = glfwCreateWindow(mode.getWidth(), mode.getHeight(), windowTitle, NULL, NULL);
		if ( Window.handle == 0L )
			throw new IllegalStateException("Failed to create Display window");
		
		
		Window.keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				latestEventKey = key;
				
				if (action == GLFW_RELEASE || action == GLFW.GLFW_PRESS) {
					Keyboard.addKeyEvent(key, action == GLFW.GLFW_PRESS ? true : false);
				}
			}
		};
		
		Window.charCallback = new GLFWCharCallback() {
			@Override
			public void invoke(long window, int codepoint) {
				Keyboard.addCharEvent(latestEventKey, (char)codepoint);
			}
		};
		
		Window.cursorEnterCallback = new GLFWCursorEnterCallback() {
			@Override
			public void invoke(long window, boolean entered) {
				Mouse.setMouseInsideWindow(entered == true);
			}
		};
		
		Window.cursorPosCallback = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				Mouse.addMoveEvent(xpos, ypos);
			}
		};
		
		Window.mouseButtonCallback = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				Mouse.addButtonEvent(button, action == GLFW.GLFW_PRESS ? true : false);
			}
		};
		
		Window.windowFocusCallback = new GLFWWindowFocusCallback() {
			@Override
			public void invoke(long window, boolean focused) {
				displayFocused = focused == true;
			}
		};
		
		Window.windowIconifyCallback = new GLFWWindowIconifyCallback() {
			@Override
			public void invoke(long window, boolean iconified) {
				displayVisible = iconified == false;
			}
		};
		
		Window.windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				latestResized = true;
				latestWidth = width;
				latestHeight = height;
			}
		};
		
		Window.windowPosCallback = new GLFWWindowPosCallback() {
			@Override
			public void invoke(long window, int xpos, int ypos) {
				displayX = xpos;
				displayY = ypos;
			}
		};
		
		Window.windowRefreshCallback = new GLFWWindowRefreshCallback() {
			@Override
			public void invoke(long window) {
				displayDirty = true;
			}
		};
		
		Window.framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				displayFramebufferWidth = width;
				displayFramebufferHeight = height;
			}
		};
		
		Window.scrollCallback = new GLFWScrollCallback() {
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				Mouse.addWheelEvent((int)(yoffset * 120));
			}
		};
		
		Window.setCallbacks();
		
		displayWidth = mode.getWidth();
		displayHeight = mode.getHeight();
		
		IntBuffer fbw = BufferUtils.createIntBuffer(1);
		IntBuffer fbh = BufferUtils.createIntBuffer(1);
		GLFW.glfwGetFramebufferSize(Window.handle, fbw, fbh);
		displayFramebufferWidth = fbw.get(0);
		displayFramebufferHeight = fbh.get(0);
		
		glfwSetWindowPos(
			Window.handle,
			(monitorWidth - mode.getWidth()) / 2,
			(monitorHeight - mode.getHeight()) / 2
		);
		
		displayX = (monitorWidth - mode.getWidth()) / 2;
		displayY = (monitorHeight - mode.getHeight()) / 2;

		glfwMakeContextCurrent(Window.handle);
		context = org.lwjglx.opengl.GLContext.createFromCurrent();
		
		glfwSwapInterval(1);
		glfwShowWindow(Window.handle);
		
		displayCreated = true;
	}
	
	public static boolean isCreated() {
		return displayCreated;
	}
	
	public static boolean isActive() {
		return displayFocused;
	}
	
	public static boolean isVisible() {
		return displayVisible;
	}
	
	public static org.lwjglx.opengl.GLContext getContext() {
		return context;
	}
	
	public static void setLocation(int new_x, int new_y) {
		GLFW.glfwSetWindowPos(Window.handle, new_x, new_y);
	}
	
	public static void setVSyncEnabled(boolean sync) {
		vsyncEnabled = sync;
	}
	
	public static long getWindow() {
		return Window.handle;
	}
	
	public static void update() {
		update(true);
	}
	
	public static void update(boolean processMessages) {
		try {
			swapBuffers();
			displayDirty = false;
		}
		catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
		
		if (processMessages) processMessages();
	}
	
	public static void processMessages() {
		glfwPollEvents();
		Keyboard.poll();
		Mouse.poll();
		
		if (latestResized) {
			latestResized = false;
			displayResized = true;
			displayWidth = latestWidth;
			displayHeight = latestHeight;
		}
		else {
			displayResized = false;
		}
	}
	
	public static void swapBuffers() throws LWJGLException {
		glfwSwapBuffers(Window.handle);
	}
	
	public static void destroy() {
		Window.releaseCallbacks();
		glfwDestroyWindow(Window.handle);
		
		/*try {
			glfwTerminate();
		} catch (Throwable t) {
			t.printStackTrace();
		}*/
		displayCreated = false;
	}
	
	public static void setDisplayMode(DisplayMode dm) throws LWJGLException {
		mode = dm;
		newCurrentWindow(GLFW.glfwCreateWindow(dm.getWidth(), dm.getHeight(), windowTitle, 0, 0));
	}
	
	public static DisplayMode getDisplayMode() {
		return mode;
	}
	
	public static DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		GLFWVidMode.Buffer modes = GLFW.glfwGetVideoModes(GLFW.glfwGetPrimaryMonitor());
		
		DisplayMode[] displayModes = new DisplayMode[modes.capacity()];

		for (int i = 0; i < modes.capacity(); i++) {
			modes.position(i);

			int w = modes.width();
			int h = modes.height();
			int b = modes.redBits() + modes.greenBits() + modes.blueBits();
			int r = modes.refreshRate();

			displayModes[i] = new DisplayMode(w, h, b, r);
		}

		return displayModes;
	}
	
	public static DisplayMode getDesktopDisplayMode() {
		long mon = GLFW.glfwGetPrimaryMonitor();
		GLFWVidMode mode = GLFW.glfwGetVideoMode(mon);
		return new DisplayMode(mode.width(), mode.height(), mode.redBits() + mode.greenBits() + mode.blueBits(), mode.refreshRate());
	}
	
	public static boolean wasResized() {
		return displayResized;
	}
	
	public static int getX() {
		return displayX;
	}
	
	public static int getY() {
		return displayY;
	}
	
	public static int getWidth() {
		return displayWidth;
	}
	
	public static int getHeight() {
		return displayHeight;
	}
	
	public static int getFramebufferWidth() {
		return displayFramebufferWidth;
	}
	
	public static int getFramebufferHeight() {
		return displayFramebufferHeight;
	}
	
	public static void setTitle(String title) {
		windowTitle = title;
	}
	
	public static boolean isCloseRequested() {
		return glfwWindowShouldClose(Window.handle) == true;
	}
	
	public static boolean isDirty() {
		return displayDirty;
	}
	
	public static void setInitialBackground(float red, float green, float blue) {
		// TODO
		System.out.println("TODO: Implement Display.setInitialBackground(float, float, float)");
	}
	
	public static int setIcon(java.nio.ByteBuffer[] icons) {
		// TODO
		System.out.println("TODO: Implement Display.setIcon(ByteBuffer[])");
		return 0;
	}
	
	public static void setResizable(boolean resizable) {
		displayResizable = resizable;
		if (displayResizable^resizable) {
			if (Window.handle!=0) {
				IntBuffer width = BufferUtils.createIntBuffer(1);
				IntBuffer height = BufferUtils.createIntBuffer(1);
				GLFW.glfwGetWindowSize(Window.handle, width, height);
				width.rewind();
				height.rewind();
				
				GLFW.glfwDefaultWindowHints();
				glfwWindowHint(GLFW_VISIBLE, displayVisible ? GL_TRUE : GL_FALSE);
				glfwWindowHint(GLFW_RESIZABLE, displayResizable ? GL_TRUE : GL_FALSE);
				glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL_TRUE);
				
				newCurrentWindow(GLFW.glfwCreateWindow(width.get(), height.get(), windowTitle, GLFW.glfwGetWindowMonitor(Window.handle), 0));
			}
		}
		displayResizable = resizable;
	}
	
	public static boolean isResizable() {
		return displayResizable;
	}
	
	public static void setDisplayModeAndFullscreen(DisplayMode mode) throws LWJGLException {
		Display.mode = mode;
		newCurrentWindow(GLFW.glfwCreateWindow(mode.getWidth(), mode.getHeight(), windowTitle, mode.isFullscreenCapable() ? GLFW.glfwGetPrimaryMonitor() : 0, 0));
	}
	
	public static void setFullscreen(boolean fullscreen) throws LWJGLException {
		if (isFullscreen()^fullscreen) {
			if (fullscreen&&(!mode.isFullscreenCapable())) throw new LWJGLException("Display mode is not fullscreen capable");
			if (Window.handle!=0) {
				IntBuffer width = BufferUtils.createIntBuffer(1);
				IntBuffer height = BufferUtils.createIntBuffer(1);
				GLFW.glfwGetWindowSize(Window.handle, width, height);
				width.rewind();
				height.rewind();
				
				GLFW.glfwDefaultWindowHints();
				glfwWindowHint(GLFW_VISIBLE, displayVisible ? GL_TRUE : GL_FALSE);
				glfwWindowHint(GLFW_RESIZABLE, displayResizable ? GL_TRUE : GL_FALSE);
				glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL_TRUE);
				
				if (fullscreen) newCurrentWindow(GLFW.glfwCreateWindow(width.get(), height.get(), windowTitle, GLFW.glfwGetPrimaryMonitor(), 0));	
				else newCurrentWindow(GLFW.glfwCreateWindow(width.get(), height.get(), windowTitle, NULL, NULL));
			}
		}
		displayFullscreen = fullscreen;
	}
	
	public static boolean isFullscreen() {
		return displayFullscreen;
	}
	
	public static void setParent(java.awt.Canvas parent) throws LWJGLException {
		// Do nothing as set parent not supported
	}
	
	public static void releaseContext() throws LWJGLException {
		glfwMakeContextCurrent(0);
	}
	
	public static boolean isCurrent() throws LWJGLException {
		return glfwGetCurrentContext() == Window.handle;
	}
	
	public static void makeCurrent() throws LWJGLException {
		glfwMakeContextCurrent(Window.handle);
	}
	
	public static java.lang.String getAdapter() {
		// TODO
		return "GeNotSupportedAdapter";
	}
	
	public static java.lang.String getVersion() {
		// TODO
		return "1.0 NOT SUPPORTED";
	}
	
	/**
	 * An accurate sync method that will attempt to run at a constant frame rate.
	 * It should be called once every frame.
	 *
	 * @param fps - the desired frame rate, in frames per second
	 */
	public static void sync(int fps) {
		if (vsyncEnabled) Sync.sync(fps);
	}
	
	public static Drawable getDrawable() {
		return null;
	}
	
	static DisplayImplementation getImplementation() {
		return null;
	}
	
	private static void newCurrentWindow(long newWindow) {
		GLFW.glfwDestroyWindow(Window.handle);
		Window.handle = newWindow;
		try {
			Mouse.setNativeCursor(Mouse.getCurrentCursor());
		} catch (LWJGLException e) {
			System.err.println("Failed to set new window cursor!");
			e.printStackTrace();
		}
		GLFW.glfwSetWindowTitle(newWindow, windowTitle);
		Window.setCallbacks();
		
		glfwMakeContextCurrent(Window.handle);
		context = org.lwjglx.opengl.GLContext.createFromCurrent();
		
		glfwSwapInterval(1);
		glfwShowWindow(Window.handle);
	}
	
	private static class Window {
		static long handle;
		
		static GLFWKeyCallback keyCallback;
		static GLFWCharCallback charCallback;
		static GLFWCursorEnterCallback cursorEnterCallback;
		static GLFWCursorPosCallback cursorPosCallback;
		static GLFWMouseButtonCallback mouseButtonCallback;
		static GLFWWindowFocusCallback windowFocusCallback;
		static GLFWWindowIconifyCallback windowIconifyCallback;
		static GLFWWindowSizeCallback windowSizeCallback;
		static GLFWWindowPosCallback windowPosCallback;
		static GLFWWindowRefreshCallback windowRefreshCallback;
		static GLFWFramebufferSizeCallback framebufferSizeCallback;
		static GLFWScrollCallback scrollCallback;
		
		public static void setCallbacks() {
			glfwSetKeyCallback(handle, keyCallback);
			glfwSetCharCallback(handle, charCallback);
			glfwSetCursorEnterCallback(handle, cursorEnterCallback);
			glfwSetCursorPosCallback(handle, cursorPosCallback);
			glfwSetMouseButtonCallback(handle, mouseButtonCallback);
			glfwSetWindowFocusCallback(handle, windowFocusCallback);
			glfwSetWindowIconifyCallback(handle, windowIconifyCallback);
			glfwSetWindowSizeCallback(handle, windowSizeCallback);
			glfwSetWindowPosCallback(handle, windowPosCallback);
			glfwSetWindowRefreshCallback(handle, windowRefreshCallback);
			glfwSetFramebufferSizeCallback(handle, framebufferSizeCallback);
			glfwSetScrollCallback(handle, scrollCallback);
		}
		
		public static void releaseCallbacks() {
			keyCallback = null;
			charCallback = null;
			cursorEnterCallback = null;
			cursorPosCallback = null;
			mouseButtonCallback = null;
			windowFocusCallback = null;
			windowIconifyCallback = null;
			windowSizeCallback = null;
			windowPosCallback = null;
			windowRefreshCallback = null;
			framebufferSizeCallback = null;
			scrollCallback = null;
			System.gc();
		}
	}

}
