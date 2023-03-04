package org.lwjgl.system;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Objects;

public class LWJGLXHelper {
    public static boolean assumeGlExtensions = loadParam("assume-gl-extensions", true);
    public static boolean resizeRecreateDisplay = loadParam("resize-recreate-display", false);

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
}
