package com.danieldias.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danieldias.workshopmongo.domain.Post;
import com.danieldias.workshopmongo.domain.User;
import com.danieldias.workshopmongo.dto.AuthorDTO;
import com.danieldias.workshopmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob, sasha));
		
		Post post1 = new Post(null, sdf.parse("05/04/2023"), "Bate volta", "Iremos Viajar para Chile", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("05/06/2023"), "Viagens Baratas", "Iremos Viajar para Mexico", new AuthorDTO(sasha));
	
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("13/11/2023"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite a viagem!", sdf.parse("14/11/2023"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/11/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPots().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
	}

}
