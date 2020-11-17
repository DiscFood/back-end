package br.com.discfood.controllers

import br.com.discfood.auth.JwtTokenUtil
import br.com.discfood.models.jwt.JwtRequest
import br.com.discfood.services.JwtUserDetailsService
import br.com.studae.models.jwt.JwtResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
@CrossOrigin
@RequestMapping
@Tag(name = "Authentication", description = "Authentication Controller")
class AuthenticationController(
        val authenticationManager: AuthenticationManager,
        val jwtTokenUtil: JwtTokenUtil,
        val userDetailsService: JwtUserDetailsService
) {

    @PostMapping(value = ["/authenticate"])
    @Operation(summary = "Login as a company")
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*>? {
        val userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.email)
        authenticate(authenticationRequest.email, authenticationRequest.password)
        val token = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok<Any>(JwtResponse(token))
    }

    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }

}
