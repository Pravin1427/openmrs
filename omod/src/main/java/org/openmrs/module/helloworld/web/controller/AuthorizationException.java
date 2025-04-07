package org.openmrs.module.helloworld.web.controller;

import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Insufficient privileges")
public class AuthorizationException extends ResponseException {
}