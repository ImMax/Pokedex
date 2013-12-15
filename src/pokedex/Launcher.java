package pokedex;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Launcher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        WebAppContext webapp = new WebAppContext("/home", "");
        webapp.setInitParameter("org.apache.jasper.compiler.disablejsr199", "true");
        webapp.setInitParameter("org.eclipse.jetty.servlet.Default.compilerTargetVM", "1.7");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{webapp});
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
