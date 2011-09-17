package com.adreamzone.common.server.connection;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class NettyClientChannelPipelineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		// Chain to decode/encode a message
		return Channels.pipeline(
				new NettyClientChannelEncoder(),
				new NettyClientChannelDecoder(),
				new NettyClientChannelServerHandler()
			);
	}
}
