//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm;

import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setName("dummy");
		user.setEmail("dummy@gmail.com");
		user.setPassword(this.passwordEncoder.encode("dummy"));
		user.setRolesList(List.of("ROLE_USER"));
		user.setEmailVerified(true);
		user.setEnabled(true);
		user.setAbout("this is dummy account");
		user.setPhoneVerified(true);
		this.userRepo.findByEmail("dummy@gmail.com").ifPresentOrElse((user1) -> {
		}, () -> this.userRepo.save(user));
	}
}
