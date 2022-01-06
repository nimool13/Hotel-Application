package sample.model;

import sample.controller.UDPChatController;

import java.net.*;

public class UDPChatCommunicator implements Runnable {
    private final int DATAGRAM_LENGTH = 100;
    private final int PORT = 6789;
    private final String MULTICAST_ADDRESS = "228.28.28.28";
    private InetSocketAddress _group;
    private NetworkInterface _netIf;
    private UDPChatController _chat = null;
    private MulticastSocket _socket = null;


    public UDPChatCommunicator(UDPChatController chat) {
        _chat = chat;

        System.setProperty("java.net.preferIPv4Stack", "true"); //force java to use IPv4 so we do not get a problem when using IPv4 multicast address
    }


    public void sendChat(String sender, String message) throws Exception {

        try (DatagramSocket socket = new DatagramSocket()) {
            String toSend = sender + ": " + message;
            byte[] b = toSend.getBytes();

            DatagramPacket datagram = new DatagramPacket(b, b.length, InetAddress.getByName(MULTICAST_ADDRESS), PORT);

            socket.send(datagram);
            socket.disconnect();

        } catch (Exception e) {
            throw e;
        }
    }


    public void startListen() {
        new Thread(this).start();
    }


    private void listenForMessages() throws Exception {
        byte[] b = new byte[DATAGRAM_LENGTH];
        DatagramPacket datagram = new DatagramPacket(b, b.length);

        if(_socket == null) {
            _group = new InetSocketAddress(InetAddress.getByName(MULTICAST_ADDRESS), PORT);
            _netIf = NetworkInterface.getByName("bge0");
            _socket = new MulticastSocket(PORT);
        }

        _socket.joinGroup(_group, _netIf);

        while (true) {
            _socket.receive(datagram);
            String message = new String(datagram.getData());
            message = message.substring(0, datagram.getLength());
            _chat.receiveMessage(message);
            datagram.setLength(b.length);
        }

    }

    public void stopListen() throws Exception {
        _socket.leaveGroup(_group, _netIf);
    }

    @Override
    public void run() {
        try {
            this.listenForMessages();
        } catch (Exception e) {
            _chat.error(e);
        }
    }
}
