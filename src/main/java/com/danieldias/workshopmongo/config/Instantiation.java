package com.danieldias.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danieldias.workshopmongo.domain.Post;
import com.danieldias.workshopmongo.domain.User;
import com.danieldias.workshopmongo.repository.PostRepository;
import com.danieldias.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User sasha = new User(null, "sasha Grey", "sasha@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("05/04/2023"), "Bate volta", "Iremos Viajar para Chile", maria);
		Post post2 = new Post(null, sdf.parse("05/06/2023"), "Viagens Baratas", "Iremos Viajar para Mexico", sasha);
		
		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob, sasha));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		
	}

}
