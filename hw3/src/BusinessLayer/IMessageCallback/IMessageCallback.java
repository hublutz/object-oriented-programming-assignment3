package BusinessLayer.IMessageCallback;

public interface IMessageCallback {
    /**
     * The method that is used to pass massages ti other classes
     * @param message the massage
     */
    public void passMessage(String message);
}
