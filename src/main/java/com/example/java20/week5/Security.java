package com.example.java20.week5;


/**
 *  encode :  1MB -> > 1MB
 *      ASCII 0 - 127 standard characters
 *  encrypt : symmetric encryption :
 *            Asymmetric encryption : private key -> encrypt -> public key -> decrypt
 *                                    public key  -> encrypt -> private key -> private key
 *
 *  security of application / secure rest api / secure spring application
 *
 *  1. authentication
 *      a. username password
 *      b. token
 *      c. email / phone / code
 *      d. multi-factor
 *      401
 *  2. authorization (role)
 *      JWT = json web token
 *      header.payload.signature
 *      encode(header.payload.encrypt(header + payload))
 *  3. https (http + ssl / tls)
 *
 *                      CA (certificate authority)
 *                  certification : exp, domain, public key
 *
 *
 *         client             hi  ->               server
 *                        <- certification
 *         public key                             private key
 *  *                 -> public key[random string / value]
 *                      <- hash(value)
 *                    [generate symmetric key]
 *
 *                  -> symmetric key[user info]
 *                  <- symmetric key[response info]
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *
 *   filter -> filter -> filter ->  controller
 *   Spring security
 *
 *    1. authentication
 *       username password authentication filter
 *          doFilter
 *              get username password from request body
 *              use dao authentication provider -> get username password from user details service
 *              compare user info from request and user info from user details service
 *              if verified -> successful handler -> store user info into security context (thread local)
 *              if not      -> unsuccessful handler -> return customized message to user
 *
 *    2. authorization
 *       impl JWT Filter
 *          doFilter
 *              get jwt token from header
 *              verify jwt token
 *              if verified -> get user role / info -> store user info into security context (thread local)
 *              if not -> execute following filter
 *
 *     JWT filter -> authentication filter
 *
 *
 * Tomorrow :
 *      microservice introduction
 */
