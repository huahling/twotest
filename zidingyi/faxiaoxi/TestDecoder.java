package com.encoder.zidingyi.faxiaoxi;

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

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


//        不管发什么类型都会进  可能多种类型   需要判断类型
//        if (ctx instanceof Msg) {


            int mark = 0;
            mark = in.readableBytes();//标志

            if (mark < 4) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            int nameLength = in.readInt();


            mark = in.readableBytes();
            if (mark < nameLength) {
                in.resetReaderIndex();//返回到标志
                return;
            }

//        mark=in.readableBytes();
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

            long contentLenght = in.readLong();

            mark = in.readableBytes();
            if (mark < contentLenght) {
                in.resetReaderIndex();//返回到标志
                return;
            }
            byte[] contentbyte = new byte[Integer.valueOf(String.valueOf(contentLenght))];

            in.readBytes(contentbyte);

            System.out.println(nameLength);
            System.out.println(new String(namebyte));
            System.out.println(age);
            System.out.println(contentLenght);
            System.out.println(new String(contentbyte));


            Msg msg = new Msg();
            msg.setNameLength(nameLength);
            msg.setName(new String(namebyte));
            msg.setAge(age);
            msg.setContentLength(contentLenght);
            msg.setContent(new String(contentbyte));
            out.add(msg);//添加后自动输出

            ctx.writeAndFlush(msg);//发送  指定类型才会进入

//        byte[] req=("").getBytes();
//        ByteBuf byteBuf= Unpooled.buffer(req.length);
//        byteBuf.writeBytes(req);
//        ctx.writeAndFlush(byteBuf);

//        }
    }
}