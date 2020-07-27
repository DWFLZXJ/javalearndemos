package com.daiwf.javalearndemos.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOPlainEchoServer
{
    public void serve(int port) throws IOException {
        System.out.println("Listenning for connection on port " + port);
        final AsynchronousServerSocketChannel severChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        //将ServerSocket绑定到指定的端口里
        severChannel.bind(address);
        final CountDownLatch latch = new CountDownLatch(1);
        //开始接收新的客户端请求，一旦一个客户端请求被接收，CompletionHandler就会被调用
        severChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>()
        {

            @Override public void completed(AsynchronousSocketChannel channel, Object attachment) {
                //一旦完成处理，再次接收新的客户端请求
                severChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(100);
                //在channel里植入一个读操作EchoCompleteionHandler,一旦buffer有数据写入，EchoCompleteionHandler
                channel.read(buffer, buffer, new EchoCompletionHandler(channel));
            }

            @Override public void failed(Throwable exc, Object attachment) {
                try {
                    severChannel.close();
                }
                catch (IOException e) {
                    //ignore on close
                }
                finally {
                    latch.countDown();
                }

            }
        });
    }

    private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>
    {
        private final AsynchronousSocketChannel channel;

        EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            //在channel里植入一个读操作CompletionHandler,一旦channel有数据写入
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>()
            {
                @Override public void completed(Integer result, ByteBuffer attachment) {
                    if (buffer.hasRemaining()) {
                        //如果buffer里还有内容，则再次出发写入操作将buffer里的内容写入channel
                        channel.write(buffer, buffer, this);
                    }
                    else {
                        buffer.compact();
                        //如果channel里还有内容需要读入到buffer，则再次触发写入操作将channnel里的内容读入buffer
                        channel.read(buffer, buffer, EchoCompletionHandler.this);
                    }
                }

                @Override public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    }
                    catch (IOException e) {

                    }
                }
            });
        }

        @Override public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }

}
