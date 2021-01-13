package ninja.i3s.example.errorlocalization;

public class ErrorlocalizationValidationError {
    
    public String Error;
    public String ErrorNumber;
    public String ErrorDescription;

    public ErrorlocalizationValidationError() {}

    public ErrorlocalizationValidationError(String error, String errorNumber, String errorDescription) {
        Error = error;
        ErrorNumber = errorNumber;
        ErrorDescription = errorDescription;
    }
}
