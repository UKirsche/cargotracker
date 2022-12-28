package api.rest;

import api.exceptions.UnknownUriException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
@Slf4j
public class UnknownUriExceptionMapper implements ResponseExceptionMapper<UnknownUriException> {

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        log.info("status = " + status);
        return status == 404;
    }

    @Override
    public UnknownUriException toThrowable(Response response) {
        return new UnknownUriException();
    }
}