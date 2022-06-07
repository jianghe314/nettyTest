package com.cidi.nettytest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

/**
 * Created by CIDI zhengxuan on 2019/10/14
 * QQ:1309873105
 */
public class NettyUDPSocket {
    private  static final String tag = "NettyUDP";
    private static Map<Integer,Bootstrap> bootstrapMap = new ConcurrentHashMap<>();
    private static EventLoopGroup group;
    private static ConnectState mconnectState;
    private static final int OK = 11;

    private static Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == OK){
                if(mconnectState != null){
                    mconnectState.connectSuccess((byte[]) msg.obj,8889);
                }
            }
        }
    };

    public static void initNettyUdpSocket(){
        if(null == group){
            group = new NioEventLoopGroup();
        }
    }

    public static synchronized void connect(String ipAddress,final int port){
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                if(null == group){
                    throw new NullPointerException("NettyUdpSocket is not init !");
                }
                try {
                    if(null == bootstrapMap.get(port)){
                        Bootstrap bootstrap = new Bootstrap();
                        bootstrap.group(group)
                                .channel(NioDatagramChannel.class)
                                .option(ChannelOption.SO_BROADCAST,true)
                                .handler(new UDPMsgHandler());
                        Channel channel = bootstrap.bind(port).sync().channel();
                        channel.closeFuture().sync();
                        group.shutdownGracefully();
                        bootstrapMap.put(port,bootstrap);
                    }else {
                        bootstrapMap.get(port).group(group)
                                .channel(NioDatagramChannel.class)
                                .option(ChannelOption.SO_BROADCAST,true)
                                .handler(new UDPMsgHandler());
                        Channel channel = bootstrapMap.get(port).bind(port).sync().channel();
                        channel.closeFuture().sync();
                        group.shutdownGracefully();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private static class UDPMsgHandler extends SimpleChannelInboundHandler<DatagramPacket>{

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
            ByteBuf buf = datagramPacket.content();
            int port = datagramPacket.recipient().getPort();
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            handler.sendMessage(handler.obtainMessage(OK,req));
        }
    }

    public static void onClose(){
        group.shutdownGracefully();
    }

    public  interface ConnectState{
        void connectSuccess(byte[] result,int port);
    }

    public static void ConnectStateListener(ConnectState connectState){
        mconnectState = connectState;
    }

}
