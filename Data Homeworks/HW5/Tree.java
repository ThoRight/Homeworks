/**
 * @author MuratErbilici
 * @since 03.05.2023
 */



import javax.swing.*;  
import javax.swing.tree.DefaultMutableTreeNode; 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Tree{
    private JTree jt;
    private JFrame f;
    private String [][] dataFromFile;
    private int lineSize=0;
    private int lineCapacity=10;
    private int columnSize=0;
    private int columnCapacity=10;
    private DefaultMutableTreeNode root;

    /**
     * constructor..
     * it takes the data fromfile, create a tree with JTRee and add the tree to JFrame.
     * @param filename filename that we will get the data.
     */
    Tree(String filename) throws FileNotFoundException{
        dataFromFile = new String[lineCapacity][columnCapacity];
        getdata(filename);
        f = new JFrame("Tree");
        root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode newNode = null;
        DefaultMutableTreeNode newNode2 = null;
        boolean check=false;
        for(int i=0;i<dataFromFile.length;++i){
            check = isSame(root,dataFromFile[i][0]);
            if(dataFromFile[i][0] != null && check == false){
                newNode = new DefaultMutableTreeNode(dataFromFile[i][0]);
                root.add(newNode);
            }
            else if(dataFromFile[i][0] != null && check == true){
                newNode = searchNode(root,dataFromFile[i][0]);
            }
            for(int j=1;j<dataFromFile[i].length;++j){
                check = isSame(newNode,dataFromFile[i][j]);
                if(dataFromFile[i][j] != null && check == false){
                    newNode2 = new DefaultMutableTreeNode(dataFromFile[i][j]);
                    newNode.add(newNode2);
                    newNode = searchNode(newNode,dataFromFile[i][j]);
                }
                else if(dataFromFile[i][j] != null && check == true){
                    newNode = searchNode(newNode,dataFromFile[i][j]);
                }
            }
        }
        jt=new JTree(root);
        f.add(jt);
        f.setSize(200,600);
        f.setVisible(true);
        
    }
    /**
     * to get the data from file.
     * It uses useDelimiter() method to seperate node names.
     * @param filename Name of file.
     */
    public void getdata(String filename) throws FileNotFoundException{
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int tempcolumnsize = columnSize;
        while (sc.hasNextLine()){
            String temp = sc.nextLine();
            Scanner scanner = new Scanner(temp);
            scanner.useDelimiter(";");
            while(scanner.hasNextLine()){
                dataFromFile[lineSize][tempcolumnsize] = scanner.next();
                ++tempcolumnsize;
                if(tempcolumnsize==columnCapacity){
                    resizeColumnCapacity();
                }
            }
            tempcolumnsize = 0;
            ++lineSize;
            if(lineSize==lineCapacity){
                resizeLineCapacity();
            }
        }
        
    }

    /**
     * to increase the capacity of array for first dimension.
     */
    private void resizeLineCapacity(){
        String [][] temp = new String[lineCapacity*2][columnCapacity];
        for(int i=0;i<lineCapacity;++i){
            for(int j=0;j<columnCapacity;++j){
                temp[i][j] = dataFromFile[i][j];
            }
        }
        dataFromFile = temp;
        lineCapacity = lineCapacity*2;
    }

    /**
     * to increase the capacity of array for second dimension.
     */
    private void resizeColumnCapacity(){
        String [][] temp = new String[lineCapacity][columnCapacity*2];
        for(int i=0;i<lineCapacity;++i){
            for(int j=0;j<columnCapacity;++j){
                temp[i][j] = dataFromFile[i][j];
            }
        }
        dataFromFile = temp;
        columnCapacity = columnCapacity*2;
    }

    /**
     * helper method to determine whether tree has already the node that we want to add.
     * @param root parent node.
     * @param str String that we are checking.
     * @return true if There is a node named str, false otherwise.
     */
    public boolean isSame(DefaultMutableTreeNode root, String str){
        if(str == null){
            return false;
        }
        for(int i=0;i<root.getChildCount();++i){
            if(str.equals(root.getChildAt(i).toString())) 
                return true;
        }
        return false;
    }

    /** 
     * to return child node of parent node according to parameters.
     * @param root Parent node that we want to check.
     * @param str String that we are checking.
     * @return Node if there is a child node of parent node named str.
     * @return null if there isn't child node of parent node named str.
    */
    public DefaultMutableTreeNode searchNode(DefaultMutableTreeNode root, String str){
        for(int i=0;i<root.getChildCount();++i){
            if(str.equals(root.getChildAt(i).toString())) 
                return (DefaultMutableTreeNode)root.getChildAt(i); 
        }
        return null;
    }

    /**
     * BFS search according to parameter.
     * @param str Node name that we want to find with BFS search.
     */
    public void BFSsearch(String str){
        System.out.printf("Using BFS to find '%s' in the tree...\n", str);
        Queue<DefaultMutableTreeNode> q = new LinkedList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode tempNode;
        int step = 1;
        boolean found = false;
        q.add(root);
        while(!q.isEmpty()){
            tempNode = q.poll();
            System.out.printf("Step %d -> %s", step, tempNode.toString());
            if(str.equals(tempNode.toString())){
                found = true;
                break;
            }
            else{
                System.out.printf("\n");
            }
            for(int i=0;i<tempNode.getChildCount();++i){
                q.add((DefaultMutableTreeNode)tempNode.getChildAt(i));
            }
            ++step;
        }
        if(found) System.out.println(" (Found!)");
        else System.out.println("Not Found.");
    }

    /**
     * DFS search according to parameter.
     * @param str Node name that we want to find with DFS search.
     */
    public void DFSsearch(String str){
        System.out.printf("Using DFS to find '%s' in the tree...\n", str);
        Stack<DefaultMutableTreeNode> s = new Stack<DefaultMutableTreeNode>();
        DefaultMutableTreeNode tempNode;
        int step = 1;
        boolean found = false;
        s.push(root);
        while(!s.isEmpty()){
            tempNode = s.pop();
            System.out.printf("Step %d -> %s", step, tempNode.toString());
            if(str.equals(tempNode.toString())){
                found = true;
                break;
            }
            else{
                System.out.printf("\n");
            }
            for(int i=0;i<tempNode.getChildCount();++i){
                s.push((DefaultMutableTreeNode)tempNode.getChildAt(i));
            }
            ++step;
        }
        if(found) System.out.println(" (Found!)");
        else System.out.println("Not Found.");
    }

    /**
     * post-order traversal search according to parameter.
     * @param input Node name that we want to find with post-order traversal search.
     */
    public void postOrderSearch(String input){
        System.out.printf("Using Post-Order traversal to find '%s' in the tree...\n", input);
        Stack<DefaultMutableTreeNode> s = new Stack<DefaultMutableTreeNode>();
        boolean found = false;
        fillStackRec(s,root);
        int step=1;
        while(!s.isEmpty()){
            System.out.printf("Step %d -> ", step);
            String str = s.pop().toString();
            System.out.print(str);
            if(input.equals(str)){
                found = true;
                break;
            }
            else{
                System.out.printf("\n");
            }
            ++step;
        }
        if(found) System.out.println(" (Found!)");
        else System.out.println("Not Found.");
    }

    /**
     * fill the stack with recursion according to parent node
     * it pushes from last child of parent to first. Because we want to search from left to right.
     */
    public void fillStackRec(Stack<DefaultMutableTreeNode> s, DefaultMutableTreeNode root){
        for(int i=root.getChildCount()-1;i>=0;--i){
            s.push((DefaultMutableTreeNode)root.getChildAt(i));
            fillStackRec(s,(DefaultMutableTreeNode)root.getChildAt(i));
        }
    }



    /**
     * to take source and destination inputs and seperate them to move the related node.
     * it checks if there is a node according to user input. if there is, it invokes the move method and removes the source node.
     * after moving, if parent node is empty, it removes the parent node.
     */
    public void moveOperation(){
        int size=0;
        int cap = columnCapacity;
        String [] source = new String[cap];
        String destination;
        System.out.println("Enter the source location that you want to move with comma and without space between them.(For example: 2022,CSE321,Lecture1)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        System.out.println("Enter the destination year that you want to move(For example: 2023)");
        sc = new Scanner(System.in);
        destination = sc.nextLine();

        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(",");
        while(scanner.hasNextLine()){
            source[size] = scanner.next();
            ++size;
            if(size==cap){
                resizeSourceCapacity(source,cap);
                cap*=2;
            }
        }
        //to prevent errors and also we cannot move the years which are child of the root.
        if(size<=1){
            System.out.println("Please Enter proper input.");
            return;
        }
        DefaultMutableTreeNode movedNode = root;
        DefaultMutableTreeNode preNode = root;
        DefaultMutableTreeNode preNode2 = root;
        boolean flag=true;
        for(int i=0;i<size;++i){
            preNode2 = preNode;
            preNode = movedNode;
            movedNode = searchNode(movedNode,source[i]);
            if(movedNode == null){
                flag=false;
                noTreeError(source,size);
                break;
            }
        }
        if(flag){
            System.out.printf("Moved ");
            printMovedNode(source,size);
            System.out.printf(" to %s.\n", destination);
            preNode.remove(movedNode);
            moveNode(source,size,movedNode,destination);
            if(preNode.getChildCount()==0 && preNode != root){ 
            // after moving, if there is no child, removes the parent. it works for only years which are childs of root.
                preNode2.remove(preNode);
            }
        }

        // to reprint the tree.
        f.remove(jt);
        jt=new JTree(root);
        f.add(jt);
        f.setSize(200,600);
        f.setVisible(true);

    }

    /**
     * to increase the capacity of source array.
     * @param str source array.
     * @param cap current capacity of source array.
     */
    private void resizeSourceCapacity(String [] str, int cap){
        String [] tempStr = new String[cap*2];
        for(int i=0;i<cap;++i){
            tempStr[i] = str[i];
        }
        str = tempStr;
        cap*=2;
    }

    /**
     * to print error when there is no node to move according to user input.
     * @param source source array.
     * @param size size of source array.
     */
    private void noTreeError(String [] source, int size){
        System.out.printf("Cannot move ");
        printMovedNode(source,size);
        System.out.println(" Because it doesn't exist in the tree.");
    }

    /**
     * to print movedNode with their parent nodes.
     * @param source source array.
     * @param size size of source array.
     */
    public void printMovedNode(String [] source, int size){
        for(int i=0;i<size;++i){
            if(i!=size-1){
                System.out.printf("%s->",source[i]);
            }
            else{
                System.out.printf("%s",source[i]);
            }
        }
    }

    /**
     * to print that node has been overwritten.
     * difference between printMovedNode and this method is that this prints destination year and movedNode with parents.
     */
    public void printOverwrittenNode(String [] source, int size, String destination){
        System.out.printf("%s->",destination);
        for(int i=1;i<size;++i){
            if(i!=size-1){
                System.out.printf("%s->",source[i]);
            }
            else{
                System.out.printf("%s",source[i]);
            }
        }
    }
    /**
     * to move the node to new location.
     * if destination year doesn't exist, it creates and move the node.
     * if node already exists, it overwrites and prints. While overwriting, it removes the current node in destination, and adds the source node.
     * if destination year exists, but there is no parent nodes same as source node, it creates parent nodes and move the node.
     * Search algorithm for part E is the if-else part. It decides what happen according to above conditions.
     * @param source source array.
     * @param size size of source array.
     * @param movedNode the node that we want to move.
     * @param destination new destination of movedNode.
     */
    public void moveNode(String [] source, int size, DefaultMutableTreeNode movedNode, String destination){
        DefaultMutableTreeNode destNode = root;
        DefaultMutableTreeNode preNode = root;
        DefaultMutableTreeNode tempNode = root;
        destNode = searchNode(root,destination);
        if(destNode==null){
            for(int i=0;i<size-1;++i){
                if(i==0){
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(destination);
                    tempNode.add(newNode);
                    tempNode = newNode;
                }
                else{
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(source[i]);
                    tempNode.add(newNode);
                    tempNode = newNode;
                }
            }
            tempNode.add(movedNode);
        }
        else{
            for(int i=1;i<size;++i){
                preNode = destNode;
                destNode = searchNode(destNode,source[i]);
            //    While overwriting, it removes the current node in destination, and adds the source node.
                if(destNode != null && i == size-1){
                    preNode.remove(destNode);
                    preNode.add(movedNode);
                    printOverwrittenNode(source,size,destination);
                    System.out.println(" has been overwritten.");
                }
                else if(destNode == null){
                    for(int j=i;j<size-1;++j){
                        DefaultMutableTreeNode tnode = new DefaultMutableTreeNode(source[j]);
                        preNode.add(tnode);
                        preNode = tnode;
                    }
                    preNode.add(movedNode);
                    break;
                }
            }
        }
    }

    /**
     * menu to take inputs from the user.
     */
    public void menu(){
        boolean loop=true;
        while(loop){
            System.out.println("Please enter the number:");
            System.out.println("1. Search with BFS. (PART B)");
            System.out.println("2. Search with DFS. (PART C)");
            System.out.println("3. Search with post order traversal algorithm. (PART D)");
            System.out.println("4. Move node. (PART E)");
            System.out.println("5. Quit.");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.length() != 1){
                System.out.println("Please enter proper input.");
            }
            else{
                switch(input.charAt(0)){
                    case '1':
                        System.out.print("Enter the node name to be searched with BFS algorithm: ");
                        sc = new Scanner(System.in);
                        input = sc.nextLine();
                        BFSsearch(input);
                        break;
                    case '2':
                        System.out.print("Enter the node name to be searched with DFS algorithm: ");
                        sc = new Scanner(System.in);
                        input = sc.nextLine();
                        DFSsearch(input);
                        break;
                    case '3': ;
                        System.out.print("Enter the node name to be searched with post order traversal algorithm: ");
                        sc = new Scanner(System.in);
                        input = sc.nextLine();
                        postOrderSearch(input);
                        break;
                    case '4':
                        moveOperation();
                        break;
                    case '5':
                        System.out.println("Quitting.....");
                        loop = false;
                        break;
                    default:
                        System.out.println("Please enter proper input.");
                        break;
                }
            }
        }
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }


}
