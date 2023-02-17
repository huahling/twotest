package com.encoder.zidingyi.file.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;


public class TimeServer {
	public void bind(int port) throws Exception
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)  //��ӹ����߳���
			 .channel(NioServerSocketChannel.class)   //���ùܵ�ģʽ
			 .option(ChannelOption.SO_BACKLOG, 1024)  //����BLOCK��С
			 .childHandler(new ChildChannelHandler());
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>
	{
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			//arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
			//ByteBuf delimiter = Unpooled.copiedBuffer("!@$".getBytes());
			//arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(2048,delimiter));

			//使用自定义编码器
			arg0.pipeline().addLast(new ByteArrayEncoder());//数组加密器
			arg0.pipeline().addLast(new TestDecoder());
//			arg0.pipeline().addLast(new StringDecoder());//string解码器
			arg0.pipeline().addLast(new TimeServerHandler());
		}
	}
	
	public static void main(String[] args)throws Exception {
		new TimeServer().bind(8080);
	}
}
