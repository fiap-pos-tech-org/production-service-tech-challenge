package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.web.handlers.ErrorDetails;

public class ErrorDetailsHelper {

    private ErrorDetailsHelper() {
    }

    public static ErrorDetails criarErrorDetails(int httpStatus) {
        return new ErrorDetails.Builder()
                .status(httpStatus)
                .message("alguma exception")
                .timestamp(System.currentTimeMillis())
                .build();
    }

}
