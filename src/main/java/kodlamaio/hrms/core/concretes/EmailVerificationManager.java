package kodlamaio.hrms.core.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.abstracts.EmailVerificationService;


@Service
public class EmailVerificationManager implements EmailVerificationService {

	@Override
	public void sendVerificationCode(String email) {
		
		System.out.println("email" + "adresine doğrulama e-postası gönderildi.");
	}

}
