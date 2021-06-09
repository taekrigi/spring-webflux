package com.my.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.webflux.domain.Todo;
import com.my.webflux.service.TodoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	@GetMapping
	public Flux<Todo> getTodos() {
		return todoService.getTodos();
	}
	
	@GetMapping("zip")
	public Flux<Todo> getTodosByZip() {
		return todoService.getTodosByZip();
	}
	
	@GetMapping("iterable")
	public Flux<Todo> getTodosByIterable() {
		return todoService.getTodosByIterable();
	}
}
