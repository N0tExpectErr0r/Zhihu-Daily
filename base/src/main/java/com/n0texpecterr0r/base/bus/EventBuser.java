package com.n0texpecterr0r.base.bus;

import org.greenrobot.eventbus.EventBus;

/**
 * 为了与EventBus解耦，在此封装一层
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public class EventBuser {
    public static EventBus getInstance() {
        // EventBus本身就是单例，调用EventBus的getDefault方法就好
        return EventBus.getDefault();
    }

    public static void register(Object object) {
        if (!getInstance().isRegistered(object)) {
            getInstance().register(object);
        }
    }

    public static void unregister(Object object) {
        if (getInstance().isRegistered(object)) {
            getInstance().unregister(object);
        }
    }
}
