package com.kowalski.controlacademyapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123456"));
		System.out.println(encoder.matches("123456", "$2a$10$QPdfktjH8CAyCzY3Jhq7F..x4NGMx6I41eByywrc1/ijM9O9wBtke"));
		
		//$2a$10$TZzTqBy9gCzw8gpdj3nIauxUl7riYlfpdSjU../vJZ1Yat6oo5QEu
		//$2a$10$VqAa1CwOaQXwg.irDd6IreFXdMiD51y0SJ1oedcXSOBcG9hhlpB7q
	}
}