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

public class TestClass{
    public static void main(String[] args)throws FileNotFoundException{  
        Tree t1 = new Tree("tree.txt");
        t1.menu();
    }
}