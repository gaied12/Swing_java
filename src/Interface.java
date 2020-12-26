import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame implements ActionListener {
    private JLabel Title, LId, LNom, LPrenom, LClasse;
    private JTextField Id, Nom, Prenom, Classe;
    private JButton Ajt, Mdf, Sup;
    private List <Object> objectList ;

    public Interface(String title) throws HeadlessException {
        super(title);
    }

    public void f() {


        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        Title = new JLabel("Remlir les Cordonnées d'étudiant");
        p1.add(Title);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 2));

        LId = new JLabel("ID");
        Id = new JTextField();
        Id.setColumns(12);

        LNom = new JLabel("NOM");
        Nom = new JTextField();
        Nom.setColumns(12);

        LPrenom = new JLabel("Prenom");
        Prenom = new JTextField();
        Prenom.setColumns(12);

        LClasse = new JLabel("CLASSE");
        Classe = new JTextField();
        Classe.setColumns(12);
        p2.add(LId);
        p2.add(Id);
        p2.add(LNom);
        p2.add(Nom);
        p2.add(LPrenom);
        p2.add(Prenom);
        p2.add(LClasse);
        p2.add(Classe);
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER));


        Ajt = new JButton("Ajouter");
        Ajt.addActionListener(this);

        Mdf = new JButton("Modifier");
        Mdf.addActionListener(this);
        Sup = new JButton("Supprimer");
        p3.add(Ajt);
        p3.add(Mdf);
        p3.add(Sup);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(Mdf)) {
            new Update();
        }
        if (actionEvent.getSource().equals(Ajt)) {
        /*    if (Id.getText().isEmpty() || Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Classe.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "remplir le formulaire", "Erreur ",

                      JOptionPane.ERROR_MESSAGE);*/

try {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tuto_symfo","root","");
    Statement stmt=con.createStatement();
    int id=Integer.valueOf(this.Id.getText());
    String req = "INSERT INTO user (id, nom, prenom,classe)  VALUES (?,?,?,?)";
    PreparedStatement preparedStatement = con.prepareStatement(req);
    preparedStatement.setObject(1,id);

    preparedStatement.setString(2,Nom.getText());
    preparedStatement.setString(3,Prenom.getText());
    preparedStatement.setString(4,Classe.getText());
String z=preparedStatement.toString();






    Integer i=preparedStatement.executeUpdate();

    JOptionPane.showMessageDialog(this, "votre utlisateur a etait enregistre ", "Succes ",
            JOptionPane.DEFAULT_OPTION);

}
catch (Exception e){
   System.out.println(e.fillInStackTrace());
}


        }


            }


        }


