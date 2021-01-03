import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JButton bD,bP;
    public Home(){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        JLabel Titre = new JLabel("<html><h2><center>Bienvenue au cabinet Docteur </center></h2></html>");
        p1.add(Titre);
        JPanel p2 = new JPanel();
        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/medical (1).png");
        JLabel img = new JLabel();
        img.setIcon(icon);
        p2.add(img);
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER));
        bD=new JButton("Espace Docteur");
        bD.addActionListener(this);
        bP=new JButton("Espace Passion");
        bP.addActionListener(this);
        p3.add(bD);
        p3.add(bP);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400,500);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(bD)){
            new Login("");
        }
        if (actionEvent.getSource().equals(bP)){
            new InterfaceUser("");

        }



    }
}
