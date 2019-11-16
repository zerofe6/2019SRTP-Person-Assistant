package dall;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.DatabaseObjectDescription;

import beans.CommonRequest;
import beans.CommonResponse;
import net.sf.json.JSONObject;
import util.DatabaseUtil;

public class RegisterHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		System.out.println("�ݲ�֧��get����");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		System.out.println("���ܵ�ע������");
		BufferedReader read = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = read.readLine())!= null) {
				sb.append(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		String req1  = sb.toString();
		System.out.println("���յ���ע���������Ϊ��"+req1);
		JSONObject object = JSONObject.fromObject(req1);
		System.out.println("***************");
		/*
		 * try { String requestCode = object.getString("requestCode");//���code�ǿͻ��˵�����ָ��
		 * } catch (Exception e1) { // TODO �Զ����ɵ� catch �� e1.printStackTrace(); }
		 */
		JSONObject requestParam = object.getJSONObject("requestParam");
		/*
		//select 1 from tablename where col = 'col' limit 1;
		String sql = "select 1 from userinfo  where account = "+"'"+requestParam.getString("account")+"' limit 1";
		System.out.println("ע��Ĳ�ѯ���Ϊ��"+sql);
		*/
		String sql1 = "insert userinfo values('" + requestParam.getString("account") + "','"
				+ requestParam.getString("password") + "','" + requestParam.getString("username") + "')";
		System.out.println("ע��Ĳ���sql���Ϊ��" + sql1);
		CommonResponse res = new CommonResponse();
		try {
			// ResultSet resutl = DatabaseUtil.query(sql);
			// if(resutl.next()) {
			int number = DatabaseUtil.update(sql1);
			if (number == 1) {
				System.out.println("ע����Ϣ�������ݿ�ɹ���");
				res.setResult("0", "ע��ɹ�");
				res.getProperty().put("account", "password");
			}else if(number==0) {
				res.setResult("4", "�Ѵ��ڸ��û�");
			}
			// }
		} catch (Exception e) {
			res.setResult("301", "���ݿ�������");
			e.printStackTrace();
		}

	}

}
