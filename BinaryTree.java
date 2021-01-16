import java.util.LinkedList; 
import java.util.Queue; 
/**
 * A class that acts as an abstract data type to help sort players by their different statistics
 * 
 * @author Felix Liao
 * 
 * @version 13 March 26, 2020
 * 
 * @see https://www.baeldung.com/java-binary-tree the website that this was learned from
 */
public class BinaryTree {

    private Node root;
    private LinkedList<Node> sorted = new LinkedList<Node>();
    private LinkedList<PlayerButton> list = new  LinkedList<PlayerButton>();
    /**
     * A node that holds a player's button object, it can contain two more nodes, a left and right node. They can store different player statistics depending on how the user wants to sort them
     * 
     * @author Felix Liao
     * 
     * @version 13 March 26, 2020
     * 
     * @see https://www.baeldung.com/java-binary-tree the website that this was learned from
     */
    private class Node {
        PlayerButton playerButton;

        int points;
        int wins;
        int exp;
        Node left;
        Node right;

        /**
         * The constructor for each Node in this class
         * 
         * @param the PlayerButton object that is stored in each node
         */
        Node(PlayerButton player) {
            playerButton = player;

            this.points = player.getPlayer().getPoints();
            this.wins = player.getPlayer().getWins();
            this.exp = player.getPlayer().getExperience();
            right = null;
            left = null;
        }

        /**
         * A class that acts as an abstract data type to help sort players by their different statistics
         * 
         * @return the playerButton that is stored in each node
         */
        protected PlayerButton getPlayerButton()
        {
            return playerButton;
        }
        // ...
    }

    /**
     * Gets the root of this tree
     * 
     * @return the root Node of the tree
     */
    protected Node getRoot()
    {
        return root;
    }

    /**
     * A method using recursive algorithm to add nodes to the tree which adds by comparing the playerButton's player object's experience
     * 
     * @return the playerButton that is stored in each node
     * 
     * @param current the node that will be added through the recursive method
     * 
     * @param player the playerButton of the node. The method gets and compares the objects experience to decide where to add it to the tree
     */
    private Node addRecursiveExp(Node current, PlayerButton player) {
        int value = player.getPlayer().getExperience();
        if (current == null) {
            return new Node(player);
        }

        if (value <= current.exp) {
            current.left = addRecursiveExp(current.left,player);
        } else  {
            current.right = addRecursiveExp(current.right,player);
        } 

        return current;
    }

    /**
     * A method using recursive algorithm to add nodes to the tree which adds by comparing the playerButton's player object's wins
     * 
     * @return the playerButton that is stored in each node
     * 
     * @param current the node that will be added through the recursive method
     * 
     * @param player the playerButton of the node. The method gets and compares the objects wins to decide where to add it to the tree
     */
    private Node addRecursiveWins(Node current, PlayerButton player) {
        int value = player.getPlayer().getWins();
        if (current == null) {
            return new Node(player);
        }

        if (value <= current.wins) {
            current.left = addRecursiveWins(current.left,player);
        } else if (value > current.wins) {
            current.right = addRecursiveWins(current.right,player);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    /**
     * A method using recursive algorithm to add nodes to the tree which adds by comparing the playerButton's player object's points
     * 
     * @return the playerButton that is stored in each node
     * 
     * @param current the node that will be added through the recursive method
     * 
     * @param player the playerButton of the node. The method gets and compares the objects points to decide where to add it to the tree
     */
    private Node addRecursivePoints(Node current, PlayerButton player) {
        int value = player.getPlayer().getPoints();
        if (current == null) {
            return new Node(player);
        }

        if (value <= current.points) {
            current.left = addRecursivePoints(current.left,player);
        } else if (value > current.points) {
            current.right = addRecursivePoints(current.right,player);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    /**
     * Adds the PlayerButton object to the tree by calling resursive adding method which looks at a playerButton's player's wins
     * 
     * @param vale the PlayerButton that will be added to the tree
     */
    public void addWins(PlayerButton value) {
        root = addRecursiveWins(root, value);
    }

    /**
     * Adds the PlayerButton object to the tree by calling resursive adding method which looks at a playerButton's player's experience
     * 
     * @param vale the PlayerButton that will be added to the tree
     */
    public void addExp(PlayerButton value) {
        root = addRecursiveExp(root, value);
    }

    /**
     * Adds the PlayerButton object to the tree by calling resursive adding method which looks at a playerButton's player's points
     * 
     * @param vale the PlayerButton that will be added to the tree
     */
    public void addPoints(PlayerButton value) {
        root = addRecursivePoints(root, value);
    }

    /**
     * A method that uses a recursive algorithm to traverse the tree while adding the node objects to a LinkedList which will be sorted by the value the tree is sorted by.
     * 
     * @param node the node which contains a PlayerButton
     */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.right);     
            sorted.add(node);  

            traverseInOrder(node.left);
        }
    }

    /**
     * Adds the sorted list into a LinkedList with the object type PlayerButtton. Returns the new list.
     * 
     * @return LinkedList<PlayerButton> returns the new sorted list of PlayerButtons
     */
    public LinkedList<PlayerButton> getSorted()
    {
        for (int i = 0; i < sorted.size();i++)
        {
            list.add(sorted.get(i).getPlayerButton());
        }
        return list;
    }
} 