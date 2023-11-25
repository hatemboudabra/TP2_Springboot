package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.AUTHENTICATION_ENDPOINT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.auth.AuthenticationRequest;
import net.javaguides.springboot.springbootbackend.Request.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("AuthenticationApi ")
public interface AuthenticationApi {
    @PostMapping(AUTHENTICATION_ENDPOINT + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
