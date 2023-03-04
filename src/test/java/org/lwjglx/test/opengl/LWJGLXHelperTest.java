package org.lwjglx.test.opengl;

import org.junit.jupiter.api.Test;
import org.lwjgl.system.LWJGLXHelper;

public class LWJGLXHelperTest {
    private static class TestGetCallerName {
        static String getSelfName() {
            return LWJGLXHelper.getCallerName();
        }
    }

    @Test
    public void testGetCallerName() {
        String selfName = TestGetCallerName.getSelfName();
        if (!selfName.equals(LWJGLXHelperTest.class.getName())) {
            throw new IllegalArgumentException("Got " + selfName);
        }
    }
}
