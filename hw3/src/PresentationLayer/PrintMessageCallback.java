package PresentationLayer;

import BusinessLayer.IMessageCallback.IMessageCallback;

/**
 * PrintMessageCallback is a message callback which prints the messages in the CLI
 */
public class PrintMessageCallback implements IMessageCallback
{
    /**
     * This method prints the given message
     * @param message the massage passed
     */
    @Override
    public void passMessage(String message)
    {
        System.out.println(message);
    }
}
