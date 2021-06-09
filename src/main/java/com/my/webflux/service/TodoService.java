package com.my.webflux.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.my.webflux.domain.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {
	
	private final WebClient webClient = WebClient.create();

	public Flux<Todo> getTodos() {
		return Flux.range(1, 100)
			.flatMapSequential(id -> 
				webClient.get()
					.uri("https://jsonplaceholder.typicode.com/todos/" + id)
					.retrieve()
					.bodyToMono(Todo.class)
                    .doOnSuccess(data -> System.out.println(Thread.currentThread().getName())));
	}
	
	public Flux<Todo> getTodosByZip() {
		return Flux.zip(requestTodo(1), requestTodo(2), requestTodo(3))
			.flatMap(tuple -> 
				Flux.just(
					tuple.getT1(), 
					tuple.getT2(), 
					tuple.getT3())
				);
	}
	
	public Flux<Todo> getTodosByIterable() {
		return Flux.fromIterable(IntStream.range(1, 100).boxed().collect(Collectors.toList()))
				.flatMapSequential(id -> 
					webClient.get()
						.uri("https://jsonplaceholder.typicode.com/todos/" + id)
						.retrieve()
						.bodyToMono(Todo.class)
		                .doOnSuccess(data -> System.out.println(Thread.currentThread().getName())));
	}
	
	private Mono<Todo> requestTodo(Integer id) {
		return webClient.get()
			.uri("https://jsonplaceholder.typicode.com/todos/" + id)
			.retrieve()
			.bodyToMono(Todo.class)
	        .doOnSuccess(data -> System.out.println(Thread.currentThread().getName()));
	}
	
	
}
