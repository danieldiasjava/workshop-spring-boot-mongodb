package com.danieldias.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieldias.workshopmongo.domain.Post;
import com.danieldias.workshopmongo.repository.PostRepository;
import com.danieldias.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll() {

		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
