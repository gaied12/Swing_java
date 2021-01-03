import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceUser extends JFrame implements ActionListener {
    private JLabel Lab1;
    private JButton b1,b2,b3,b4,bVerf,bHome;
    private JLabel l,l1;
    private JPanel pAdd,pDel,pVer,pUpd,panel,panel1;
    private JTextField idVerf,txNom,txPrenom,txNum;
    private JCalendar dRen,dUpd ;
    private  JComboBox jComboBox ;
    

    public InterfaceUser(String titre){
        super(titre);
        init();

    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1=new JPanel();
        ImageIcon icon=new ImageIcon("/home/oussamagaied/Téléchargements/medical.png");
        Lab1=new JLabel(icon);
        Lab1.setText("<html><h3><center>Interface passion </center></h3></html>");
        p1.add(Lab1);
        JPanel p2=new JPanel(new GridLayout(4,1));
        b1=new JButton("Consulter l'etat d'un rendez-vous ");
        b1.addActionListener(this);
        b2=new JButton(" Modifier un rendez-vous");
        b3=new JButton("Prendre un rendez-vous");
        b4=new JButton("Annuler un redez-vous");
        b4.addActionListener(this);

         b3.addActionListener(this);
        b2.addActionListener(this);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        JPanel p3 =new JPanel();
        l=new JLabel();
        p3.add(l);


        this.add(p1);
        this.add(p2);
        this.add(p3);



        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500,350);





    }
    public void addRen(){
        JPanel panel=new JPanel(new GridLayout(5,2));
        JLabel label=new JLabel("Nom:");
        txNom=new JTextField();
        txNom.setColumns(15);
        JLabel label1=new JLabel("Prenom:");
        txPrenom=new JTextField();
        txPrenom.setColumns(15);
        JLabel label2=new JLabel("Numero de Telephone");
        txNum=new JTextField();
        txNum.setColumns(15);
        JLabel label3=new JLabel("choisir un date :");
        dRen=new JCalendar();
        JLabel label4=new JLabel("choisir une heure :");
        String [] renTime={"8:30","9:30","10:30","11:30","12:30","14:00","14:30","15:00","15:30","16:00"};
        jComboBox=new JComboBox(renTime);
        panel.add(label);
        panel.add(txNom);
        panel.add(label2);
        panel.add(txPrenom);
        panel.add(label3);
        panel.add(dRen);
        panel.add(label4);
        panel.add(jComboBox);
        pAdd=new JPanel();
        pAdd.add(panel);
        this.add(pAdd);
















    }
    public void delRen(){


    }
    public void verRen(){
     panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label=new JLabel("Saisi l'id de votre Rendez-vous");
        idVerf=new JTextField();
        idVerf.setColumns(15);
        panel.add(label);
        panel.add(idVerf);
         panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
       bVerf=new JButton("vérifier");
       bHome=new JButton("Annuler");
       panel1.add(bVerf);
       panel1.add(bHome);
       pVer=new JPanel(new GridLayout(4,2));
       pVer.add(panel);
       pVer.add(panel1);
       this.add(pVer);












    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(b1)){
            new State() ;

        }
        if (actionEvent.getSource().equals(b2)){
            new Update();

        }
        if (actionEvent.getSource().equals(b3)){
            new Add();



        }

    }
}
