
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Ping extends JFrame implements KeyListener {
    JTextField cmd = new JTextField();
    JLabel cmd_label = new JLabel("fernando-maleiane@fernando-Latitude-E6430:~$ ");
    JLabel cmd_result = new JLabel("");

    Font cmd_font = new Font("Sans Serif", Font.PLAIN, 16);
    Dimension cmd_line_dim = new Dimension(450, 40);

    JList<String> ping_result;
    public DefaultListModel<String> pList = new DefaultListModel<>();

    JScrollPane scroll;

    InetAddress inet;

    public Ping(){

        // this.setPreferredSize(new Dimension(900, 600));
        this.setSize(900, 600);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cmd_label.setForeground(Color.green);
        cmd_label.setFont(cmd_font);
        // cmd_label.setPreferredSize(new Dimension(150, cmd.getHeight()));

        cmd.setForeground(Color.white);
        cmd.setBackground(this.getContentPane().getBackground());
        cmd.setPreferredSize(cmd_line_dim);
        cmd.setFont(cmd_font);
        cmd.setCaretColor(Color.WHITE);
        cmd.addKeyListener(this);

        this.ping_result = new JList<>(pList);
        ping_result.setPreferredSize(new Dimension(this.getContentPane().getWidth() - 50, 200));
        ping_result.setBackground(this.getContentPane().getBackground());
        ping_result.setForeground(Color.white);
        ping_result.setFont(cmd_font);
        
        scroll = new JScrollPane(ping_result);
        scroll.setPreferredSize(new Dimension(800, 500));
        scroll.setBackground(Color.BLACK);
        scroll.setForeground(Color.white);

        this.getContentPane().add(cmd_label);
        this.getContentPane().add(cmd);
        // this.getContentPane().add(ping_result);
        this.getContentPane().add(scroll);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key char" + e.getKeyCode());

        if(e.getKeyCode() == 10){
            String[] split_cmd;

            split_cmd = cmd.getText().split(" ");
            if(split_cmd[0].equals("ping")){

                try {
                    inet = InetAddress.getByName(split_cmd[1]);
                    
                    pList.addElement("PING " + inet.getHostAddress() + " (" + inet.getHostName() + ") 56(84) bytes of data.");

                    if(inet.isReachable(1000)){
                        System.out.println(inet.getHostName());

                        pList.addElement(inet.getHostName());
                        for(int i = 0; i < 4; i++){
                            pList.addElement("64 bytes from " + inet.getHostAddress() + ": icmp_seq=1 ttl=64 time=0.047 ms");
                        }

                        pList.addElement("---" + inet.getHostAddress() + " ping statistics ---");
                        pList.addElement("4 packets transmitted, 4 received, 0% packet loss, time 2030ms\n" + "rtt min/avg/max/mdev = 0.047/0.058/0.064/0.007 ms");

                        this.getContentPane().revalidate();
                        this.getContentPane().repaint();

                    }
                    else {
                        pList.addElement("");
                        pList.addElement("---" + inet.getHostAddress() + " ping statistics ---");
                        pList.addElement("4 packets transmitted, 0 received, 100% packet loss, time 3074ms");

                        this.getContentPane().revalidate();
                        this.getContentPane().repaint();
                    }
                    cmd.setText("");
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(cmd.getText().trim().equals("clear")){
                cmd.setText("");
                pList.removeAllElements();
            }
            else if(cmd.getText().equals("exit")){
                System.exit(0);
            }
            else {
                pList.addElement(split_cmd[0] + ": command not found");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
}
