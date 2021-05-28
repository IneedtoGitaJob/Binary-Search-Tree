import java.io.FileWriter;
import java.io.IOException;

/* Andreas Hinsch
 * 
 *  an478360
 * 
 *  COP3505
 */
 
public class BinaryTree {
	
	public static boolean flag = false;

	//Inserts nodes
	public static Node insertNode(Node root, int num) {
		
		//If the Tree is empty make the tree
		if(root == null) {
			root = new Node();
			root.val = num;
			root.left = null;
			root.right = null;
			return root;
		}
		//If num less go left
		else {
		if(num < root.val) {
			root.left = insertNode(root.left, num);
		}
		//If num greater go right
		else if(num > root.val) {
			root.right = insertNode(root.right, num);
		}
		//If the num is equal to a number already in the tree
		else {
			root.right = insertNode(root.right, num);
		}

			
		}
		
		
		return root;
	}

	//Determines if the node is present or not
	public static int Search(Node root, int num){
		flag = false;
		searchNode(root, num);

		if(flag) {
			
			return 1;
		}
		else{
		return 0;}
	}
	
	//Searchs for the node
	public static void searchNode(Node root,int num) {
		
		if(root == null) {
			flag = false;
			return;
		}
		
		
		//If we found the node
		if(num == root.val) {
			flag = true;
			return;
		}
		//if the node is to the left
		if(num < root.val && root.left != null) {
			searchNode(root.left,num);
		}
		//if the node is to the right
		else if(num > root.val && root.right != null) {
			searchNode(root.right,num);
		}

	}
	
	//Finds and deletes a node
	public static Node deleteNode(Node root, int num){
		
		//If root is null return the head
		if(root == null) {
			return root;
		}
		
		//go left
		if(num < root.val) {
			root.left = deleteNode(root.left, num);
		}
		//go right
		else if(num > root.val) {
			root.right = deleteNode(root.right, num);
		}
		//If the num is equal to a number in the tree i.e. we found the node
		else {
			//If there is a right child
			if(root.left == null && root.right != null) {return root.right;}
			
			//If there is a left child
			else if(root.right == null) {return root.left;}
			
			//If there are two children
			else if(root.right != null && root.left != null) {
				Node temp = root;
			     Node min = deleteHelper(temp.right);
					root.val = min.val;
					root.right = deleteNode(root.right, min.val);
				
			}
			
					
					//If there are no children
					else if(root.left == null && root.right == null){
						
						root = null;
						
					}
		}

			
		return root;
		
	}
	
	//A helper function for deleting nodes by finding the lowest value
	static Node deleteHelper(Node root) {
		
//If it is the lowest value
if(root.left == null) {return root;}
else {
	return deleteHelper(root.left);
}
	}
	
	//prints the tree Inorder
	public static void PrintInorder(Node root,FileWriter STDOUT) throws IOException{
		if(root == null){
			return;
		}
		PrintInorder(root.left, STDOUT);
		STDOUT.write(root.val+" ");
		PrintInorder(root.right, STDOUT);
		
		
	}
	
	
	
}
