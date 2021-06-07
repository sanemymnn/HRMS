package kodlamaio.hrms.business.abstracts;

public interface CandidateValidityService {
	
	boolean isFirstNameEmpty(String firtsName);
	boolean isLastNameEmpty(String lastName);
	boolean isBirthYearEmpty(int birthYear);
	boolean isNationalIdentityEmpty(String nationalIdentity);
	boolean isPasswordEmpty(String password);
	boolean isPasswordAgainEmpty(String password);
	
	boolean isEmailUsed(String email);
	boolean isNationalIdentityUSed(String nationalIdentity);
}
