//jfong27, Jason Fong, Assignment4
import java.io.*;
import java.util.Scanner;
public class RecordBST{

   Node root;

   //If no arguments are given, root is initialized to null
   public RecordBST(){
      root = null;
   }

   //If a file is given as argument(dictionary file), all words are 
   //added to the BST.
   public RecordBST(File input){
      root = null;
      try{
         Scanner in = new Scanner(input);

         while(in.hasNext()){
            insert(in.next()); 
         }


      }
      catch (FileNotFoundException ex){
         throw new Error("File not found");
      }
       
   }
    
   //Searches the BST for the given word. If the word comes after, 
   //the left node is searched. Return true if it is found (ignoring case)
   public boolean contains(String word){
      Node current = root;
      while(current != null){
         if(word.compareToIgnoreCase(current.item.word())==0){
            current.item.found();
            return true;
         }
         else if(word.compareToIgnoreCase(current.item.word())<0){
            current = current.left;
         }
         else{
            current = current.right;
         }
      }
      return false;
   }


   //Insert the string into the BST, its flag is automatically null
   //Traverse the tree until a proper empty spot is found for the word.
   public void insert(String x){
      main:
      if (root == null){
         root = new Node(x);
      }
      else{
         Node p = root;
         while(p != null){
            if (p.item.compareTo(x)>0){
               if(p.left != null){
                  p = p.left;
               }
               else{
                  p.left = new Node(x);
                  break main;
               }
            }
            else{
               if (p.right != null){p = p.right;}
               else{
                  p.right = new Node(x);
                  break main;
               }
            }
         }
      }
   }

   //Method goes through every node and prints each word which has a 
   //true flag
   public void print_flagged(Node p){
      if(p != null){
         print_flagged(p.left);
         if(p.item.flag()){
            System.out.println(p.item.word());
         }
         print_flagged(p.right);
      }
   }

   //Node class to be used by the BST
   public class Node{
      ItemRecord item;
      Node left;
      Node right;
      
      public Node(String x){
         item = new ItemRecord(x);
         left = null;
         right = null;
      }
   }






}





