package otherObjects;

/**
 * Creates instance of 'robot' which has got its own making cost.
 * With possibility to create an array of robots with incrmeent cost starting from 0....size
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class Robot {

    private int cost;   // cost of the robot

    /**
     * Robot class constructor taking cost(price to make) as parameter and assigning it to robot itself
     *
     * @param cost  price needed to make a robot. Must be integer
     */
    Robot(int cost) {
        this.cost = cost;
    }


    /**
     * Creates desired amount Robot objects and stores them into one Robot Objects Array
     * assigning the increment cost to each robot [1,2,3...... size]
     *
     * @param size  determines how many robots needs to be created (size of array)
     * @return      returns generated Robots Objects Array
     */
    public static Robot[] createRobotArmy(int size) {
        Robot[] robotArmy = new Robot[size]; // create new instance of Robot Object Array

        /*
        * Structure:
        *   1. Create Robot Object assigning the increment cost for it
        *   2. Store Robot Object into Robot Object Array
        * */
        for(int i = 0; i < size; i++)
        {
            robotArmy[i] = new Robot(i);
        }
        return robotArmy; // returns generated Robots Objects Array
    }
}
