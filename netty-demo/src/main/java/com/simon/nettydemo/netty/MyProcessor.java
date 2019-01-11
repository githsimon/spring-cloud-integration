package com.simon.nettydemo.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/7 16:17
 **/
public class MyProcessor {
    private final static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
