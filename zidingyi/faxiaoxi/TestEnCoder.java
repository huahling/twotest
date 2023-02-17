package com.encoder.zidingyi.faxiaoxi;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;  

/**
 * ����
 * @����  �����
 *
 * @ʱ�� 2017��6��14�� ����11:24:31
 */
public class TestEnCoder extends MessageToByteEncoder<Msg> {

    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getNameLength());
        out.writeBytes(msg.getName().getBytes());
        out.writeInt(msg.getAge());
        out.writeLong(msg.getContentLength());
        out.writeBytes(msg.getContent().getBytes());

    }

}