package com.yis.util.netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeServerHandler extends ChannelHandlerAdapter {

	//ChannelHandlerContext通道处理上下文
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws InterruptedException { // (1)
		while (true) {
			ByteBuf buffer = ctx.alloc().buffer(20); //为ByteBuf分配四个字节
			SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			buffer.writeBytes(sf.format(new Date()).getBytes());
			ctx.writeAndFlush(buffer); // (3)
			TimeUnit.SECONDS.sleep(1);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
}
