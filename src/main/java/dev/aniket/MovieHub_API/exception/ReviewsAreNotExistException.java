package dev.aniket.MovieHub_API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewsAreNotExistException extends RuntimeException {
    public ReviewsAreNotExistException(String message) {
        super(message);
    }
}
