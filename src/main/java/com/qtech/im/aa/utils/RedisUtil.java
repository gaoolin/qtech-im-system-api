package com.qtech.im.aa.utils;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/04 09:20:06
 * desc   :
 */


public class RedisUtil {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean tryLock(String key, String value, long expireTime) {
        return false;
    }

    public static boolean releaseLock(String key, String value) {
        return false;
    }

}
