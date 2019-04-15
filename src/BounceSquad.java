import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BounceSquad extends JLabel {

	private static final long serialVersionUID = 5935524L;

	private int pv = -1;
	private int ph = 1;

	private int speed;

	private List<Thread> horizontal = new ArrayList<Thread>();
	private List<Thread> vertical = new ArrayList<Thread>();
	private JPanel pai;

	public BounceSquad(int speed, JPanel pai) {
		super("");
		this.speed = speed;
		this.pai = pai;
		setBackground(new Color(255, 255, 255, 0));
		this.setIcon(new ImageIcon("dvd.png"));

	}

	public void startBounce() {
		horizontal.add(new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						if (getLocation().getX() <= 3) {
							ph = 1;
							randomizeColor();
						} else if (getLocation().getX() + getWidth() > pai.getWidth()) {
							ph = -1;
							randomizeColor();
						}
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setLocation((int) getLocation().getX() + (1 * ph), (int) getLocation().getY());
				}

			}
		});
		horizontal.get(horizontal.size() - 1).start();

		vertical.add(new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						if (getLocation().getY() < 3) {
							pv = 1;
							randomizeColor();
						} else if (getLocation().getY() + getHeight() > pai.getHeight()) {
							pv = -1;
							randomizeColor();
						}
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setLocation((int) getLocation().getX(), (int) getLocation().getY() + (1 * pv));
				}

			}
		});
		vertical.get(vertical.size() - 1).start();

	}

	public Rectangle getRectangle() {
		return (new Rectangle((int) getLocation().getX(), (int) getLocation().getY(), (int) getWidth(),
				(int) getHeight()));
	}

	public void randomizeColor() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("dvd.png"));
		} catch (IOException e) {
		}

		int imgW = img.getWidth();
		int imgH = img.getHeight();

		int arr[][] = new int[imgW][imgH];

		Color c;

		Random random = new Random();

		int R = random.nextInt(255);
		int G = random.nextInt(255);
		int B = random.nextInt(255);

		for (int i = 0; i < imgW; i++) {

			for (int j = 0; j < imgH; j++) {

				int a = (img.getRGB(i, j) >> 24) & 0xFF;

				if (a > 0) {
					c = new Color(R, G, B, a);
					img.setRGB(i, j, c.getRGB());
				}

			}
		}
		if (img != null) {
			this.setIcon(new ImageIcon(img));
		}
		
	}

}
