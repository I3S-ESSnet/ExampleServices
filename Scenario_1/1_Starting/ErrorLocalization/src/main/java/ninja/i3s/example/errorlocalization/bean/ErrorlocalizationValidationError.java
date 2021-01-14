package ninja.i3s.example.errorlocalization.bean;

public class ErrorlocalizationValidationError {
    
    public String error;
    public String errorNumber;
    public String errorDescription;

    public ErrorlocalizationValidationError() {}

    public ErrorlocalizationValidationError(String error, String errorNumber, String errorDescription) {
        this.error = error;
        this.errorNumber = errorNumber;
        this.errorDescription = errorDescription;
    }

    public String getError() {
		return this.error;
	}
	public void setError(String  error) {
		this.error = error;
    }
    
    public String getErrorNumber() {
		return this.errorNumber;
	}
	public void setErrorNumber(String  errorNumber) {
		this.errorNumber = errorNumber;
    }
    
    public String getErrorDescription() {
		return this.errorDescription;
	}
	public void setErrorDescription(String  errorDescription) {
		this.errorDescription = errorDescription;
	}

}
