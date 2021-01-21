package socketserver.netty.websocket;

public class TestObject {

    public static boolean isEmpty(Object test) {
        if (test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "")
            return true;
        else
            return false;
    }

    public static boolean notEmpty(Object test) {
        if (test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "")
            return false;
        else
            return true;
    }

}