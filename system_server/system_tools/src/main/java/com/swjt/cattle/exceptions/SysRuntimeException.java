package com.swjt.cattle.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class SysRuntimeException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private HttpStatus httpStatus;
	private String[] parameters;

	public SysRuntimeException() {
		super();
	}

	public SysRuntimeException(String code) {
		this.code = code;
		this.message = SysValidateMessageConfig.getProperty(code);
	}

	public SysRuntimeException(String code, HttpStatus httpStatus) {
		this.code = code;
		this.message = SysValidateMessageConfig.getProperty(code);
		this.httpStatus = httpStatus;
	}

	public SysRuntimeException(Exception e, String code) {
		if (e instanceof SysRuntimeException) {
			this.code = ((SysRuntimeException) e).getCode();
		} else {
			this.code = code;
		}
		this.message = SysValidateMessageConfig.getProperty(code);
	}

	public SysRuntimeException(String code, String... parameters) {
		this.code = code;
		this.message = SysValidateMessageConfig.getProperty(code);
		this.parameters = parameters;
	}

	public SysRuntimeException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
}
