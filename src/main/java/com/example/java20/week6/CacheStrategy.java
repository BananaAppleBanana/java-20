package com.example.java20.week6;

/**
 *
 *  *    Cache :
 *  *      read through
 *  *      write through
 *  *      write back
 *  *      cache aside
 *  *    Redis : AOF + Database (snapshot)
 *  *            hash slots
 *  *            leader + followers
 *  *    Global transaction: 2PC / SAGA pattern(message queue)
 *  *
 *  *    message queue : Rabbit MQ, Kafka
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *
 *   local cache vs global cache ?
 *
 *              client
 *              |
 *          N1,  N2 ,  N3(local cache)
 *           \   |    /
 *         cache server(global cache)
 *              |
 *            database
 *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *    1. cache aside pattern
 *       read from cache
 *          if yes => return data
 *          if no  => read from database + save to cache
 *       write
 *          delete cache
 *          update database
 *
 * user1       remove 1 from cache                            update 1 in database
 *       -----------------------------------------------------------------------------------> time line
 * user2                                                                            select  ,  save to cache
 *       -----------------------------------------------------------------------------------> time line
 * user3                                                                            select 1
 *
 * solution1: distribution lock
 *      1. lock =>  server crash before unlock()
 *      2. lock + timeout 30s => version / optimistic lock
 * solution2: eventually consistency
 *      1. update key 1 value null,  timeout (5s)
 *      2. when read cache if value is null -> read from database
 *
 *    2. read through pattern
 *      read <- cache <- database
 *    3. write through pattern
 *      write -> cache -> database
 *    4. write back pattern
 *      write ->
 *      write -> cache -> database
 *      write ->
 *    5. write around pattern
 *      write -> database
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *     Redis
 *           AOF => File
 *           DB  => snapshot
 *
 *         hash slots
 *         leader          leader          leader
 *        |     \           /    \          /   \
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *    Global Transaction
 *    1. 2PC
 *
 *                    coordinator
 *                /                 \
 *         insert a1                insert a2
 *         DB1                      DB2
 *
 *       initial step: are you able to execute query ?
 *                     are you ready ?
 *                     save commands to log
 *       2nd    step : let's commit
 *   2. SAGA pattern

 *          inventory service  ->  message queue ->  payment service  ->
 *             -1 phone                             not able to charge $30
 *          commit to db                            |
 *                                                  | compensation transaction
 *             +1 phone     <-   message queue <-   |
 *            commit to db
 *
 *   3. outbox pattern + CDC change data capture pattern
 *
 *             client
 *              |
 *          order service  ->  message queue ->  inventory service  ->
 *             |
 *         order database
 *
 *       1. order insert data to db
 *          order service crash
 *       2. send messages to message queue
 *
 *       1. send messages to message queue
 *          order service crash
 *       2. order insert data to db
 *
 *
 *             client
 *              |
 *          order service
 *             |
 *         order database    -> CDC service / table changed capture service ->  message queue ->  inventory service  ->
 *
 *
 *       order service
 *       1.  insert order into order table
 *           insert message into outbox table
 *           commit transaction
 *       CDC service
 *       1.  get changes from outbox table
 *           send to message queue
 *           remove message from outbox table
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *   message queue
 *
 *   queue model
 *                                                         ->  consumer(server)[5]
 *       producer(server)   ->   message queue (server)    ->  consumer(server)[6]
 *                                                         ->  consumer(server)[4]
 *
 *                               [1][2][3][4][5][6]
 *
 *   topic model / publisher subscriber model
 *                                                         ->  consumer(server)[6]
 *       producer(server)   ->   message queue (server)    ->  consumer(server)[6]
 *                                                         ->  consumer(server)[6]
 *                               [1][2][3][4][5][6]
 *
 *
 *
 *  Kafka
 *
 *                                                              consumer group + consumer 1 - m partitions
 *
 *         producer        ->     message queue(broker)     ->  consumer group
 *                               Topic1                         consumer group1
 *                                  partition1[3][2][1]             consumer1 ,  p1[1], p2
 *                                  partition2[5][6][4]             consumer2 ,  p3
 *                                  partition3
 *                                                              consumer group2
 *                                                                  consumer1 ,  p1[1], p2, p3
 *                                                                  consumer2
 *
 *
 *     we have 3 partitions
 *          if we have 1 consumer,  it will pull messages from p1, p2, p3
 *          if we have 3 consumers,  one consumer will pull messages from only one partition
 *
 *
 *     why message queue?
 *
 *
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *     how to handle duplicate messages in message ?
 *
 *
 *
 *
 *   Tomorrow
 *   basic aws introduction + ci/cd pipeline
 */