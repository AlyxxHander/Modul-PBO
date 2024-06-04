package exception.custom;

public class InvalidChoice extends Exception {
    public InvalidChoice(String message) throws InvalidChoice {
        super(message);
    }
}
