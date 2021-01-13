package ninja.i3s.example.errorlocalization;

import java.util.ArrayList;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate() {

        ErrorlocalizationValidationError error = new ErrorlocalizationValidationError();
        ArrayList<ErrorlocalizationValidationError> errors = new  ArrayList<ErrorlocalizationValidationError>();

        error.Error = "Test";
        error.ErrorDescription = "This is an error";
        error.ErrorNumber = "001";
        
        errors.add(error);

        return errors;
    }
}
