import com.toedter.calendar.JCalendar;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Filter extends JFrame  implements ActionListener, ListSelectionListener {
    DefaultListModel  <Rendez_vous> dlm ;
    JList lR ;
    private JLabel lId,dId,lN,dN,lP,dP,lH,dH,lPh,dPh ;

    private JCalendar dRen ;
    private JTextField Dtxt ;
    private  Button cD,oD,aD,bR,cR,aR ;
    private  JWindow wDate ;



    public Filter(){
        init();

    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel p=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Dtxt=new JTextField();
        Dtxt.setColumns(10);
        cD=new Button("check");
        bR=new Button("chercher");
        bR.addActionListener(this);

        cD.addActionListener(this);
        JPanel p1=new JPanel(new GridLayout(5,2));
 lId=new JLabel();
 dId=new JLabel() ;
 lN=new JLabel();
 dN=new JLabel();
 lP=new JLabel();
 dP=new JLabel();
 lH=new JLabel();
 dH=new JLabel();
 lPh=new JLabel();
 dPh=new JLabel();




        p.add(Dtxt);
        p.add(cD);
        p.add(bR);
        p1.add(lId);
        p1.add(dId);
        p1.add(lN);
        p1.add(dN);
        p1.add(lP);
        p1.add(dP);
        p1.add(lH);
        p1.add(dH);
        p1.add(lPh);
        p1.add(dPh);
JPanel p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
cR=new Button("Confirmer ");
cR.addActionListener(this);
aR=new Button("Annuler");
aR.addActionListener(this);
p3.add(cR);
p3.add(aR);





        this.add(p);
        this.add(p1);
        this.add(p3);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 350);




    }
    private void aff() throws SQLException {
        JWindow jWindow=new JWindow(this);
        dlm=new DefaultListModel();
        lR=new JList(dlm);
        ConData conData=new ConData();
        String req= " SELECT `id`, `nom`, `prenom`, `time`,  `phoneNumber` FROM `rendz-vous` WHERE `date`=? AND `confirmed`=?";
        PreparedStatement preparedStatement=ConData.getCon().prepareStatement(req);
        preparedStatement.setObject(1,Dtxt.getText());
        preparedStatement.setObject(2,false);
        ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()) {
                Rendez_vous rendez_vous = new Rendez_vous();
                rendez_vous.setId(resultSet.getInt(1));
                rendez_vous.setNom(resultSet.getString(2));
                rendez_vous.setPrenom(resultSet.getString(3));
                rendez_vous.setHeure(resultSet.getNString(4));
                rendez_vous.setTel(resultSet.getInt(5));
                dlm.addElement(rendez_vous);
                jWindow.add(lR);

                lR.addListSelectionListener(this);
                jWindow.add(new JScrollPane(lR));
                jWindow.setSize(200, 300);
                jWindow.pack();
                jWindow.setVisible(true);
            }
        }





    private  void win(){
        wDate=new JWindow();
        BoxLayout bx=new BoxLayout(wDate.getContentPane(),BoxLayout.Y_AXIS);
        wDate.setLayout(bx);

        dRen=new JCalendar();

        oD=new Button("add");
        oD.addActionListener(this);

        aD=new Button("clear");
        aD.addActionListener(this);


        JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(oD);
        p2.add(aD);
        wDate.add(dRen);
        wDate.add(p2);
        wDate.setSize(200,200);
        wDate.setLocationRelativeTo(null);
        wDate.pack();
        wDate.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(cD)){
            win();


        }
        if (actionEvent.getSource().equals(oD)){

            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String date = fmt.format(dRen.getDate());
            Dtxt.setText(date);
            wDate.dispose();




        }
        if (actionEvent.getSource().equals(aD)) {
            wDate.dispose();
            Dtxt.setText(null);





        }
        if (actionEvent.getSource().equals(bR)) {
            try {
                aff();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (actionEvent.getSource().equals(cR)) {

            ConData conData = new ConData();
            String req = "UPDATE `rendz-vous` SET `confirmed`=? WHERE `id`=?";
            try {
                PreparedStatement preparedStatement = ConData.getCon().prepareStatement(req);
                preparedStatement.setObject(1, true);
                preparedStatement.setObject(2, Integer.valueOf(dId.getText()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Le rendez-vous a été accepté", "Succés",
                        JOptionPane.ERROR_MESSAGE);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            if (actionEvent.getSource().equals(aR)) {
                ConData conData=new ConData();
                String req="UPDATE `rendz-vous` SET `confirmed`=? WHERE `id`=?";
                try {
                    PreparedStatement preparedStatement=ConData.getCon().prepareStatement(req);
                    preparedStatement.setObject(1,false);
                    preparedStatement.setObject(2,Integer.valueOf(dId.getText()));
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Le rendez-vous a été Annulé" ,"succès",
                            JOptionPane.ERROR_MESSAGE);



                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }





    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
       Rendez_vous rendez_vous=dlm.get(lR.getSelectedIndex());
        lId.setText("Id rendez-vous :");
        dId.setText(String.valueOf(rendez_vous.getId()));
    lPh.setText("Numero de Télephone :");
     dPh.setText(String.valueOf(rendez_vous.getTel()));
     lP.setText("Prénom :");
      dP.setText(rendez_vous.getPrenom());
        lN.setText("Nom :");
   dN.setText(rendez_vous.getNom());
   lH.setText("heure :");
     dH.setText(rendez_vous.getHeure());

    }
}
