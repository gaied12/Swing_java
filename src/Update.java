import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update extends JFrame implements ActionListener {
    private JLabel title ;

    public Update() throws HeadlessException {
        f();
    }

    private void f() {
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.setTitle("update");
        JPanel p1=new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        title=new JLabel("Modifier Etudiant ");
        p1.add(title);
        this.add(p1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
