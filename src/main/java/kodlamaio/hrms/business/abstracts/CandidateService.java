package kodlamaio.hrms.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateService {
	Result register(Candidate candidate) throws NumberFormatException, RemoteException;
	DataResult<List<Candidate>> getAll();
}
