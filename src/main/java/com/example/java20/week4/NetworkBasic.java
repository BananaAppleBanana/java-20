package com.example.java20.week4;

/**
 *    1. what are OSI layers , explain each layer
 *    2. what happened after clicking url
 *
 *     *     *     *     *     *     *     *     *     *     *     *     *     *
 *    IP 0.0.0.0 ~ 255.255.255.255
 *    [public ip + port] <------->  [public ip + port]
 *    connection [source ip + port][destination ip + port]
 *    *     *   *   *   *   *   *   *   *
 *    http://ip/xx
 *    http://www.name.com/xx   <->  DNS server
 *
 *    open browser , click url
 *      1. www.name.com <--> fetch public ips from DNS server
 *      2. cache ips with TTL
 *      3. build connection
 *         private ip + port <->  NAT(public ip pool)  <->   server / website
 *                                [public ip + port]        [public ip + port]
 *
 *         A    Application         => http: get , ..
 *                                      /student/{id}
 *                                      request body / info
 *                                      [http body]
 *         P    Presentation        => SSL / TLS
 *         S    Session             => socket / session
 *         T    Transport           => TCP / UDP
 *                                      [TCP Header: port, seq]
 *         N    Network             => ip
 *                                      ip packet [ip header: ip address][TCP Header: port, seq]
 *         D    Data Link           => ethernet, mac
 *         P    Physical            => cable
 *
 *
 *         build connection
 *         client  -> ip packet [ip header: ip address][TCP Header: flag, port, seq] -> server      Hi, want to build connection
 *         client  <-           ACK number                                           <- server      OK, I receive your request
 *         client  ->                                                                -> server      OK, Thanks / I receive your info
 *                              [server assign connection to one thread]
 *
 *
 *         transfer user data -> request
 *         client -> ip packet [ip header: ip address][TCP Header: flag, port, seq][http body] -> server
 *         ...
 *
 *                              [thread processing user data]
 *
 *          server send result back to client -> response
 *          client <- ip packet [ip header: ip address][TCP Header: flag, port, seq][data] <- server
 *          ...
 *
 *          disconnect
 *          client                -> close  ->                      server
 *                                  <- one sec
 *                                  <- done
 *                                  -> ok ->
 *
 *       *       *       *       *       *       *       *       *       *       *       *       *       *       *
 *     client -> request -> Tomcat server (session: key value)
 *                          Thread pool -> get one thread -> servlet -> process your request
 *           <- response
 *              S_id / session_id
 *           -> request  -> Tomcat
 *
 *     Tomcat[war[your code]]
 *     jar[Tomcat + code]
 *      *      *      *      *      *      *      *      *      *      *      *      *      *      *      *      *
 *      horizontal scaling
 *                    client
 *                      |
 *                 loadbalancer (public ip)
 *
 *            /         |           \           \
 *      node1         node2         node3       node4(vertical scaling: upgrade hardware)
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *     Tomorrow:
 *          1. Http
 *          2. Restful api
 *          3. SpringMVC
 *
 */