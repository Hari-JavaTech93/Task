package com.example.ems_backendapi.exception;/*
 *
 * @author Lawshiga
 *
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ControllerException extends RuntimeException{

    private static final long serialVersionUID = 8709360106183142224L;
    private String errorCode;
    private String errorMessage;
    
    ControllerException(){
    	
    }
    
    public ControllerException(String errorCode, String errorMessage) {
		//super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
    
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
