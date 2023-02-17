package com.encoder.zidingyi.file.server;

import com.encoder.zidingyi.file.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jboss.marshalling.ByteInputStream;
import org.jboss.marshalling.ByteOutputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	private int counter;
	private static String bathpath ="C:\\Users\\user\\Desktop\\";
	private BufferedOutputStream bufferedOutputStream;
	private FileOutputStream fileOutputStream;

	private String OK="ok";
	public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception
	{

		if(msg instanceof Msg){
			Msg body = (Msg)msg;
			System.out.println(body);

			File file=new File(bathpath+"FanSong.war");

			try {
				if (!file.exists()){
					file.createNewFile();
				}
			}catch (Exception e){
				e.printStackTrace();;
			}

			try {
				fileOutputStream=new FileOutputStream(file);
				bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
			}catch (Exception e){
				e.printStackTrace();;
			}

			ctx.writeAndFlush(OK.getBytes());
			return;
		}


		try {
			//传输
			byte [] bytes= (byte[]) msg;
			bufferedOutputStream.write(bytes,0,bytes.length);
			System.out.println("传输长度 "+bytes.length);
		}catch (Exception e){
			e.printStackTrace();;
		}

	}


	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx)throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)
	{
		cause.printStackTrace();
		System.out.println(cause.getMessage()+"----------");
		ctx.close();
	}
}
