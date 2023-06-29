package BusinessLayer.Tiles.Units.Players.Mage;

public class Mana {

    /**
    * Initial mana amount as a portion of manaPool
    */
    private static final int INITIAL_MANA_PROPORTION_OF_POOL = 4;
    /**
     * manaPool field, maximum amount of mana that a player can have
     */
    private int manaPool;
    /**
     * manaPool field, current manaAmount
     * */
    private int manaAmount;

    /**
     * Mana class constructor
     * @param manaPool starting manaPool
     * */
    public Mana(int manaPool){
        this.manaPool = manaPool;
        this.manaAmount = manaPool/INITIAL_MANA_PROPORTION_OF_POOL;
    }
    /**
     * ManaAmount getter
     * @return the  manaAmount
     * */
    public int getManaAmount() {
        return manaAmount;
    }

    /**
     * Function that is used by the player to use mana
     * @param amount amount of mana to use
     * @return true if operation was successful throws exception otherwise
     * @throws Exception if amount <=0 or player doesn't have enough mana
     * */
    public boolean useMana(int amount) throws Exception {
        if(amount >=0){
            if(manaAmount - amount >=0){
                this.manaAmount-=amount;
                return true;
            }else
               return false;
        }else
            throw new Exception("amount cant be negative");
    }
    /**
     * The function that increases the manaPool
     * @param increaseBy the amount of which to increase the manaPoll
     * @throws Exception if increaseBy <=0
     * */
    public void increaseManaPool(int increaseBy) throws Exception {
        if (increaseBy > 0)
            this.manaPool = this.manaPool + increaseBy;

        else
            throw new Exception("cannot increase by negative amount");
    }
    /**
     * Getter for getManaPool
     * @return The mana pool
     * */
    public int getManaPool(){
        return this.manaAmount;
    }

    /**
     * This method increases the current mana with the value specified.
     * If the current mana will be bigger than the mana pool, the current
     * mana will be the mana pool
     * @param amount the amount of mana to add
     */
    public void refillMana(int amount){
        if(this.manaAmount +amount > this.manaPool)
            manaAmount = manaPool;
        else
            manaAmount+= amount;

    }
}
