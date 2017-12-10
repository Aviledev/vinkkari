package avile.validator;

import avile.domain.BookRecommendation;
import avile.domain.Tag;
import java.util.ArrayList;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class IsbnValidator implements Validator{
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Tag.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRecommendation bookRecommendation = (BookRecommendation) target;
        
       if(!checkIfValidISBN(bookRecommendation)) {
           errors.rejectValue("isbn", "Length", "Not valid ISBN");
       }
       
    }
    private static boolean checkIfValidISBN(BookRecommendation bookRecommendation) {
        String trialISBN = bookRecommendation.getIsbn();
        if(trialISBN==null || trialISBN.equals("")) {
            return false;
        }

        trialISBN = trialISBN.toUpperCase().replace("ISBN", "").replace(" ", "").replace("-", "");
                
        //must be either ISBN-10 or ISBN-13 sized string
        if(trialISBN.length()!=10 && trialISBN.length()!=13) {
            return false;
        }
        
        // checks if ISBN-10 is correct
        if(trialISBN.length()==10) {
            ArrayList<Integer> digitsList = new ArrayList<>();

            for(int i = 0; i < trialISBN.length(); i++) {
                // in case last number is 10 and therefor marked as x
                if(i==9) {
                    char c = trialISBN.charAt(i);
                    if(c == 'x' || c == 'X') {
                        digitsList.add(10);
                        continue;
                    }
                }
                int num = Character.getNumericValue(trialISBN.charAt(i));
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
        if(trialISBN.length()==13) {
            ArrayList<Integer> digitsList = new ArrayList<>();
            for(int i = 0; i < trialISBN.length(); i++) {
                
                int num = Character.getNumericValue(trialISBN.charAt(i));
                digitsList.add(num);
            }
            
            int isbnSum = 0;
            int multiplier = 1;
            for(int i = 0; i < digitsList.size()-1; i++) {
                
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
        bookRecommendation.setIsbn(trialISBN);
        return true;
    }
}
