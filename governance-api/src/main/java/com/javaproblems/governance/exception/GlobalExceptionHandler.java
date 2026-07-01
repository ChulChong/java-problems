package com.javaproblems.governance.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * TODO: implement exception -> HTTP status mapping.
 *
 * Requirements:
 * - ProposalNotFoundException      -> 404 Not Found
 * - DuplicateVoteException         -> 409 Conflict
 * - InvalidProposalStateException  -> 400 Bad Request
 * - MethodArgumentNotValidException (bean validation) -> 400 Bad Request with field errors
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO: add @ExceptionHandler methods here
}
