package avile.utils;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BookUtils {

    
    public BookUtils() {
        
    }
    
    public static boolean checkIfValidISBN(String trialISBN) {
        //is not empty
        if(trialISBN==null || trialISBN.equals("")) {
            return false;
        }
        
        //must be either ISBN-10 or ISBN-13 sized string
        if(trialISBN.length()!=10+4 && trialISBN.length()!=13+4) {
            return false;
        }
        
        // checks if ISBN-10 is correct
        if(trialISBN.length()==10+4) {
            String trialTenDigits = "";
            trialTenDigits = trialISBN.substring(4, trialISBN.length());
            ArrayList<Integer> digitsList = new ArrayList<>();

            for(int i = 0; i < trialTenDigits.length(); i++) {
                // in case last number is 10 and therefor marked as x
                if(i==9) {
                    char c = trialTenDigits.charAt(i);
                    if(c == 'x' || c == 'X') {
                        digitsList.add(10);
                        continue;
                    }
                }
                int num = Character.getNumericValue(trialTenDigits.charAt(i));
                digitsList.add(num);
            }

            int isbnSum = 0;
            int multiplier = 10;
            for(int i = 0; i < digitsList.size(); i++) {

                isbnSum += (multiplier * digitsList.get(i));
                multiplier--;
            }
            if(isbnSum % 11 != 0) {
                return false;
            }
        }
        
        // checks if ISBN-13 is correct
        if(trialISBN.length()==13+4) {
            String trial13Digits = "";
            trial13Digits = trialISBN.substring(4, trialISBN.length());
            ArrayList<Integer> digitsList = new ArrayList<>();
            for(int i = 0; i < trial13Digits.length(); i++) {
                
                int num = Character.getNumericValue(trial13Digits.charAt(i));
                digitsList.add(num);
            }
            
            int isbnSum = 0;
            int multiplier = 1;
            for(int i = 0; i < digitsList.size()-1; i++) {
                System.out.println(digitsList.get(i));
                isbnSum += (multiplier * digitsList.get(i));
                
                // change the multiplier, alternating between 1 and 3
                if(multiplier==1) {
                    multiplier = 3;
                } else {
                    multiplier = 1;
                }

            }
            int remainder = isbnSum % 10;

            if(digitsList.get(12)!= 10 - remainder) {
                return false;
            }
        }

        return true;
    }
    
    
    
    
}
