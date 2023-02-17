package com.encoder.zidingyi.faxiaoxi;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	private int counter;
	public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception
	{

		if(msg instanceof  Msg){
			Msg body = (Msg)msg;
			System.out.println(body);
		}

		System.out.println("---------------------------");
		/*System.out.println("======================"+body);
		System.out.println("The teim server reveive order :" +body + "; the counter is : "+ ++counter);
		
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : " BAD ORDER";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);*/
		
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx)throws Exception
	{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)
	{
		System.out.println(cause.getMessage()+"----------");
		ctx.close();
	}
}
