package web;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
public class CheckcodeServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("��ʼִ��CheckcodeServlet");
		//һ.��ͼ
		//step1,�����ڴ�ӳ�����(����)
		BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		//step2,��û���
		Graphics g = image.getGraphics();
		//step3,����������ɫ
		g.setColor(new Color(255,255,255));
		//step4,���ñ�����ɫ
		g.fillRect(0, 0, 80, 30);
		//step5,�����ǰ����ɫ
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//step6,��ͼ
		//��������(����,���,��С)
		g.setFont(new Font(null,Font.ITALIC,24));
		String number = getNumber(2);
		System.out.println(number);
		//����֤��(number)�󶩵�session������
		HttpSession session=request.getSession();
		session.setAttribute("number", number);
		g.drawString(number,2,26);
		//step7,��һЩ������
		for(int i=0;i<6;i++){
			g.drawLine(r.nextInt(80),r.nextInt(30),r.nextInt(80),r.nextInt(30));
		}
		//��.��ͼƬѹ�������͸������
		response.setContentType("image/jpeg");
		OutputStream ops=response.getOutputStream();
		//write����:�ὫԭʼͼƬ(image)����ָ�����㷨("jpeg")ѹ����Ȼ�������
		javax.imageio.ImageIO.write(image,"jpeg",ops);
		System.out.println("����ִ��CheckcodeServlet");
		ops.close();
	}
	private String getNumber(int size) {
		//String str="ABCDEFGHKJKLMNOPQRSTUVWXYZ0123456789";
		String str="0123456789";
		String number="";
		Random r=new Random();
		for(int i=0;i<size;i++){
			number+=str.charAt(r.nextInt(str.length()));
		}
		System.out.print(number);
		return number;
	}
}
