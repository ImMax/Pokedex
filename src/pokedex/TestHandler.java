package pokedex;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHandler extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String res = "";
        Map<String, String[]> str = request.getParameterMap();
        for(String[] s : str.values()) {
            res += Arrays.toString(s) + "\n";
        }
        for(int i=1; i <= str.values().size(); i++) {
            res += "select" + i + ": " + Arrays.toString(str.get("select" + i));
        }
    }
}
