import java.io.*;
import java.util.Scanner;
public class CheckMessage{
   
   
   public static Scanner scanner;
   public static String word;

   public static void main(String[] args){
      File message = new File(args[0]);
      File alarm;
      String[] alarm_names = new String[args.length-2];
      RecordBST spell_check = new RecordBST(new File(args[1]));
      RecordBST alarm_words = new RecordBST();

   
      System.out.println("Analyzing the file " + args[0] + "...\n");
    
      //Create BST for alarm words, which are located args[2] and beyond
      for(int i = 2; i < args.length; i++){
         alarm = new File(args[i]);
         alarm_names[i-2] = args[i];
         try{
            scanner = new Scanner(alarm);
            //Insert words into the BST
            while(scanner.hasNext()){
               alarm_words.insert(scanner.next());
            }
         }
         catch(FileNotFoundException ex){
            throw new Error("File not found");
         }

      }
       
      //Call methods to check for spelling and alarm words 
      check_spelling(spell_check, message, args[1]);
      
      check_alarm(alarm_words, message, alarm_names);      
      

   }

   private static void check_spelling(RecordBST check, File msg, String unsort){ 
           
      //Check all words for spelling errors     

      System.out.println("Checking against the file " + unsort + ":"); 
      System.out.println("These words were not found:");
      try{
         scanner = new Scanner(msg);
         
         while(scanner.hasNext()){
            word = scanner.next();
            //If the BST contains the word, then nothing must be done
            if(check.contains(word)){
               continue;
            }
            //Otherwise, the word is checked if its plural
            //If it is still not found, letters are swapped to find 
            //possible spelling errors
            else{
               if(remove_end(word, check)){
                  continue;
               }
               else{
                  swap_letters(word, check);
               }
            }
         }
      }
      catch (FileNotFoundException ex){
         throw new Error("File not found");
      }
   }

   private static void check_alarm(RecordBST alarm_words, File msg, String[] al){
      
      System.out.print("\nChecking against the alarm files "); 
      for (int i = 0; i < al.length-1; i++){
         System.out.print(al[i] + ",");
      }
      System.out.println(al[al.length-1] + ":");
      System.out.println("These words were found:");

      try{
         scanner = new Scanner(msg);

         //While loops checks every word against the alarm words BST
         //The contains method flags words that are found, 
         while(scanner.hasNext()){
            word = scanner.next();
            if(alarm_words.contains(word)){
               continue;
            }
         }
      }
      catch(FileNotFoundException ex){
         throw new Error("File not found");
      }
   
      alarm_words.print_flagged(alarm_words.root);
   }

      
   private static void swap_letters(String word, RecordBST check){
      String swapped = "";
      int len = word.length();
      char[] array = word.toCharArray();

      //Loop goes through and swaps neighboring characters in the array
      //It then puts them back together in a string to check against BST
      for(int i = 0; i < len -1; i++){
         char temp = array[i+1];
         array[i+1] = array[i];
         array[i] = temp;
         swapped = new String(array);
         if (check.contains(swapped)){
            break;
         }
         else{
            array = word.toCharArray();
            swapped = "";
         }
      }
      System.out.println(word + "    possible spellings: " + swapped);
   }


   private static boolean remove_end(String word, RecordBST check){
      //Checks words for specific endings, then removes them and
      //checks against BST. Return true if it is found, false otherwise
    
      int len = word.length();
      if (word.endsWith("s") ){
         if(check.contains(word.substring(0,len-1))){
            return true;
         }
      }
      if (word.endsWith("es") ){
         if(check.contains(word.substring(0,len-2))){
            return true;
         }
      }
      if (word.endsWith("ing")){
         if (check.contains(word.substring(0,len-3))){
            return true;
         }
      }
      return false;
   }


}
