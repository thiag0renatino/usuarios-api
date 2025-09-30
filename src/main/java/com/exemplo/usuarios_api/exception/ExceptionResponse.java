package com.exemplo.usuarios_api.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String deatils) {
}

