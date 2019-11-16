package dall;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.CommonResponse;
import net.sf.json.JSONObject;
import util.DatabaseUtil;

public class LoginHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		System.out.println("��֧��GET����");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		//***last
		System.out.println("���յ�������");
		BufferedReader read = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line  = null;
		while((line = read.readLine()) != null) {
			sb.append(line);//׷�ӵ������ĩβ
		}
		
		String req1 = sb.toString();
		System.out.println("���ܵ�������Ϊ��"+req1);
		
		//1 ��ȡ�ͻ��˵����� ������ָ�ΪJson��ʽ
		JSONObject  object =  JSONObject.fromObject(req1); 
		// requestCode��ʱ�ò���
		// ע���±��õ���2���ֶ�����requestCode��requestParamҪ�Ϳͻ���CommonRequest��װʱ�������һ��
		String requestCode = object.getString("requestCode");
		JSONObject requestParam = object.getJSONObject("requestParam");
		
		// �ڶ�������Jsonת��Ϊ������ݽṹ����ʹ�û���ֱ��ʹ�ã��˴�ֱ��ʹ�ã�������ҵ�������ɽ��
		// ƴ��SQL��ѯ���
		String sql = "select * from tb_account where account = "+"'"+requestParam.getString("name")+"'";
		System.out.println("sql���Ϊ��"+sql);
		/*String sql = String.format("SELECT * FROM %s WHERE account='%s'", 
		DBnames.Table_Account, 
		requestParam.getString("name"));
		System.out.println(sql);
		*/
		//***last
		CommonResponse res = new CommonResponse();
		try {
			ResultSet result = DatabaseUtil.query(sql); // ���ݿ��ѯ����
            //result.getRow();
			
			if (result.next()) {
				if (result.getString("password").equals(requestParam.getString("password"))) {
					res.setResult("1", "��½�ɹ�");
					res.getProperty().put("custId", result.getString("_id"));
				} else {
					res.setResult("2", "��¼ʧ�ܣ���¼�������");
				}
			} else {
				res.setResult("3", "�Ҳ������˺�,�õ�½�˺�δע��");
			}
		} catch (SQLException e) {
			res.setResult("300", "���ݿ��ѯ����");
			e.printStackTrace();
		}

		// ���������������װ��Json��ʽ׼�����ظ��ͻ��ˣ���ʵ�����紫��ʱ���Ǵ���json���ַ���
		// ������֮ǰ��String����һ����ֻ��Json�ṩ���ض����ַ���ƴ�Ӹ�ʽ
		// ��Ϊ�����JSon���õ�����ĵ�����JSon��������ǿ�󣬲�����Android�������Լ��ֶ�ת��ֱ�ӿ��Դ�Beanת��JSon��ʽ
		String resStr = JSONObject.fromObject(res).toString();
		
		System.out.println(resStr);
        //response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // ���Զ��ַ������м��ܲ�������Ӧ�ĵ��˿ͻ��˾���Ҫ����
		resp.getWriter().append(resStr).flush();
	}

	
}
