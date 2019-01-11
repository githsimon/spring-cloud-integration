package com.simon.nettydemo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/4 11:49
 **/
public class NettyService {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup1 = new NioEventLoopGroup();
        EventLoopGroup eventLoopGroup2 = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(eventLoopGroup1, eventLoopGroup2)
                    .channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                socketChannel.pipeline().addLast(new HttpResponseEncoder());
//                socketChannel.pipeline().addLast(new HttpResponseDecoder());
                    //业务逻辑处理
                    //解码和编码HTTP请求
                    socketChannel.pipeline().addLast(new HttpServerCodec());
                    socketChannel.pipeline().addLast(new HttpObjectAggregator(64 * 1024));
                    socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                    socketChannel.pipeline().addLast(new MySimpleChannelHandler());

                    socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/im"));
                    socketChannel.pipeline().addLast(new MyWebSocketHandler());

                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);


            ChannelFuture future = serverBootstrap.bind(5051).sync();
            System.err.println("Netty is start...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup1.shutdownGracefully();
            eventLoopGroup2.shutdownGracefully();
        }
    }
}
