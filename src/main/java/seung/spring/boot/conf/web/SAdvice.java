package seung.spring.boot.conf.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponseError;

@ControllerAdvice
@Slf4j
public class SAdvice {

	@ExceptionHandler({
		MethodArgumentNotValidException.class
	})
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, SRequest sRequest) {
		log.error("" + methodArgumentNotValidException);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		final long timestamp = new Date().getTime();
		List<SLinkedHashMap> errors = new ArrayList<>();
		List<String> message = new ArrayList<>();
		for(FieldError fieldError : fieldErrors) {
			errors.add(
					new SLinkedHashMap()
						.add("field", fieldError.getField())
						.add("message", fieldError.getDefaultMessage())
						.add("value", fieldError.getRejectedValue())
						.add("code", fieldError.getCode())
					);
			message.add(String.format("%s(%s)", fieldError.getField(), fieldError.getCode()));
		}
		return new ResponseEntity<>(
				SResponseError.builder()
					.status(httpStatus.value())
					.timestamp(timestamp)
					.error(httpStatus.getReasonPhrase())
					.message(
							String.format(
									"입력값이 올바르지 않습니다. %s"
									, Arrays.toString(message.toArray())
									)
							)
					.trace(bindingResult.toString())
					.errors(errors)
					.path("")
					.requestTime(sRequest.getRequest_time())
					.responseTime(timestamp)
					.build()
				, httpStatus
				);
	}
	
}
