package it.uniroma3.siw.catering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.catering.model.Admin;
import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.repository.AdminRepository;
import it.uniroma3.siw.catering.repository.CredentialsRepository;
import it.uniroma3.siw.catering.service.AdminService;

@SpringBootApplication
public class SiwPersonaleApplication implements CommandLineRunner {
  @Autowired
	private CredentialsRepository credentialsRepository;
  @Autowired
	protected AdminRepository adminRepository;
  @Autowired
  protected PasswordEncoder passwordEncoder;
  @Autowired
  protected AdminService as;
  
	public static void main(String[] args) {
		SpringApplication.run(SiwPersonaleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*	Admin u = new Admin();
	u.setNome("Simone");
	u.setCognome("De Sanctis");
		
		Credentials c = new Credentials();
	c.setAdmin(u);
	c.setPassword(this.passwordEncoder.encode("simone"));
		c.setUsername("simone");
	c.setRole("ADMIN");
		
	credentialsRepository.save(c);	*/
	}
	

}
