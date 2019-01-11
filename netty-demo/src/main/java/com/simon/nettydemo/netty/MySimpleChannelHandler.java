package com.simon.nettydemo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/6 22:34
 **/
public class MySimpleChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        String url = fullHttpRequest.uri();
        System.err.println("channelRead0, uri=" + url);
    }
}
