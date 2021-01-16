/**
 * This object holds player information
 * 
 * @author Felix Liao
 *
 * @version 13 March 26, 2020
 * 
 */
public class Player
{
    private static int playerCount = 0;
    private String name;
    private boolean isGirl;
    private int age;
    private int experience;
    private int wins =0;
    private int losses = 0;
    private int points=0;
    private String clubName = "none";
    
    /**
     * The defualt constructor of this class
     * 
     */
    public Player()
    {
        name = null;
        isGirl = false;
        age = 0;
        experience = 0;
    }

    /**
     * Creates constructor of a new player.
     * 
     * @param isGirl the player's gender
     *
     * @param name the player's name
     * 
     * @param age the player's age
     * 
     * @param exp the player's years of experience
     * 
     * @param clubName the player's club they attend
     * 
     */
    public Player (boolean isGirl, String name, int age, int exp, String clubName)
    {
        this.name = name;
        this.age= age;
        this.isGirl = isGirl;
        this.experience = exp;
        this.clubName = clubName;

        playerCount = playerCount + 1;
    }

    /**
     * This constructor is used when loading players because they will not have defualt fields
     * 
     * @param isGirl the player's gender
     *
     * @param name the player's name
     * 
     * @param age the player's age
     * 
     * 
     * @param exp the player's years of experience
     * 
     * @param clubName the player's club they attend
     * 
     */
    public Player (boolean isGirl, String name, int age, int exp, int wins, int losses, int points, String clubName)
    {
        this.name = name;
        this.age= age;
        this.isGirl = isGirl;
        this.experience = exp;
        this.clubName = clubName;
        this.wins = wins;
        this.losses = losses;
        this.points = points;

        playerCount = playerCount + 1;
    }

    /**
     * Sets the player's name
     * 
     * @param name the player's name
     */
    protected void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the player's age
     * 
     * @param age the player's age
     */
    protected void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Sets the player's wins
     * 
     * @param wins the player's wins
     */
    protected void setWins(int wins)
    {
        this.wins = wins;
    }

    /**
     * Sets the player's losses
     * 
     * @param losses the player's losses
     */
    protected void setLosses(int losses)
    {
        this.losses = losses;
    }

    /**
     * Sets the player's exp
     * 
     * @param exp the player's exp
     */
    protected void setExp(int exp)
    {
        this.experience = exp;
    }

    /**
     * Sets the player's gender
     * 
     * @param gender the player's gender
     */
    protected void setGender(boolean gender)
    {
        this.isGirl = gender;
    }

    /**
     * Sets the player's club they attend
     * 
     * @param clubName the player's club name they attend 
     */
    protected void setClub(String clubName)
    {
        this.clubName = clubName;
    }

    /**
     * Sets the player's club they points
     * 
     * @param points the player's points
     */
    protected void setPoints(int points)
    {
        this.points = points;
    }

    /**
     * Gets the class's total player count 
     * 
     * @return the class's total playerCount
     */
    protected static int getPlayerCount()
    {
        return playerCount;
    }

    /**
     * Gets the object's class name
     * 
     * @return the objects clubName
     */
    protected  String getClubName()
    {
        return clubName;
    }

    /**
     * Sets the class's total player count to 0
     */
    protected static void resetCount()
    {
        playerCount = 0;
    }

    /**
     * Increases the class's total player count
     */
    protected static void addCount()
    {
        playerCount = playerCount + 1;
    }

    /**
     * Gets the object's name
     * 
     * @return the object's name
     */
    protected String getName()
    {
        return name;
    }

    /**
     * Gets the object's age
     * 
     * @return the object's age
     */
    protected int getAge()
    {
        return age;
    }

    
    /**
     * Gets the object's gender
     * 
     * @return the object's isGirl
     */
    protected boolean getGender()
    {
        return isGirl;
    }

    /**
     * Gets the object's points
     * 
     * @return the object's points
     */
    protected int getPoints()
    {
        return points;
    }

    /**
     * Gets the object's gender as a String
     * 
     * @return the object's gender as a String
     */
    public String getGenderString()
    {
        if (isGirl)
        {
            return "female";
        }
        else
        {
            return "male";
        }
    }

    /**
     * Gets the object's wins
     * 
     * @return the object's wins
     */
    protected int getWins()
    {
        return wins;
    }

    /**
     * Increase the object's wins by 1
     */
    protected void addWin()
    {
        wins++;
    }

    /**
     * Increase the object's points by the parameter
     * 
     * @param i the number of points the player scored
     * 
     */
    protected void addPoints(int i)
    {
        points = points + i;
    }

    /**
     * Increase the object's points by the parameter
     * 
     * @return the number of points the player scored
     * 
     */
    protected int getLosses()
    {
        return losses;
    }

    /**
     * Increase the object's losses by 1
     * 
     */
    protected void addLoss()
    {
        losses++;
    }

    /**
     * Gets the object's years of experience
     * 
     * @return the object's experience
     * 
     */
    protected int getExperience()
    {
        return experience;
    }

}
