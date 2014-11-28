package highrq.core.services.exceptions;

public class PhoneDoesNotExistException extends RuntimeException {

  public PhoneDoesNotExistException(Throwable cause) {
    super(cause);
  }

  public PhoneDoesNotExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public PhoneDoesNotExistException(String message) {
    super(message);
  }

  public PhoneDoesNotExistException() {
  }
}

