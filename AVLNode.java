package HW5;
// Basic node stored in AVL trees

class AVLNode {
	
	Tank t;				// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height - used as counter of nodes beneath

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param t - the data of the node.
	 */
    AVLNode(Tank t) {
          this.t = t;
          parent = null;
          left = null;
          right = null;
          height = 0;
    }
    
    /**
     * A standard constructor
     * 
     * @param t - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Tank t, AVLNode lt, AVLNode rt, AVLNode parent){  
    	this.t = t;
    	left = null;  
    	right = null; 
    	this.parent = parent;
    	height = 0; 
    }
}