package kodlamaio.hrms.core.abstracts;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.Candidate;


@Service
public interface CandidateCheckService {
	 boolean checkIfRealPerson(Candidate candidate) throws NumberFormatException, RemoteException;
}
