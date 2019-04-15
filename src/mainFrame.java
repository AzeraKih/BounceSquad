import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class mainFrame extends JFrame {

	private JPanel contentPane;
	private List<BounceSquad> quads = new ArrayList<BounceSquad>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainFrame() {
		setMinimumSize(new Dimension(250, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(1, 1));
		panel.setFocusable(false);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.MAGENTA));
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);

		JButton btnAdd = new JButton("Adicionar Bounce Squad");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAdd)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BounceSquad quadrado = new BounceSquad(7, panel);
				quadrado.setBounds(50, 15, 100, 62);
				panel.add(quadrado);
				quadrado.setBackground(Color.BLACK);
				quadrado.startBounce();

				quads.add(quadrado);

			}
		});

	}
}
