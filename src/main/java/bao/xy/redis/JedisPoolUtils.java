//package bao.xy.redis;
//
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @Description: jedisPool 单例写法
// * @CreateTime: 2020-09-28-20-24
// */
//public class JedisPoolUtils {
//
//    // 被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
//    private static volatile JedisPool jedisPool = null;
//
//    // 不让外部new
//    private JedisPoolUtils() {}
//
//    public static JedisPool getJedisPool() {
//        if (jedisPool == null) {
//            synchronized (JedisPoolUtils.class) {
//                if (jedisPool == null) {
//                    JedisPoolConfig jpc = new JedisPoolConfig();
//                    // 控制一个pool可分配多少个jedis实例
//                    jpc.setMaxTotal(1000);
//                    // 最少保存多少条
//                    jpc.setMaxIdle(30);
//                    // 超时
//                    jpc.setMaxWaitMillis(10 * 1000);
//                    // ping
//                    jpc.setTestOnBorrow(true);
//                    jedisPool = new JedisPool(jpc, "118.31.43.132", 18264, 10 * 1000, "997371");
//                }
//            }
//        }
//        return jedisPool;
//    }
//
//}
