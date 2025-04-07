package org.openmrs.module.helloworld.web.controller;

import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Authentication required")
public class AuthenticationException extends ResponseException {
}