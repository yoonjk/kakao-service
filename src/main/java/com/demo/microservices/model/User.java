package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class User {
	private String name;
	private String email;
	private String picture;
}
