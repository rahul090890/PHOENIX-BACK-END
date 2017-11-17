package com.concretepage.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = HRException.class)  
    public String handleBaseException(HRException e){ 
		log.error("Exception is " + e.getMessage());
        return e.getMessage();  
    }  
  
    @ExceptionHandler(value = Exception.class)  
    public String handleException(Exception e){
    	log.error("Exception is " + e.getMessage());
    	return e.getMessage();
    	
    }  
	
		
	
	
	 /**@ExceptionHandler
	    @ResponseBody
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map handle(MethodArgumentNotValidException exception) {
		 
		 List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		 for(FieldError error : fieldErrors) {
			error.
		 }
	        return error(exception.getBindingResult().getFieldErrors()
	                .stream()
	                .map(FieldError::getDefaultMessage)
	                .collect(Collectors.toList()));
	                
	    }


	    @ExceptionHandler
	    @ResponseBody
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map handle(ConstraintViolationException exception) {
	        return error(exception.getConstraintViolations()
	                .stream()
	                .map(ConstraintViolation::getMessage)
	                .collect(Collectors.toList()));
	    }

	    private Map error(Object message) {
	        return Collections.singletonMap("error", message);
	    }
	    **/
}
