package com.example.ig3_smartcity_android.services.mappers

import com.example.ig3_smartcity_android.model.JwtTokenPayload
import com.example.ig3_smartcity_android.repositories.dto.JwtTokenPayloadDTO

object TokenMapper {

    /*fun mapToToken(token: String) :Token{
        val parsedJWT = JWT(token);
        val allClaims : Map<String,Claim> = parsedJWT.getClaims()
        val value : Value? = allClaims.getValue("value").asObject<Value>(Value::class.java)
        return Token(token)
    }*/

    fun mapToJwtTokenPayload(JwtTokenPayloadDTO: JwtTokenPayloadDTO) :JwtTokenPayload{
        return JwtTokenPayload(JwtTokenPayloadDTO.status, JwtTokenPayloadDTO.id, JwtTokenPayloadDTO.firstname, JwtTokenPayloadDTO.lastname)
    }
}