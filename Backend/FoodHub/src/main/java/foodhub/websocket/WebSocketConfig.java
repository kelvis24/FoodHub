package foodhub.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * What happens here is that the server end point -- in this case it is
 * the web socket end point handler is registered with SPRING
 * so that requests to ws:// will be honored.
 *
 * @author Vamsi Krishna Calpakkam
 *
 */
// @Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}