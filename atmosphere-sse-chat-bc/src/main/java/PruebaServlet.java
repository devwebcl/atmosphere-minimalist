
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.util.ServletContextFactory;

public class PruebaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ip = request.getParameter("ip");
        System.out.println("ip = " + ip);
        String mensaje = request.getParameter("mensaje");
        System.out.println("mensaje = " + mensaje);

        ServletContext servletContext = ServletContextFactory.getDefault().getServletContext();
        BroadcasterFactory broadcasterFactory = (BroadcasterFactory) servletContext.getAttribute(BroadcasterFactory.class.getName());
        Broadcaster bc = broadcasterFactory.lookup(ip, true);
        if (bc != null) {
            bc.broadcast(mensaje);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
