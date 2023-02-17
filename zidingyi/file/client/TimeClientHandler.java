package com.encoder.zidingyi.file.client;

import com.encoder.zidingyi.file.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;
import java.io.RandomAccessFile;

public class TimeClientHandler extends ChannelInboundHandlerAdapter{
	
	private int counter;
	private byte[] req;
	private String filename;
	private String bathpath="C:\\Users\\user\\Desktop\\Socketio\\Springmvc.war";
	public TimeClientHandler()
	{
//		req = ("asfdf").getBytes();
		filename="Springmvc.war";
	}
	
	public void channelActive(ChannelHandlerContext ctx)
	{
		Msg msg=new Msg();
		msg.setNameLength(filename.getBytes().length);
		msg.setName(filename);
//		msg.setAge(20);
		File file=new File(bathpath);
		msg.setContentLength(file.length());
		ctx.writeAndFlush(msg);//一发送就会进入编码器
	}
	
	public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception
	{
		RandomAccessFile randomAccessFile=null;
		try {

		randomAccessFile=new RandomAccessFile(bathpath,"r");
		}catch (Exception e){
			e.printStackTrace();
		}

		try {

			ctx.writeAndFlush(new ChunkedFile(randomAccessFile)).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close();
				}
			});

		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}
}
