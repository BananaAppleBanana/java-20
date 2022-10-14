package com.example.java20.week4;


/**
 *  Restful api
 *      1. http
 *          method :
 *              get : get
 *              post : create
 *              patch + put : update
 *              delete : delete
 *          api / endpoint / url : http://www.xxx.com/..?key1=val1&key2=val2 (request param)
 *          status code :
 *              2xx : success
 *              3xx : redirect
 *              4xx : client-side error
 *              5xx : server-side error
 *          request body :
 *              {
 *                  "name": "xx",
 *                  "age" : 5
 *              }
 *          response body :
 *              {
 *                  "data": [
 *                      {
 *                          "name": "xx",
 *                          "age" : xx,
 *                          "address" : {
 *
 *                          }
 *                      },
 *                      {}
 *                   ]
 *              }
 *          header : meta data (key value pair)
 *              Content-Type : request body format
 *              Accept : response body format
 *              Authorization : jwt token
 *      2. endpoint : noun key word
 *          /student + get : select all students
 *          /student/{id} + get : get student by id
 *          /student + post : create new student
 *          /student/{id} + put / patch : update student by id
 *          /student/{id} + delete : delete student by id
 *      3. stateless
 *         get  /student?pageNum=2&pageCount=20
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  xml
 *  /student - StudentServlet
 *  /teacher - TeacherServlet
 *
 *  *  client -> request /student get -> Tomcat server (session: key value)
 *  *                                     Thread pool -> get one thread -> StudentServlet -> get / post function -> return data
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *  client -> request /student get -> Server
 *                                 -> DispatcherServlet(/*) -> handler mapping -> StudentController
 *                                          |
 *                                     view resolver
 *                                         |model + view
 *                                       JSP
 *
 *
 *  client -> request /student get -> Server
 *                                 -> DispatcherServlet(/*) -> handler mapping -> StudentController
 *                                          |
 *                                     HttpMessageConverter
 *                                         |Jackson
 *                                      Json
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *    client -> request -> server   -> fetch data -> server
 *
 *    RestTemplate
 *
 *    1. create configuration class in config package
 *      @Configure
 *      public class RestTemplateConfig {
 *          @Bean
 *          public RestTemplate restTemplate() {
 *              return new RestTemplate();
 *          }
 *      }
 *    2. inject rest template into service
 *      xx.getForObject(url, Class type)
 *    3. param
 *   //TODO
 *    1. first api
 *       get all employees and group them by age
 *    2. second api
 *       get employees whose age larger than 30
 */