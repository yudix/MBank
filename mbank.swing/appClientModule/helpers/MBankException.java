package helpers;

public class MBankException extends Exception {
	private String errorMessgae;

	private static final long serialVersionUID = 5030227803532623887L;
	public MBankException() {
	}

	public MBankException(String errorMessage) {
		this.errorMessgae = errorMessage;
	}


	@Override
	public String getMessage() {
		if (super.getMessage() != null) {
			return super.getMessage() + "\n" + errorMessgae;
		} else {
			return errorMessgae;
		}
	}

	@Override
	public String getLocalizedMessage() {
		if (super.getLocalizedMessage() != null) {
			return super.getLocalizedMessage() + "\n" + errorMessgae;
		} else {
			return errorMessgae;
		}
	}

	@Override
	public String toString() {
		return "MBankException [errorMessgae=" + errorMessgae + "]";
	}

}
