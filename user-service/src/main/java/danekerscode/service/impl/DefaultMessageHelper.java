package danekerscode.service.impl;

import danekerscode.service.MessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageHelper implements MessageHelper {

    @Value("${server.host:localhost}")
    private String host;

    @Value("${server.port:3000}")
    private Integer port;

    @Value("${server.schema:http}")
    private String schema;

    @Value("${server.servlet.context-path:}")
    private String contextPath;


    @Override
    public String getPrefix() {
        return String.format("%s://%s:%d%s", schema, host, port, contextPath);
    }
}
