package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI employeeUrl(long employeeId) {
        return URI.create(String.format("%semployees/%d", baseUri, employeeId));
    }

    public URI attendanceUrl(long attendanceId) {
        return URI.create(String.format("%sattendances/%d", baseUri, attendanceId));
    }
}
