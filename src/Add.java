import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add  extends JFrame implements ActionListener {
    private  JComboBox jComboBox ;
    private JTextField txNom,txPrenom,txNum,txDate;
    private JCalendar dRen ;
    private Button bC,bA,bD,j1,j;
    private JLabel Titre,lNom,lPrenom,lTel,lDate,lH;
    private  JWindow jWindow ;


    public Add(){
        in();
    }
    private  void in(){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        Titre = new JLabel("<html><h3><center> Remplir le formulaire Ci-dessous  afin de  prendre un Rendez-vous </center></h3></html>\"");

        p1.add(Titre);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2));
       lNom = new JLabel("Nom :");
       txNom = new JTextField();
        txNom.setColumns(5);

        lPrenom = new JLabel("Prenom");
        txPrenom = new JTextField();
        txPrenom.setColumns(5);
        JPanel pD = new JPanel(new GridLayout(1,2));


      lDate=new JLabel("choisir un date :");
      txDate=new JTextField();
        txDate.setColumns(10);
        JPanel panelD=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bD=new Button("check");
        bD.setSize(5,5);
        bD.addActionListener(this);

        JPanel pH = new JPanel(new GridLayout(2,2));
        lTel = new JLabel("Numero de Tél");

        txNum=new JTextField();
        txNum.setColumns(5);

        lH=new JLabel("choisir une heure :");
        String [] renTime={"8:30","9:30","10:30","11:30","12:30","14:00","14:30","15:00","15:30","16:00"};
        jComboBox=new JComboBox(renTime);

        p2.add(lNom);
        p2.add(txNom);
        p2.add(lPrenom);
        p2.add(txPrenom);
        pD.add(lDate);
        pD.add(txDate);
        panelD.add(bD);
        pH.add(lH);
        pH.add(jComboBox);
        pH.add(lTel);
        pH.add(txNum);


        JPanel p3 = new JPanel();
        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/doctor.png");
        JLabel img = new JLabel();
        img.setIcon(icon);
        p3.add(img);
        JPanel p4 = new JPanel();
        bC= new Button("Ok");

        bC.addActionListener(this);
        bA = new Button("Anuller");
        bA.addActionListener(this);

        p4.add(bC);
        p4.add(bC);
        JPanel p5=new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon icon1 = new ImageIcon("/home/oussamagaied/Téléchargements/date.png");

        JLabel label=new JLabel(icon1) ;

        String pattern = "MM/dd/yyyy ";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);


        label.setText(todayAsString);
        p5.add(label);

        this.add(p5);
        this.add(p1);
        this.add(p3);
        this.add(p2);
        this.add(pH);
        this.add(pD);
        this.add(panelD);
        this.add(p4);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 350);


    }

    private  void win(){
        jWindow=new JWindow();
        BoxLayout bx=new BoxLayout(jWindow.getContentPane(),BoxLayout.Y_AXIS);
        jWindow.setLayout(bx);

        dRen=new JCalendar();

        j=new Button("add");
        j.addActionListener(this);

        j1=new Button("clear");
        j1.addActionListener(this);


        JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(j);
        p2.add(j1);
        jWindow.add(dRen);
        jWindow.add(p2);
        jWindow.setSize(200,200);
        jWindow.setLocationRelativeTo(null);
        jWindow.pack();
        jWindow.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(bD)) {
            win();

        }
        if (actionEvent.getSource().equals(j)) {
            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String date = fmt.format(dRen.getDate());
            txDate.setText(date);
            this.jWindow.dispose();


        }
        if (actionEvent.getSource().equals(j1)) {


            txDate.setText(null);
            this.jWindow.dispose();


        }
      /*  if (actionEvent.getSource().equals(bC)) {
            System.out.println(jComboBox.getItemAt(jComboBox.getSelectedIndex()));


        }*/
        if (actionEvent.getSource().equals(bC)) {

            String req="INSERT INTO `rendz-vous`(`id`, `nom`, `prenom`, `time`, `date`, `phoneNumber`, `confirmed`)VALUES (?,?,?,?,?,?,?)";
            ConData con=new ConData();
            try {
                PreparedStatement preparedStatement=ConData.getCon().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setObject(2,txNom.getText());
                preparedStatement.setObject(3,txPrenom.getText());
                preparedStatement.setObject(4,jComboBox.getItemAt(jComboBox.getSelectedIndex()));
                preparedStatement.setObject(5,txDate.getText());
                preparedStatement.setObject(6,Integer.valueOf(txNum.getText()));
                preparedStatement.setObject(7,false);
          int r= preparedStatement.executeUpdate();
            if (r==1){
                ResultSet resultSet=preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    JOptionPane.showMessageDialog(this, "L'Id de votre Rendez-vous est" + id + "", "Ok ",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "error", "Erroe ",
                        JOptionPane.ERROR_MESSAGE);


            }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
