package BusinessLayer.Health;

public class Health {
    private int healthPoll;
    private int healthAmount;

    public Health(int healthPoll) {
        this.healthPoll = healthPoll;
        this.healthAmount = healthPoll;
    }


    public void increaseHealthPoll(int amount) throws Exception {
        if(amount>0)
            this.healthPoll = healthPoll + amount;
        else
            throw new Exception("cannot decrease healthPoll");
    }

    public int getHealthAmount() {
        return healthAmount;
    }


    public void increaseHealthAmount(int amount) {
        this.healthAmount = healthAmount + amount;
    }

    public void decreaseHealthAmount(int amount) {
        if(healthAmount -amount>0)
            this.healthAmount = healthAmount + amount;
        else
            this.healthAmount =0;
    }

    public void refillHealth(){
        this.healthPoll = this.healthAmount;
    }

    public boolean isDead(){
        return this.healthAmount ==0;
    }



}
