package Tests.Units;

import BusinessLayer.IMessageCallback.IMessageCallback;

public abstract class AbstractPlayerTest {

    public char c ;
    public int x ;
    public int y;
    public String name ;
    public int healthPool;
    public int attackPoints ;
    public int defencePoints;
    public String lastM;
    IMessageCallback messageCallback;

    public AbstractPlayerTest(){
        lastM ="";
        c ='5';
        x =0;
        y =0;
        healthPool = 20;
        name ="name";
        attackPoints = 10;
        defencePoints = 10;
        messageCallback = this::onMessageCallBack;
    }

    public void onMessageCallBack( String message){
        lastM =  message;
    }

}
