package kodlamaio.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateValidityService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;


@Service
public class CandidateValidityManager implements CandidateValidityService {

	private CandidateDao candidateDao;
	
	public CandidateValidityManager(CandidateDao candidateDao) {
		super();
		this.candidateDao=candidateDao;
	}
	
	@Override
	public boolean isFirstNameEmpty(String firstName) {
		if(firstName.isEmpty()) {
			System.out.println("You must enter your name.");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isLastNameEmpty(String lastName) {
		if(lastName.isEmpty()) {
			System.out.println("You must enter your last name");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isBirthYearEmpty(int birthYear) {
		if(String.valueOf(birthYear).isEmpty()) {
			System.out.println("You must enter yout birth year.");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isNationalIdentityEmpty(String nationalIdentity) {
		if(nationalIdentity.isEmpty()) {
			System.out.println("You must enter your nationality identity number.");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPasswordEmpty(String password) {
		if(password.isEmpty()) {
			System.out.println("You must enter password.");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPasswordAgainEmpty(String password) {
		if(password.isEmpty()) {
			System.out.println("You must enter password again.");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isEmailUsed(String email) {
		for(Candidate candidate : candidateDao.findAll()) {
			if(email.equals(candidate.getEmail())) {
				System.out.println("Email is used before. Please enter a new email.");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isNationalIdentityUSed(String nationalIdentity) {
		for(Candidate candidate : candidateDao.findAll()) {
			if(nationalIdentity.equals(candidate.getNationalIdentity())) {
				System.out.println("Nationality identity number is saved before!");
				return false;
			}
		}
		return true;
	}

}
