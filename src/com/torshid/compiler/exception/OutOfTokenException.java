package com.torshid.compiler.exception;

public class OutOfTokenException extends ParserException {

  private static final long serialVersionUID = 1L;

  public OutOfTokenException(String message) {
    super(message);
  }

}
