import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Login extends JFrame implements ActionListener {
    private JLabel Titre, LuserName, LPassword;
    private JTextField Tusername;
    private JPasswordField Pass;
    private JButton Ok, Anuller;

    public Login(String titre) {
        super(titre);
        Init();

    }

    private void Init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        Titre = new JLabel("<html><h2><center>veuillez authentifier </center></h2></html>");
        p1.add(Titre);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2));
        LuserName = new JLabel("Nom d'utlisateur");
        Tusername = new JTextField();
        Tusername.setColumns(10);
        LPassword = new JLabel("Mot de passe");
        Pass = new JPasswordField();
        Pass.setColumns(10);
        p2.add(LuserName);
        p2.add(Tusername);
        p2.add(LPassword);
        p2.add(Pass);
        JPanel p3 = new JPanel();
        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/doctor.png");
        JLabel img = new JLabel();
        img.setIcon(icon);
        p3.add(img);
        JPanel p4 = new JPanel();
        Ok = new JButton("Ok");

        Ok.addActionListener(this);
        Anuller = new JButton("Anuller");
        Anuller.addActionListener(this);

        p4.add(Ok);
        p4.add(Anuller);
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

        this.add(p4);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 350);



    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(Anuller)) {
            Tusername.setText(null);
            Pass.setText(null);


        }

        if (actionEvent.getSource().equals(Ok)) {
            if (Tusername.getText().isEmpty() || Pass.getPassword().length == 0) {
                ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/error-in-card.png");
                JOptionPane.showMessageDialog(this, "remplir le formulaire", "Erreur ",
                        JOptionPane.ERROR_MESSAGE, icon);

            } else {
                try {

                    ConData conData=new ConData();
                    String myPass=String.valueOf(Pass.getPassword());


                    String req = "SELECT * FROM user WHERE password=? and username=?";
                    PreparedStatement preparedStatement = ConData.getCon().prepareStatement(req);
                    preparedStatement.setString(1,myPass);
                    preparedStatement.setString(2,Tusername.getText());
                    String r=preparedStatement.toString();

                    ResultSet resultSet=preparedStatement.executeQuery();
                    boolean t=resultSet.next();
                    if (t==false){
                        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/user-error (3).png");
                        JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou Mot de passe Incorrect ", "Erreur ",
                                JOptionPane.ERROR_MESSAGE, icon);
                    }
                    else {

                        new InterfaceAdmin();
                        this.setVisible(false);


                    }




                } catch (Exception exception) {
                   exception.getMessage() ;
                }


            }

        }
    }
}
