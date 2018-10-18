package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.web.staticcontent.JwtApp;

/**
 * JWT Generator Controller
 *
 * @author Ilya Zhuravliov, Date: 08/10/2018
 */

@Controller
public class JWTGeneratorController
{
  @GetMapping(path = "/generateJWT", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String greetingSubmit( @RequestParam(value = "signerKey") final String signerKey,
                                @RequestParam(value = "clientID") final String clientID )
  {
    return JwtApp.generateJwt( signerKey, clientID, 0);
  }
}
