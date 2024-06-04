package exception.custom;

public class illegalAdminAccess extends Exception {
    public illegalAdminAccess(String message) throws illegalAdminAccess {
        super(message);
    }
}
