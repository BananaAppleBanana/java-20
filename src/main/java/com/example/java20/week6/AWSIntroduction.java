package com.example.java20.week6;

/**
 *  what is AWS / cloud service
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   1. EC2 (ENI + Elastic IP) + ASG
 *   2. Security group (inbound + outbound)
 *   3. ALB
 *                                      |
 *                          Application Load Balancer
 *             /student
 *             |
 *       target[ASG[EC2[security group],  EC2[security group],  EC2[security group]]]
 *   4. VPC
 *
 *
 *                                              private subnet (ip range1, CIDR block)
 *                                          /
 *      -> internet gateway    route table  --  private subnet (ip range2, CIDR block)
 *                                          \
 *                                              public subnet [EC2..]
 *   5. EBS
 *   6. RDS(oracle / mysql / postgresql)
 *   7. Dynamo DB
 *   8. Aurora (RDBMS)
 *   9. S3 (object storage : log / image / video)
 *   10. glacier
 *   11. EFS
 *   12. Elastic cache
 *   13. Api gateway
 *          X-forward.. : co-relation
 *          Rate limiter:
 *              a.token bucket
 *              b.queue  [x1][x2][x3][x4][x5]
 *              c.sliding window
 *                       2            [3           1]
 *                      time1       time2       time3
 *              cache
 *
 *   14. cloud front (CDN)
 *   15. SNS: pub , sub
 *       SQS: standard queue
 *            FIFO queue
 *            (visibility timeout)
 *       dead letter queue
 *   16. ?MQ service?
 *      message queue service
 *   17. redshift
 *   18. cloudwatch -> monitor EC2 status / log
 *   19. cloudtrail -> account activity log
 *   20. IAM -> role / user account
 *
 *
 *   CI/CD server(jenkins)
 *
 *   git branch changes -> trigger -> build -> test -> report -> package(application + docker file => docker image) -> push image to docker hub(ECR)  -> deploy(ECS)
 *                                                       |
 *                                                     code coverage report
 *                                                     code security flaws report
 *                                                     ..
 *                                                      |
 *                                                   push reports to sonarqube
 *
 *
 *      ECS : docker management service
 *
 *  1. understand what is docker
 *  2. ECS(task definition, service, EC2(agent) / fargate)
 *
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   developer / solution architecture associate
 *
 *
 *
 *  Tomorrow
 *      testing
 *      agile scrum
 *      daily work
 *      how to do production support
 *
 */