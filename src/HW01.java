import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;


/* Andreas Hinsch
 * 
 *  an478360
 * 
 *  COP3505
 */
 
public class HW01 {
	
	public static void main(String[] args) throws IOException {
		
		//The Command token
		String instruction;
		
		//The value of the integer being put into the BST
		int num;
		
		//The BST root
		Node root = null;
		
		//Used to write out all the commands in the file
		String writer;
		
		//Used to see if a call to delete needs to be made by checking if the number is in the bst
		int isin;
		
		//Takes in the name of the input file
		Scanner user = new Scanner(System.in);
		String InputFile = user.nextLine().trim();
		user.close();
		
		//Tries to open the input file
			Scanner Reads = OpenFile(InputFile);
            Scanner Fulltxt = OpenFile(InputFile);
			
            //Writes to STDOUT
			FileWriter STDOUT = new FileWriter("STDOUT.txt");
			
			//Writes to STDERR
			FileWriter STDERR = new FileWriter("STDERR.txt");
         
			STDOUT.write(InputFile+" contains:\n");
			
			//Writes the full text out
			while(Fulltxt.hasNext()) {
				writer = Fulltxt.next();
				if(writer.equals("i")||writer.equals("s")||writer.equals("d")) {

					STDOUT.write(writer+" "+Fulltxt.next());
				}
				else{STDOUT.write(writer);}
				STDOUT.write("\n");
			}

			
        while(Reads.hasNextLine()) {
        	//Takes the instruction
        	instruction = Reads.next();

        		//Adds a node
        	if(instruction.equals("i")) {
        		
        		//takes in the value
        		num = Reads.nextInt();
        		
        		//Adds to the BST
        		root = BinaryTree.insertNode(root, num);
        		
        	}
        	
        	//searches for a node
        	else if(instruction.equals("s")) {
        		
        		//takes in the value
        		num = Reads.nextInt();
        		
        		//Searches the BST
        		isin = BinaryTree.Search(root, num);

        		//if it is in print to STDOUT else STDERR
        		if(isin == 1) {
        			STDOUT.write("command-> s "+num+": integer "+num+" found\n");
        		}
        		else {
        			STDERR.write("command-> s "+num+": integer "+num+" NOT found\n");
        		}
        	}
        	
        	
        	 
        	//Prints the tree in order
        	else if(instruction.equals("p")) {
        		
        		BinaryTree.PrintInorder(root, STDOUT);
        		STDOUT.write("\n");
        		
        	}
        	//Deletes a node
        	else if(instruction.equals("d")) {
        		
        	
        		num = Reads.nextInt();
        		isin = BinaryTree.Search(root, num);

        		if(isin == 1) {
        			root = BinaryTree.deleteNode(root, num);
        		}
        		else {
        			STDERR.write("command-> d "+num+": integer "+num+" NOT found\n");
        		}
        		
        	}
        	
        	//quits and closes the pointers
        	else if(instruction.equals("q")) {
     
        		
        		Quit(root, STDOUT, STDERR);
        		STDOUT.close();
        		STDERR.close();
        		Reads.close();
        		System.exit(0);}
        		
        }

	}
	
	//Quits
	public static void Quit(Node root, FileWriter STDOUT, FileWriter STDERR) throws IOException {
		

		STDOUT.write("left children:          "+countChildren(root.left)+"\n");
		STDOUT.write("left depth:             "+ getDepth(root.left)+"\n");
		
		STDOUT.write("right children:         "+countChildren(root.right)+"\n");
		STDOUT.write("right depth:            "+ getDepth(root.right)+"\n");
		complexity_indicator(STDERR);
	}
	
	public static void complexity_indicator(FileWriter STDERR) throws IOException{
		STDERR.write("an478360;");
		STDERR.write("2;");
		STDERR.write("12"+"\n");
	}
	
	//Counts children
	public static int countChildren(Node root) {
		
		int numChildren = 1;
		
		if(root.left != null) {
			numChildren += countChildren(root.left);
			
		}
		if(root.right != null) {
			numChildren += countChildren(root.right);
		}
		
		return numChildren;
		
	}

	public static int getDepth(Node root){
		if(root == null) {
			return 0;
		}
		else {
			int temp = getDepth(root.left);
			int temp2 = getDepth(root.right);
			
			if(temp > temp2) {
				return(temp + 1);
			}
				else {
					return(temp2 +1);
				}
			}
		}
		

	
	private static Scanner OpenFile(String InputFile) {
		try {
			
		File input = new File(InputFile);
		Scanner ReaderIn = new Scanner(input);
		return ReaderIn;
		}
		catch(FileNotFoundException e){
			System.out.println("error file not found");
		}
		return null;
		
	}

	}
	
