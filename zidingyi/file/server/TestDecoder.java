package com.encoder.zidingyi.file.server;

import com.encoder.zidingyi.file.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * netty-��Ϣͷ����Ϣ�������
 * @����  �����
 *
 * @ʱ�� 2017��6��14�� ����11:22:40
 */
//没有泛型，所有的消息会经过这里
public class TestDecoder extends ByteToMessageDecoder {

    public static  boolean pang=true;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


//        不管发什么类型都会进  可能多种类型   需要判断类型
//        if (ctx instanceof Msg) {


        if(pang) {
            try {


//            int mark = 0;
            in.markReaderIndex();
            int mark = in.readableBytes();//标志
            if (mark < 4) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            int nameLength = in.readInt();


            mark = in.readableBytes();//标志
            if (mark < 0) {
                in.resetReaderIndex();//返回到标志
                return;
            }

            mark = in.readableBytes();
            if (mark < nameLength) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            byte[] namebyte = new byte[nameLength];
            in.readBytes(namebyte);


            mark = in.readableBytes();
            if (mark < 4) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            int age = in.readInt();


            mark = in.readableBytes();
            if (mark < 8) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            long contentLength = in.readLong();


            System.out.println(nameLength);
            System.out.println(new String(namebyte));
            System.out.println("age :"+age);
            System.out.println("contentLength :"+contentLength);


            //接到客户端发来的 文件名和内容
            Msg msg = new Msg();
            msg.setNameLength(nameLength);
            msg.setName(new String(namebyte));
//            msg.setAge(age);
            msg.setContentLength(contentLength);

            pang =false;
            out.add(msg);//添加后自动输出

//            ctx.writeAndFlush(msg);//发送  指定类型才会进入
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("抛异常");
            }

        }else{
            byte [] bytes=new byte[in.readableBytes()];
            in.readBytes(bytes);

            out.add(bytes);
        }

    }
}