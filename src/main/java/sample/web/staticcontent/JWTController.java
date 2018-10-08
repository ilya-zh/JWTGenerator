package sample.web.staticcontent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class description here
 *
 * @author Ilya Zhuravliov, Date: 08/10/2018
 */

@Controller
public class JWTController
{
  @GetMapping(path = "/showJWT", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String greetingSubmit( @RequestParam(value = "signerKey") final String signerKey,
                                @RequestParam(value = "clientID") final String clientID )
  {
    return JwtApp.generateJwt(signerKey, clientID, 0);
//    return "dd";
  }
}
