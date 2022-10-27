package com.example.java20.week6;

/**
 *  Test
 *  Unit Test
 *      Junit + Mockito
 *      functionA() {
 *          int x = B();
 *          x *= 2;
 *          return x;
 *      }
 *
 *      functionB() {
 *
 *      }
 *
 *      service + repository
 *  Integration Test
 *      MockMVC framework
 *          perform() -> /api -> expect()
 *      Postman
 *  Functional Test
 *  Smoke Test
 *
 *  Performance Test
 *      Jmeter
 *  Automation Test
 *      selenium
 *          page1 -> press button1 -> get response -> pass to page 2 -> press button2 -> get response -> check the result
 *  Behavior Test (BDD)
 *      @info("input should be,, output should be)
 *      public void test() {
 *
 *      }
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    waterfall
 *    Agile (every sprint, we finish few stories(new features) / tickets(bugs))
 *          Scrum (2 weeks sprint)
 *          1. daily stand up meeting(15min - 30min)
 *              what you done yesterday
 *              what to do today
 *          2. sprint planning meeting(at the beginning of the sprint)
 *              get stories / tickets from production backlog(TODO List + priority)
 *              fibonacci number: 1 2 3 5 8 13 (difficulties)
 *              hour based number: 1 point = 2 / 4 hours
 *          3. retrospective meeting(at the end of the sprint)
 *              review your sprint / recommendation of the sprint / technical skills sharing section / happy hours
 *          4. demo review meeting(every few sprints)
 *
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    when should we write unit testing ?  => TDD (test driven development)
 *      1. analyze requirements
 *      2. provide document / apis
 *      3. check out a new feature branch from development branch(git)
 *         design OOD (class, abstract class, interface)
 *         no implementations, leave TODO
 *      4. implement unit test + integration test(mockMVC)
 *      5. impl TODO
 *      6. run test cases -> debug
 *
 *
 *
 *    what to do after TDD ?
 *      7. push to feature branch
 *      8. pull request code review
 *      9. merge the code to development branch
 *      10. trigger jenkins pipeline
 *      git branch changes -> trigger -> build -> test -> report -> package(application + docker file => docker image) -> push image to docker hub(ECR)  -> deploy(ECS)
 *                                                       |
 *                                                     code coverage report
 *                                                     code security flaws report
 *                                                     ..
 *                                                      |
 *                                                   push reports to sonarqube
 *
 *      11. deploy code to QA environment (servers)
 *          (QA test code -> approve code -> ..)
 *
 *    when do we release the feature ?
 *      12. pull request merge to release branch
 *      13. trigger another pipeline -> deploy code to release / UAT environment(servers)
 *          also run smoke test
 *      14. QA will test all features / performance .. in release environment
 *
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    How do you do production support
 *      1. check out new hotfix branch from production / master branch
 *      2. read the ticket / bug report (feedback, issues, how to reproduce the issue..)
 *      3. search the log in "Splunk server"
 *      4. fix it in local + rewrite the test
 *          (for important bugs, schedule meetings with leader / manager)
 *      5. pull request code review to production / pull request code review to release branch
 *
 *   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   Send me your link through zoom chat before tomorrow 10am CDT
 */