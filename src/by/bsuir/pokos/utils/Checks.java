package by.bsuir.pokos.utils;

public class Checks {
    
   public static final String EMAIL_REGEX="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
           + "{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\"
           + "x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
           + "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]"
           + "|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
   
   public static final String TELEPHONE_REGEX="^\\+(?:[0-9] ?){6,14}[0-9]$";  
   public static final String CAPITALS_AND_NUMBERS_REGEX="^[A-Z0-9]+$";
   public static final String POSTAL_CODE_REGEX="^[a-zA-Z0-9-]*$"; 
   
   public static boolean isNumber(String string) {
       
         if (string == null || string.length() == 0) return false;
 
         int i = 0;
         if (string.charAt(0) == '-') {
            if (string.length() == 1) {
               return false;
            }
            i = 1;
         }
 
         char c;
         for (; i < string.length(); i++) {
             c = string.charAt(i);
             if (!(c >= '0' && c <= '9')) {
                 return false;
             }
         }
         return true;
     }
   
   public static boolean isEmpty(String str) {
       return str==null|| str.isEmpty();
   }
   
}
