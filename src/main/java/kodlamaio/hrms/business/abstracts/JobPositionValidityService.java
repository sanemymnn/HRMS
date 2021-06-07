package kodlamaio.hrms.business.abstracts;

public interface JobPositionValidityService {
	
	boolean isJobPositionEmpty(String position);
	boolean isJobPositionAdded(String position);
}
