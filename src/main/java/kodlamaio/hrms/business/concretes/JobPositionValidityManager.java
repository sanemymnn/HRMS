package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionValidityService;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionValidityManager implements JobPositionValidityService {

	

	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionValidityManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}
	
	@Override
	public boolean isJobPositionEmpty(String position) {
		if(position.isEmpty()) {
			System.out.println("You must enter a position!");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isJobPositionAdded(String position) {
		for(JobPosition jobPosition : jobPositionDao.findAll()) {
			if(position.equals(jobPosition.getPosition())) {
				System.out.println("Job position is added before.");
				return true;
			}
		}
		return false;
	}

}
