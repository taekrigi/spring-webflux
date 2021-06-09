package com.my.webflux.domain;

import lombok.Getter;

@Getter
public class Todo {
	private Long userId;
	private Long id;
	private String title;
	private boolean completed;
}
