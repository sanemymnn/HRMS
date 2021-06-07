package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.abstracts.JobPositionValidityService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {
	
	
	private JobPositionDao jobPositionDao;
	private JobPositionValidityService  jobPositionValidityService;
	

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao, JobPositionValidityService jobPositionValidityService) {
		super();
		this.jobPositionDao = jobPositionDao;
		this.jobPositionValidityService = jobPositionValidityService;
	}


	@Override
	public List<JobPosition> getAll() {
		
		return this.jobPositionDao.findAll();
	}


	@Override
	public Result add(JobPosition jobPosition) {
		if(
				(!jobPositionValidityService.isJobPositionEmpty(jobPosition.getPosition()))&&
				(!jobPositionValidityService.isJobPositionAdded(jobPosition.getPosition()))) {
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("Job position is added successfuly.");
		}
		
		return new ErrorResult("Addition failed.");
	}

}
