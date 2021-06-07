package kodlamaio.hrms.core.concretes;

import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import kodlamaio.hrms.core.abstracts.EmailDomainCheckService;


@Service
public class EmailDomainCheckManager implements EmailDomainCheckService {

	@Override
	public boolean isEmailAndDomainEqual(String website, String email) {
		
		boolean result = false;
		
		String[] domain = website.split("\\.");
		String[] mail = email.split("@|\\.");
		
		if (Objects.equal(mail[1], domain[1])) {
			result = true;
		}
		System.out.println("Domain adınız ve mail adresiniz aynı değil! ");
		return result;
	}

}
