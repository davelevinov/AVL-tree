package HW5;

public class AVLTree {

    AVLNode root; 	// The tree root.
    int size;		// The size of the tree.
   
    /**
     * Construct an empty tree.
     */
    public AVLTree() {
       	root = null;
       	size = 0;
    }

    /**
     * Insert into the tree; You may assume that every tank is unique.
     * That is, no tank will appear twice.
     * 
     * @param t the tank to insert.
     */
    public void insert(Tank t) {
    	// if first node inserted in the tree
        System.out.println("inside insert");
    	if (root == null){
    		root = new AVLNode(t);
            size++;
            System.out.println("root, size= " + size);
    	}
    	else {
    		AVLNode current = root;      	
    		while (true){

    			// inserted is smaller than root, left
    			if (t.compareTo(current.t) < 0){  
    				if (current.left == null) {
                        current.left = new AVLNode(t, null, null, current);
                        break;
                    }
                    else {
                        current = current.left; 
                    }
    			}
    			// inserted is larger than root, right 
    			else {
                    if (current.right == null) {
                        current.right = new AVLNode(t,  null, null, current);
                        break;
                    }
                    else {
    				    current = current.right;
                    }
    			}
    			
    		}
            size++;
            System.out.println("after while, size= " + size);

            updateHeight(current);

            rebalance(current);
    	}
    }
    
    // re-balancing tree bottom up from Node
    private void rebalance(AVLNode x){
    	 System.out.println("inside rebalance");          
         while(x != null) {   		 
    		int bf = bf(x);

    		 // left heavy 
             if (bf > 1) {
            	 int lcbf = bf(x.left);
                 // same side
                 if (lcbf * bf == 2) {
                     rRotate(x);
                 }
                 // alternating sides
                 if (lcbf * bf == -2) {
                     lRotate(x.left);
                     rRotate(x);
                 }
                 // right heavy
             } else if (bf < -1) {
            	 int rcbf = bf(x.right);
                 // same side
                 if (rcbf * bf == 2) {
                     lRotate(x);
                 }
                 // alternating side
                 if (rcbf * bf == -2) {
                     rRotate(x.right);
                     lRotate(x);
                 }
             }
             x = x.parent;
         }
    }

    // balance factor calculation
    private int bf(AVLNode x){
    	return getHeight(x.left) - getHeight(x.right);
    }

    // height calculation for a node
    private int getHeight(AVLNode x){
    	int left, right;
        // base case recursion
    	if(x == null){
            return 0;
        }
        if (x.left != null)
            left = 1 + x.left.height;
        else 
            left = 0;
        if (x.right != null)
            right = 1 + x.right.height;
        else
            right = 0;
        return Math.max(left, right);        
    }

    // update height from given node up
    private void updateHeight(AVLNode x){
        AVLNode current = x.parent;
        while (current != null){
            current.height = getHeight(current);
            current = current.parent;
        }
    }
    // if right heavy
    private void lRotate(AVLNode x){
    	AVLNode y = x.right;
        y.parent = x.parent;

        x.right = y.left;

        if (x.right != null)
            x.right.parent = x;

        y.left = x;
        x.parent = y;

        if (y.parent != null) {
            if (y.parent.right == x) {
                y.parent.right = y;
            }
            else {
                y.parent.left = y;
            }
        }
        // if y.parent null - means x was root, so set it to y now
        else {
            this.root = y;
        }
               
        updateHeight(x);
        updateHeight(y);
    }
    
    // if left heavy
    private void rRotate(AVLNode x){ 
        AVLNode y = x.left;
        y.parent = x.parent;

        x.left = y.right;

        if (x.left != null)
            x.left.parent = x;

        y.right = x;
        x.parent = y;

        if (y.parent != null) {
            if (y.parent.right == x) {
                y.parent.right = y;
            } 
            else {
                y.parent.left = y;
            }
        }
        // if y.parent null - means x was root, so set it to y now
        else {
            this.root = y;
        }
        updateHeight(x);
        updateHeight(y);
    }

    /**
     * Search for a tank in the tree.
     * 
     * @param t the tank to search for.
     * @return the matching tank or null if not found.
     */
    public Tank search(Tank t) {
    	AVLNode current = root;
        // move down tree
        while(current != null){
            int compared = t.compareTo(current.t);
            // if matched return the node
            if(compared == 0){
                System.out.println("found!");
                return current.t;
            }
            // if larger than node go right
            if(compared > 0){
                //System.out.println("going right");
                current = current.right;
            }
            // if smaller than node go left
            else{
                //System.out.println("going left");
                current = current.left;
            }
        }
    	return null;
    }
    
    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     * 
     * @return a sorted array of the tree's content.
     */
    Tank[] inorder(){
    	Tank[] sortedt = new Tank[size];
        AVLNode current = root;

        arrayFill(sortedt, 0, current);
        
        // went over all tanks
        if (sortedt.length == size) return sortedt;
        
    	return null;
    }
    // array filling function
    private int arrayFill(Tank[] arr, int i, AVLNode current){
         if (current != null){
             i = arrayFill(arr, i, current.left);
             //System.out.println("arrayFill: left i = " + i + " size= " + this.size);
             arr[i] = current.t;
             //System.out.println("arrayFill - found tank= " + arr[i].serialNumber + " at i= " + i);
             i++;
             //System.out.println("arrayFill: self i = " + i + " size= " + this.size);
             i = arrayFill(arr, i, current.right);
             //System.out.println("arrayFill: right i = " + i + " size= " + this.size);
         }  
         return i;  	
    }
}