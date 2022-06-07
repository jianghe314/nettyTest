package com.cidi.nettytest;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by CIDI zhengxuan on 2019/10/10
 * QQ:1309873105
 */
public class EchoServer {

    private  int port;

    public EchoServer(int port) {
        this.port=port;
    }

    public void start() throws Exception{
        //创建数据处理器
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        //创建EventLoopGroup组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        //创建服务器引导
        ServerBootstrap b = new ServerBootstrap();
        b.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)//指定所使用的NIO传输Channel
                .localAddress(new InetSocketAddress(port))//指定端口套接字地址
                //添加数据处理器到Channel的ChannelPipline中
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(echoServerHandler);
                    }
                });
        //异步绑定服务器调用sync()方法阻塞等待直到绑定完成
        ChannelFuture channelFuture = b.bind().sync();
        //获取Channel的CloseFuture并阻塞当前线程直到它完成
        channelFuture.channel().closeFuture().sync();
        //关闭EventLoopGroup释放所有资源
        eventLoopGroup.shutdownGracefully().sync();

    }
}
