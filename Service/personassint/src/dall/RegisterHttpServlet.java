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
		// TODO 自动生成的方法存根
		System.out.println("暂不支持get方法");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("接受到注册请求");
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
		System.out.println("接收到的注册请求参数为："+req1);
		JSONObject object = JSONObject.fromObject(req1);
		System.out.println("***************");
		/*
		 * try { String requestCode = object.getString("requestCode");//这段code是客户端的请求指令
		 * } catch (Exception e1) { // TODO 自动生成的 catch 块 e1.printStackTrace(); }
		 */
		JSONObject requestParam = object.getJSONObject("requestParam");
		/*
		//select 1 from tablename where col = 'col' limit 1;
		String sql = "select 1 from userinfo  where account = "+"'"+requestParam.getString("account")+"' limit 1";
		System.out.println("注册的查询语句为："+sql);
		*/
		String sql1 = "insert userinfo values('" + requestParam.getString("account") + "','"
				+ requestParam.getString("password") + "','" + requestParam.getString("username") + "')";
		System.out.println("注册的插入sql语句为：" + sql1);
		CommonResponse res = new CommonResponse();
		try {
			// ResultSet resutl = DatabaseUtil.query(sql);
			// if(resutl.next()) {
			int number = DatabaseUtil.update(sql1);
			if (number == 1) {
				System.out.println("注册信息插入数据库成功！");
				res.setResult("0", "注册成功");
				res.getProperty().put("account", "password");
			}else if(number==0) {
				res.setResult("4", "已存在该用户");
			}
			// }
		} catch (Exception e) {
			res.setResult("301", "数据库插入错误");
			e.printStackTrace();
		}

	}

}
