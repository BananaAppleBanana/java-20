package com.example.java20.week5;


/**
 *  CAP ->
 *  monolithic
 *                  LB
 *           /      \        \
 *         node1    node2   node3   .....
 *  why microservice?
 *      1. availability
 *      2. performance + scalability
 *      3. loose coupling
 *      4. diff team works on diff services
 *      5. message queue + can handle more requests (Asynchronous)
 *          Client
 *             |
 *          service1(upload document)  -> message queue[][][][][][]  ->  service2(process document / analyze document)
 *            |
 *          database
 *      6. deployment
 *
 *  How to create microservice ?
 *      1. monolithic service1 -> 2 services / 3 services
 *          service boundary  => DDD domain driven development
 *      2. discovery service, eureka
 *      3. centralized configuration service
 *      4. centralized security service
 *          gateway -> security
 *      5. api gateway service
 *      6. centralized log service (Splunk)
 *      7. co-relation id / global unique id
 *         client -> A -> B -> C -> D
 *             A    generate co-relation id
 *             |    header:
 *             B    get co-relation id from header
 *             |    header:
 *             C
 *         how to generate co-relation id
 *          1. 64 bits -> 42 bits timestamp  +  6 digits machine id   + 6 digits.. +    10 serial number
 *             2^10
 *          2. UUID
 *          3. DB1 -> 0 +3, +3,  DB2 -> 1 +3, +3,   DB3 -> 2 + 3, +3, +3
 *      8. message queue
 *      9. database cluster
 *          single leader database cluster
 *          multi  leaders database cluster
 *          leaderless cluster
 *     10. cache cluster -> redis cluster
 *     11. linux / docker container + ECS / kubernetes
 *         CI / CD
 *     12. monitoring -> log / api performance
 *     ...
 *
 *  how to improve api performance?
 *      1. scaling -> vertical scaling / horizontal scaling
 *      2. use log -> locate issues
 *      3. database ->
 *              cache
 *              execution plan -> hint / index
 *      4. jvm / spring / java ->
 *              Time complexity
 *              Space complexity
 *              multi-threading
 *              GC -> STW
 *              heap dump (file)
 *              real time heap usage
 *
 *      5. loadbalancer ->
 *              sticky session
 *              load balancing rules
 *
 *   Good restful api ?
 *      1. SOLID principle
 *      2. restful api standard
 *      3. OOD ? interface abstract class
 *      4. log ?
 *      5. exception handling ?
 *      6. business logic -> correct
 *      7. test cases
 *      8. time complexity / space complexity
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Git branch strategy
 *      master branch ---------------v1.0.0 (production)
 *                      \  /          /
 *      hotfix branch    --          /
 *                         \        /
 *      release branch -------v1.0.0---------v1.1.0---------v1.2.0   (UAT)
 *
 *                                                            /
 *      development branch ----------------------------------       (QA)
 *                                  \         / pull request code review
 *      feature branch               --------   (local laptop)
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *      Next Monday
 *   1. CAP
 *   2. Leader / Follower
 *   3. MongoDB cluster (why CP)
 *   4. Cassandra cluster (why AP) LSM, sequential IO
 *          consistent hashing + leader less cluster
 *          read consistency level / write consistency level / replica factor
 *          read repair
 *          single node:  mem-table  +  commit log -> SST(immutable sorted string table)
 *   5. Redis cache cluster
 */