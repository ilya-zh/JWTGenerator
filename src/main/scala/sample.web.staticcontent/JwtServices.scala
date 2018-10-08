package sample.web.staticcontent

import org.springframework.security.jwt.Jwt
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException

/**
 * Services for validating a JSon Web Token
 */
trait JwtServices {
  /**
   * @param clientId the client id to use
   * @param expiry the expiry time in ms for the Jwt to be able to be validated
   * @return an encrypted jtw string
   */
  def createJtw(clientId: String, expiry: Long): String

  /**
   * Consume a JSON Web Token.
   *
   * @param jwt The JSON Web Token.
   * @return The authentications associated with the jsonWebToken.
   * @throws org.springframework.security.oauth2.common.exceptions.InvalidGrantException
	 * If the jwt is invalid
   */
  @throws(classOf[InvalidGrantException])
  def verifyJwt(jwt: String): Jwt
}
