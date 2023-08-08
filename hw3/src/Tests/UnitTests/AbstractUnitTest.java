package Tests.UnitTests;

import BusinessLayer.IMessageCallback.IMessageCallback;

public abstract class AbstractUnitTest {

    protected char c ;
    protected int x ;
    protected int y;
    protected String name ;
    protected int healthPool;
    protected int attackPoints ;
    protected int defencePoints;
    protected String lastM;
    protected IMessageCallback messageCallback;

    public AbstractUnitTest(){
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
