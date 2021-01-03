import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class InterfaceAdmin extends JFrame implements ActionListener {
    JButton bF,bR;




    public InterfaceAdmin() {
     init();







    }
    private void init(){
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        JLabel Lab1=new JLabel();
        Lab1.setText("<html><h3><center>Interface Docteur </center></h3></html>");
        JPanel p=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel();
        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/ren.png");
        JLabel img = new JLabel();
        img.setIcon(icon);
        JPanel p1=new JPanel(new GridLayout(2,1));
        bF=new JButton("filtrer les rendez vous");
        bF.addActionListener(this);

        bR=new JButton("Liste Finale des rendez vous ");
        bR.addActionListener(this);
        p.add(Lab1);
        p1.add(bF);
        p1.add(bR);
        p3.add(img);
        this.add(p);
        this.add(p3);
        this.add(p1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 350);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(bF)){
            new Filter();
        }
        if (actionEvent.getSource().equals(bR)){
            new FinaleR();
        }

    }
}
