package cl.gendigital.gendeporte.users.core.exceptions;

import cl.gendigital.gendeporte.users.api.responses.ErrorResponse;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.EmailExist;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserExist;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.exceptions.user.service.MismachedValidationCode;
import cl.gendigital.gendeporte.users.core.exceptions.user_info.persistence.UserInfoNotExist;
import cl.gendigital.gendeporte.users.core.exceptions.user_info.service.NoValidatedUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotExist.class})
    public ResponseEntity<ErrorResponse> handleUserNotExist(UserNotExist userNotExist){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), userNotExist.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(UserExist.class)
    public ResponseEntity<ErrorResponse> handleUserNotExist(UserExist userExist){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),userExist.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailExist.class)
    public ResponseEntity<ErrorResponse> handleEmailExist(EmailExist emailExist){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),emailExist.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(MismachedValidationCode.class)
    public ResponseEntity<ErrorResponse> handleMismachedValidationCode(MismachedValidationCode mismachedValidationCode){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), mismachedValidationCode.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler({UserInfoNotExist.class})
    public ResponseEntity<ErrorResponse> handleUserInfoNotExist(UserNotExist userInfoNotExist){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), userInfoNotExist.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
    @ExceptionHandler(NoValidatedUser.class)
    public ResponseEntity<ErrorResponse> handleNoValidatedUser(NoValidatedUser noValidatedUser){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), noValidatedUser.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

}
