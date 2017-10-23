
import java.io.IOException;
import javax.servlet.ServletContext;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.HeaderConfig;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;
import org.atmosphere.util.ServletContextFactory;
import org.atmosphere.websocket.WebSocketEventListenerAdapter;

@AtmosphereHandlerService
public class AtmosphereHandler extends AbstractReflectorAtmosphereHandler {

    @Override
    public void onRequest(AtmosphereResource ar) throws IOException {
        suspendAtmosphereResourceIfNecessary(ar);
    }

    private void suspendAtmosphereResourceIfNecessary(
            AtmosphereResource resource) {

        AtmosphereRequest req = resource.getRequest();
        AtmosphereResponse resp = resource.getResponse();
        String method = req.getMethod();

        if ("GET".equalsIgnoreCase(method)) {

            resource.addEventListener(new WebSocketEventListenerAdapter());
            resp.setContentType("text/html;charset=ISO-8859-1");

            Broadcaster b = lookupBroadcaster(req);
            resource.setBroadcaster(b);

            if (req.getHeader(HeaderConfig.X_ATMOSPHERE_TRANSPORT)
                    .equalsIgnoreCase(HeaderConfig.LONG_POLLING_TRANSPORT)) {
                req.setAttribute(ApplicationConfig.RESUME_ON_BROADCAST, Boolean.TRUE);
            }
            resource.suspend(-1);
        }

    }

    private Broadcaster lookupBroadcaster(AtmosphereRequest req) {
        ServletContext servletContext = ServletContextFactory.getDefault().getServletContext();
        BroadcasterFactory broadcasterFactory = (BroadcasterFactory) servletContext.getAttribute(BroadcasterFactory.class.getName());
        // aca puedes crear un broadcaster por el dato que te acomode. Algun dato de sesion como el usuario conectado, un id unico etc.
        // para efectos de prueba es la ip del usuario conectado. (==getRemoteAddr)
        // req.getRequest().getRemoteAddr(), true);
        return broadcasterFactory.lookup(req.getRemoteHost(), true);
    }
}
