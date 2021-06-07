package kodlamaio.hrms.core.adapters;

import java.rmi.RemoteException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import kodlamaio.hrms.core.abstracts.CandidateCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;

@Component
@Primary
public class FakeMernisServiceAdapter implements CandidateCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) throws NumberFormatException, RemoteException {
	
		return true;
	}

}
