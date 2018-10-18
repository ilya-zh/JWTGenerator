package sample.web.staticcontent

import org.springframework.security.jwt.{Jwt, JwtHelper}
import org.springframework.security.jwt.crypto.sign.{MacSigner, SignatureVerifier, Signer}
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException

object DefaultJwtServices {
  def apply(verifier: SignatureVerifier, signer: Signer): JwtServices = new DefaultJwtServices(verifier, signer)

  def apply(signerKey: String): JwtServices = {
    val signer = new MacSigner(signerKey)
    apply(signer, signer)
  }
}

class DefaultJwtServices(verifier: SignatureVerifier, signer: Signer) extends JwtServices {

  def createJtw (clientId: String, expiry: Long): String = {
    val jsonClaims = "{\"iss\":" + clientId + ",\r\n" + " \"exp\":" + expiry + "}"
    val token = JwtHelper.encode(jsonClaims, signer)
    token.getEncoded
  }

  def verifyJwt (jwt: String): Jwt = {
    try {
      JwtHelper.decodeAndVerify(jwt, verifier)
    }
    catch {
      case e: RuntimeException =>
        throw new InvalidGrantException(e.getMessage)
    }
  }
}
