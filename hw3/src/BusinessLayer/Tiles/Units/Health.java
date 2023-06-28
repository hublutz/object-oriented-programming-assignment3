package BusinessLayer.Tiles.Units;

public class Health
{
    private static final int DEAD_HEALTH_AMOUNT =0 ;
    private int healthPool;
    private int healthAmount;

    /**
     * Constructor of health
     *
     * @param healthPool the health pool of the unit and its initial health amount
     */
    public Health(int healthPool)
    {
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
    }

    /**
     *  Getter of healthAmount
     * @return the healthAmount
     */
    public int getHealthAmount()
    {
        return this.healthAmount;
    }

    /**
     * Getter for healthPool
     * */
    public int getHealthPool()
    {
        return this.healthPool;
    }

    /**
     * The method that is used to increase the healthPool
     *
     * @param amount the amount to increase the healthPool
     */
    public void increaseHealthPool(int amount) throws Exception
    {
        if(amount > DEAD_HEALTH_AMOUNT)
            this.healthPool += amount;
        else
            throw new Exception("Cannot decrease health pool");
    }

    /**
     * The method that is used to increase the healthAmount
     *
     * @param amount the amount to increase the healthAmount
     */
    public void increaseHealthAmount(int amount) throws Exception
    {
        if (this.isDead())
        {
            throw new Exception("Cannot increase health amount of a dead unit");
        }
        if(this.healthAmount + amount > this.healthPool)
        {
            this.refillHealth();
            return;
        }
        this.healthAmount += amount;
    }

    /**
     * The method that is used to decrease the healthAmount
     *
     * @param amount the amount to decrease the healthAmount
     */
    public void decreaseHealthAmount(int amount) {
        if(this.healthAmount - amount > DEAD_HEALTH_AMOUNT)
            this.healthAmount -= amount;
        else
            this.healthAmount = DEAD_HEALTH_AMOUNT;
    }

    /**
     * The method that is used to refill the healthAmount to the healthPool number

     */
    public void refillHealth() throws Exception
    {
        if (this.isDead())
        {
            throw new Exception("Cannot refill health amount of a dead unit");
        }
        this.healthPool = this.healthAmount;
    }

    /**
     * The method that is used to check if a player is dead
     */
    public boolean isDead()
    {
        return this.healthAmount == DEAD_HEALTH_AMOUNT;
    }
}
