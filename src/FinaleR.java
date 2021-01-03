import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FinaleR extends JFrame implements ActionListener {
    private  JTextField Dtxt;
    private Button cD,bR,oD,aD;
    private JWindow wDate,wRf;
    private JCalendar dRen ;
    private  JTable tRf;


    public FinaleR(){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel p=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Dtxt=new JTextField();
        Dtxt.setColumns(10);
        cD=new Button("check");
        bR=new Button("Rendez-vous finale");
        bR.addActionListener(this);

        cD.addActionListener(this);
        p.add(Dtxt);
        p.add(cD);
        p.add(bR);
        this.add(p);
        this.setSize(600,400);
        this.setVisible(true);

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
private void aff() throws SQLException {
        wRf=new JWindow(this);
    String column[]={"ID","NOM","PRENOM","HEURE","TELEPHONE"};
    ConData conData=new ConData();
    String req= " SELECT `id`, `nom`, `prenom`, `time`,  `phoneNumber` FROM `rendz-vous` WHERE `date`=? AND `confirmed`=?";
    PreparedStatement preparedStatement=ConData.getCon().prepareStatement(req);
    preparedStatement.setObject(1,Dtxt.getText());
    preparedStatement.setObject(2,true);
    ResultSet resultSet=preparedStatement.executeQuery();
    resultSet.last();
    int row =resultSet.getRow();
    resultSet.beforeFirst();

    if (row==0){
        ImageIcon icon = new ImageIcon("/home/oussamagaied/Téléchargements/error-in-card.png");
        JOptionPane.showMessageDialog(this, "Aucun rendez-vous dans le date de  "+Dtxt.getText(), "Erreur ",
                JOptionPane.ERROR_MESSAGE, icon);
    }
    Object dta[][]=new Object[row][column.length];
    int i=0 ;
    int j=0 ;


    while (resultSet.next()) {
        dta[i][j] = (Object) resultSet.getInt(1);
        j++;

        dta[i][j] = (Object) resultSet.getString(2);
        j++;
        dta[i][j] = (Object) resultSet.getString(3);
        j++;

        dta[i][j] = (Object) resultSet.getString(4);
        j++;

        dta[i][j] = (Object) resultSet.getInt(5);
        i++;
        tRf = new JTable(dta, column);
        wRf = new JWindow(this);
        wRf.add(tRf);
        wRf.add(new JScrollPane(tRf));
        wRf.setSize(400, 600);
        wRf.setVisible(true);



    }




}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(oD)) {

            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String date = fmt.format(dRen.getDate());
            Dtxt.setText(date);
            wDate.dispose();


        }
        if (actionEvent.getSource().equals(cD)){
            win();


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


    }
}
