package com.example.java20.week6;

/**
 *  1. how to scale up database
 *      RDBMS
 *      a. vertical scaling + horizontal scaling
 *      DB1(vertical scaling)       DB2       DB3       DB4
 *      b. write + read database
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *      Single Leader
 *        write
 *          /
 *      DB1(leader)      DB2(followers)       DB3(followers)       DB4(followers)
 *          \               /                   /                   /
 *               read
 *
 *         1. write to leader +  followers
 *         2. only write to leader + leader send data to followers in background threads
 *  u1   begin tx          update row1 (acquire row1's exclusive lock)   commit(release locks)
 *     ------------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------>  timeline
 *  u2          begin tx                    update row2 (wait for lock) -> get lock / timeout exception
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *      Multi Leader
 *
 *   update stu tom - jerry          update stu tom - alex
 *      DB1(leader1)    <-->        DB2(leader2)
 *     /        \                   /       \
 *   Follower1  2               Follower3   4
 *
 *
 *              LWW = last write win
 *     clock vector =
 *          N1: (v1,  1) -> (v1, 1)(v1, 2)
 *          N2: (v1,  1) -> (v1, 1)(v2, 2)
 *
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    how to find database
 *    solution1  :  id%3
 *    DB1 (id = 0, 3, 6, 9..)
 *    DB2 (id = 1, 4, 7, 10..)
 *    DB3 (id = 2, 5, 8, 11..)
 *    DB4
 *
 *    solution2  : consistent hashing
 *              N1(0)
 *
 *     N6               N2(100k)
 *
 *                      NX(150k)
 *     N5               N3(200k)
 *
 *             N4(300k)
 *
 *    solution3  : hash slots(400)
 *                      id -> find slot
 *          N1 (0 ~ 200)            N2 (201 ~ 400)
 *
 *    solution4  : config server
 *
 *          gateway         -           config server
 *        |     \       \
 *       N1     N2      N3
 *   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    LeaderLess (all leaders)
 *
 *    Cassandra
 *      LSM tree -> log structured merge index tree
 *
 *                      immutable file3
 *                       val = 2, time = 2
 *                  /                   \
 *          immutable file1         immutable file2
 *          val = 1, time = 1       val = 2, time = 2
 *
 *     Node
 *           ->    mem table (cache)   ->  threshold  ->  flush into SST (sorted string table) (disk)
 *          |
 *       commit log
 *
 *
 *        read -> blooming filter -> sst -> merge result
 *
 *
 *              [][][x][][][][][][][]    hash algo1
 *              [][][][][][][x][][][]    hash algo2
 *              [x][][][][][][][][][]    hash algo3
 *
 *
 *     gossip
 *              N1(0)
 *
 *     N6               N2(100k)
 *
 *                      NX(150k)
 *     N5               N3(200k)
 *
 *             N4(300k)
 *
 *      replica factor = 3
 *      read consistency level = 1 ~ 3
 *
 *                 RC = 2 (read repair)
 *                  |
 *                  N6
 *              /           \
 *             N2           NX          N3
 *      write consistency level
 *
 *                       WC = 2
 *                        |
 *                        N6
 *              /           \           \
 *             N2           NX          N3
 *
 *      Read consistency + Write consistency > Replica factor
 *
 *   Cassandra : AP -> BASE (eventually consistency)
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *    MongoDB : CP
 *
 *         gateway (Mongos)        -           config server(Mongos)
 *        |          \         \
 *       Sharding1   Sharding2  Sharding3
 *       Primary N
 *       Secondary N
 *       1 - 10k
 *
 *
 *       Leader   -   candidate   -  follower (Raft)
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *    Tomorrow
 *
 *    Cache :
 *      read through
 *      write through
 *      write back
 *      cache aside
 *    Redis : AOF + Database (snapshot)
 *            hash slots
 *            leader + followers
 *    Global transaction: 2PC / SAGA pattern(message queue)
 *
 *    message queue : Rabbit MQ, Kafka
 */
