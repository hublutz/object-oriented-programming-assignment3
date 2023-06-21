package BusinessLayer.Units.Health;

public class Health {
    private static final int DEAD_HEALTH_AMOUNT =0 ;
    private int healthPool;
    private int healthAmount;

    /**
     * Constructor of health
     *
     * @param healthPool the health pool of the unit and its initial health amount
     */
    public Health(int healthPool) {
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
    }

    /**
     * The method that is used to increase the healthPool
     *
     * @param amount the amount to increase the healthPool
     */
    public void increaseHealthPool(int amount) throws Exception {
        if(amount>DEAD_HEALTH_AMOUNT)
            this.healthPool = healthPool + amount;
        else
            throw new Exception("cannot decrease healthPool");
    }
    /**
     *  Getter of healthAmount
     * @return the healthAmount
     */
    public int getHealthAmount() {
        return healthAmount;
    }

    /**
     * The method that is used to increase the healthAmount
     *
     * @param amount the amount to increase the healthAmount
     */
    public void increaseHealthAmount(int amount) {
        if(healthAmount + amount > healthPool){
            refillHealth();
            return;
        }
        this.healthAmount = healthAmount + amount;
    }

    /**
     * The method that is used to decrease the healthAmount
     *
     * @param amount the amount to decrease the healthAmount
     */
    public void decreaseHealthAmount(int amount) {
        if(healthAmount -amount>DEAD_HEALTH_AMOUNT)
            this.healthAmount = healthAmount + amount;
        else
            this.healthAmount =DEAD_HEALTH_AMOUNT;
    }

    /**
     * The method that is used to refill the healthAmount to the healthPool number

     */
    public void refillHealth(){
        this.healthPool = this.healthAmount;
    }

    /**
     * The method that is used to check if a unit is dead
     */
    public boolean isDead(){
        return this.healthAmount ==DEAD_HEALTH_AMOUNT;
    }



}
