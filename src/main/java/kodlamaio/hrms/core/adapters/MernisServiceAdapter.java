package kodlamaio.hrms.core.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

import kodlamaio.hrms.core.abstracts.CandidateCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Component
public class MernisServiceAdapter implements CandidateCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) throws NumberFormatException, RemoteException {
		
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		
		return kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(candidate.getNationalIdentity()),
				candidate.getFirstName().toUpperCase(), candidate.getLastName().toUpperCase(),
				candidate.getBirthYear());
	}
}


