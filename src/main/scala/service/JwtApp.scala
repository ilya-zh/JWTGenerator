package sample.web.staticcontent

import java.util.UUID

import org.springframework.security.jwt.crypto.sign.MacSigner

import scala.util.Random

/**
 * A utility that helps generate jwt tokens
 */
object JwtApp extends App {

  def generateJwt(signerKey: String, clientId: String, expiry: Long) = {
    val signer = new MacSigner(signerKey)
    val jwtServices = new DefaultJwtServices(signer, signer)
    jwtServices.createJtw(clientId, expiry)
  }

  def printJwt(signerKey: String, clientId: String, expiry: Long) = {
    println("Assertion for signerKey: " + signerKey + " clientId: " + clientId + " expiry: " + expiry)
    println("jwt: " + generateJwt(signerKey, clientId, expiry))
  }

  //printJwt("iqwtyw3478tywiourtywiurytw3847tyieuoty", "73823659374523840583647592645382", 0)

  def generateKeys() = {
    Random.setSeed(System.currentTimeMillis())

    def randomId() = String.valueOf(Math.abs(Random.nextLong()))

    val signerKey = String.valueOf(UUID.randomUUID())
    val apiId = randomId()
    val adminId = randomId()
    val apiJwt = generateJwt(signerKey, apiId, 0)
    val adminJwt = generateJwt(signerKey, adminId, 0)
    val sb = new StringBuilder()
    sb.append("apiId: ")
    sb.append(apiId)
    sb.append('\n')
    sb.append("adminId: ")
    sb.append(adminId)
    sb.append('\n')
    sb.append("signerKey: ")
    sb.append(signerKey)
    sb.append('\n')
    sb.append("client jwt: ")
    sb.append(apiJwt)
    sb.append('\n')
    sb.append("admin jwt: ")
    sb.append(adminJwt)
    sb.toString()
  }

  println(generateKeys())
}
