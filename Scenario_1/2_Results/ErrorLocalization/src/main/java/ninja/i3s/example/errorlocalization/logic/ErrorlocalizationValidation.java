package ninja.i3s.example.errorlocalization.logic;

import java.util.ArrayList;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate(String country, String weather) {

        ArrayList<ErrorlocalizationValidationError> errors = new  ArrayList<ErrorlocalizationValidationError>();
   
        ErrorlocalizationValidationError error;

        //for each rule not passed, setup a new error and add it to the list
        {
        error = new ErrorlocalizationValidationError();
        error.setError("Weather not allowed");
        error.setErrorDescription("Country cannot have this weather");
        error.setErrorNumber("001");
        
        errors.add(error);
        } 

        return errors;
    }
  
}
