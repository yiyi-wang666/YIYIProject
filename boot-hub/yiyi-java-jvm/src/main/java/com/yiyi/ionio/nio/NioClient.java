package com.yiyi.ionio.nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7654;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public NioClient() {
        init();
    }

    public static void main(String[] args) {
        NioClient chatClient = new NioClient();
        // 开启线程不断读取数据
//        new Thread(() -> {
//            chatClient.readInfo();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        // 发送数据
//        Scanner input = new Scanner(System.in);
//        while (input.hasNextLine()) {
//            chatClient.sendInfo(input.nextLine());
//        }
        chatClient.sendInfo("你好服务器");
        chatClient.readInfo();
    }

    /**
     * 读取服务器发送的消息
     */
    public void readInfo() {
        try {
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String msg = new String(buffer.array()).trim();
                        System.out.println(msg);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向服务器发送消息
     */
    public void sendInfo(String info) {
        info = userName + ": " + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
