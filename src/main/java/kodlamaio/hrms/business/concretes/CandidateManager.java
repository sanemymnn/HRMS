package kodlamaio.hrms.business.concretes;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.CandidateValidityService;
import kodlamaio.hrms.core.abstracts.CandidateCheckService;
import kodlamaio.hrms.core.abstracts.EmailCheckService;
import kodlamaio.hrms.core.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.core.utilities.results.ErrorResult;

@Service
public class CandidateManager implements CandidateService{

	
	private CandidateDao candidateDao;
	private CandidateCheckService candidateCheckService;
	private CandidateValidityService candidateValidityService;
	private EmailCheckService emailCheckService;
	private EmailVerificationService emailVerificationService;
	
	
	@Autowired 
	public CandidateManager(CandidateDao candidateDao, CandidateCheckService candidateCheckService,
			CandidateValidityService candidateValidityService, EmailCheckService emailCheckService,
			EmailVerificationService emailVerificationService) {
		super();
		this.candidateDao = candidateDao;
		this.candidateCheckService = candidateCheckService;
		this.candidateValidityService = candidateValidityService;
		this.emailCheckService = emailCheckService;
		this.emailVerificationService = emailVerificationService;
	}

	@Override
	public Result register(Candidate candidate) throws NumberFormatException, RemoteException {
		if(
			(!candidateValidityService.isFirstNameEmpty(candidate.getFirstName()))&& 
			(!candidateValidityService.isLastNameEmpty(candidate.getLastName()))&&
			(!candidateValidityService.isBirthYearEmpty(candidate.getBirthYear()))&&
			(!candidateValidityService.isNationalIdentityEmpty(candidate.getNationalIdentity()))&&
			(!candidateValidityService.isPasswordEmpty(candidate.getPassword()))&&
			(!candidateValidityService.isPasswordAgainEmpty(candidate.getPassword()))&&
			candidateCheckService.checkIfRealPerson(candidate)&&
			emailCheckService.isEmailValid(candidate.getEmail())&&
			candidateValidityService.isEmailUsed(candidate.getEmail())&&
			candidateValidityService.isNationalIdentityUSed(candidate.getNationalIdentity())&&
			Objects.equal(candidate.getPassword(), candidate.getPassword())) {
			
			emailVerificationService.sendVerificationCode(candidate.getEmail());
			
			this.candidateDao.save(candidate);
			
			return new SuccessDataResult("Registration Successful. Please confirm the code sent to your e-mail address.");
		}else {
			return new ErrorResult("Registration failed. Please try again!");
		}
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
	
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Data is listed.");
	}

}
