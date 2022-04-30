package com.j6d1.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsException {
	private String message;
	private Integer code;
	private HttpStatus status;
	private LocalDateTime timeStamp;
	
}
