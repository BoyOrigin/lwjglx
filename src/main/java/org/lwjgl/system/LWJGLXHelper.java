package org.lwjgl.system;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class LWJGLXHelper {
    public static boolean assumeGlExtensions = loadParam("assume-gl-extensions", true); // LWJGLX_ASSUME_GL_EXTENSIONS
    public static boolean attrRecreateDisplay = loadParam("attr-recreate-display", true); // LWJGLX_ATTR_RECREATE_DISPLAY
    public static boolean resizeRecreateDisplay = loadParam("resize-recreate-display", false); // LWJGLX_RESIZE_RECREATE_DISPLAY
    public static boolean awtCanvasNoCreate = loadParam("awt-canvas-no-create", false); // LWJGLX_AWT_CANVAS_NO_CREATE
    public static boolean earlyDisplayCreate = loadParam("early-display-create", false); // LWJGLX_EARLY_DISPLAY_CREATE
    public static boolean earlyDisplayResizable = loadParam("early-display-resizable", false); // LWJGLX_EARLY_DISPLAY_RESIZEABLE

    static {
        try {
            Class.forName("org.lwjgl.BufferUtils").getDeclaredMethod("getOffset", Buffer.class);
        } catch (Exception e) {
            System.err.println("LWJGLX Wasn't loaded before LWJGL, limited support is available.");
        }
    }

    private static boolean loadParam(String key, boolean def) {
        String property = System.getProperty("org.lwjglx." + key);
        if (property == null || property.isEmpty())
            property = System.getenv("LWJGLX_" + key
                    .toUpperCase(Locale.ROOT).replace('-', '_'));
        return property == null || property.isEmpty() ?
                def : Boolean.parseBoolean(property.toLowerCase(Locale.ROOT));
    }

    public static ByteBuffer getBuffer(CustomBuffer<?> customBuffer) {
        return Objects.requireNonNull(customBuffer.container);
    }

    public static String getCallerName() {
        return getCallerName(2);
    }

    public static String getCallerName(int i) {
        RuntimeException ex = new RuntimeException();
        StackTraceElement[] stackTrace = ex.getStackTrace();
        ArrayList<String> list = new ArrayList<>();
        for (StackTraceElement element : stackTrace)
            if (!element.getClassName().startsWith("sun.reflect") &&
                    !element.getClassName().startsWith("java.lang.reflect")) {
                list.add(element.getClassName());
            }
        try {
            return list.get(i + 1);
        } catch (Exception e) {
            return null;
        }
    }
}
