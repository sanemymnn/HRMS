package kodlamaio.hrms.core.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.abstracts.EmailCheckService;


@Service
public class EmailCheckManager implements EmailCheckService {

	@Override
	public boolean isEmailValid(String email) {
		
		 java.util.regex.Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);

	        if(matcher.matches()) {
	            return true;
	        }else if(email.isEmpty()){
	        	System.out.println("Email girmelisiniz!");
	        	return false;
	        }else {
	        	System.out.println("Geçersiz email!");
	            return false;
	        }
	}

}
