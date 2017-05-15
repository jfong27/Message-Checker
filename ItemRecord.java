//Jason Fong, jfong27, ItemRecord.java, Assignment4
public class ItemRecord implements Comparable{
 
   //Token holds words to be stored in BST, flag is for alarm words
   //and whether or not they have been found. A true flag has been found
   private String token;
   private boolean flag;

   public ItemRecord(String word){
      //Initialize token to the given word, flag is false by default
      token = word;
      flag = false; 
   }

   public String word(){
      return token;
   }

   public boolean flag(){
      return flag;
   }

   public void found(){
      flag = true;
   }

   public int compareTo(Object x){
      //Method must be implemented, but is not used
      return 0;
   }
   
   public int compareTo(String x){
      return token.compareToIgnoreCase(x);
   }


}
