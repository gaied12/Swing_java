import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class State extends JFrame implements ActionListener {
    private  JTextField idVerf;
    private  JPanel panel1,panel2;
    private JButton  bVerf,bHome ;
    public  State(){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

         panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel label=new JLabel("Saisi l'id de votre Rendez-vous");
        idVerf=new JTextField();
        idVerf.setColumns(15);
        panel1.add(label);
        panel1.add(idVerf);
        panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        bVerf=new JButton("vérifier");
        bVerf.addActionListener(this);
        bHome=new JButton("Annuler");
        panel2.add(bVerf);
        panel2.add(bHome);
        this.add(panel1);
        this.add(panel2);







        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500,350);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(bVerf)){
            ConData conData=new ConData();
            String req="  SELECT `confirmed` FROM `rendz-vous` WHERE `id`=?";
            try {
                PreparedStatement preparedStatement =ConData.getCon().prepareStatement(req);

                preparedStatement.setObject(1,Integer.valueOf(idVerf.getText()));
                ResultSet resultSet=preparedStatement.executeQuery();
                boolean res=resultSet.next();
                if (res==false){
                    JOptionPane.showMessageDialog(this, "Vérifier L'id de votre rendez-vous", " ",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(res=true){
                   int State =resultSet.getInt(1);
                   if (State==1){
                       JOptionPane.showMessageDialog(this, "Votre Rndez-vous est confirmé", " ",
                               JOptionPane.INFORMATION_MESSAGE);



                   }
                    if (State==0){
                        JOptionPane.showMessageDialog(this, "Votre Rndez-vous est en cours de tratiement", " ",
                                JOptionPane.INFORMATION_MESSAGE);




                    }
                }




            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
