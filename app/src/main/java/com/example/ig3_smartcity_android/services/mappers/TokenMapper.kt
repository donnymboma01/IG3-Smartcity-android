package com.example.ig3_smartcity_android.services.mappers

import com.auth0.android.jwt.Claim
import com.auth0.android.jwt.JWT
import com.example.ig3_smartcity_android.model.Token
import com.example.ig3_smartcity_android.model.Value
import com.example.ig3_smartcity_android.repositories.dto.TokenDTO
import java.util.*

object TokenMapper {

    fun mapToToken(token: String) :Token{
        val parsedJWT = JWT(token);
        val allClaims : Map<String,Claim> = parsedJWT.getClaims()
        val value : Value? = allClaims.getValue("value").asObject<Value>(Value::class.java)
        val userId :Int = value!!.userId
        val username :String = value.username
        val exp : Date? = parsedJWT.expiresAt

        return Token(username,userId,exp,token)
    }
}