//package bao.xy.redis;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import redis.clients.jedis.Jedis;
//
///**
// * @Description:
// * @CreateTime: 2020-09-28-19-14
// */
//public class RedisTest {
//
//    public static void main(String[] args) {
//        Jedis j = new Jedis("118.31.43.132", 18264);
//        j.auth("997371");
//
////        j.set("aaa", "www");
//
//        JSONObject json = new JSONObject();
////        json.put("bbb", "asdf");
////        String s = json.toJSONString();
////        j.set("json1", s);
////        System.out.println(j.get("aaa"));
//
////        String jsonStr = j.get("json1");
////        JSONObject json1 = (JSONObject) JSONObject.parse(jsonStr);
////        System.out.println(json1);
////        System.out.println(json1.get("bbb"));
//
////        j.append("aaa","ccc");
//
////        System.out.println(j.strlen("json1"));
//
////        j.rpush("rList","r1", "r2", "r3");
////        j.lpush("lList", "l1", "l2", "l3");
////        System.out.println(j.lrange("lList", 0, -1));
////        System.out.println(j.lrange("rList", 0, -1));
//
//        // set
//        // 添加数据
////        j.sadd("set", "123", "345");
////        j.sadd("set", "789", "456");
//        // 删除数据
////        j.srem("set", "123");
//
////        System.out.println(j.smembers("set"));
//
//
//        // zset 通过score排序
//        j.zadd("zset", 1, "s1");
//        j.zadd("zset", 5, "s5");
//        j.zadd("zset", 3, "s3");
//        j.zadd("zset", 4, "s4");
//        j.zadd("zset", 2, "s2");
//        System.out.println(j.zrange("zset", 0, -1));
//
//
//        // hash
//        // set
////        j.hset("hash", "aa", "aaa");
////        j.hset("hash", "bb", "bbb");
////        j.hset("hash", "cc", "ccc");
//        // get
////        System.out.println(j.hget("hash", "aa"));
////
////        // del
////        j.hdel("hash", "bb");
//
//
//
//        j.close();
//
//
//    }
//}
