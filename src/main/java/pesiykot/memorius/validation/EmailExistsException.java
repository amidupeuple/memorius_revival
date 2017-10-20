package pesiykot.memorius.validation;

/**
 * Created by danya_000 on 10/15/2017.
 */
public class EmailExistsException extends RuntimeException {

    public EmailExistsException(final String message) {
        super(message);
    }
}
