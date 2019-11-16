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
		// TODO 自动生成的方法存根
		System.out.println("不支持GET方法");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		//***last
		System.out.println("接收到了请求");
		BufferedReader read = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line  = null;
		while((line = read.readLine()) != null) {
			sb.append(line);//追加到对象的末尾
		}
		
		String req1 = sb.toString();
		System.out.println("接受到的请求为："+req1);
		
		//1 获取客户端的请求 并将其恢复为Json格式
		JSONObject  object =  JSONObject.fromObject(req1); 
		// requestCode暂时用不上
		// 注意下边用到的2个字段名称requestCode、requestParam要和客户端CommonRequest封装时候的名字一致
		String requestCode = object.getString("requestCode");
		JSONObject requestParam = object.getJSONObject("requestParam");
		
		// 第二步：将Json转化为别的数据结构方便使用或者直接使用（此处直接使用），进行业务处理，生成结果
		// 拼接SQL查询语句
		String sql = "select * from tb_account where account = "+"'"+requestParam.getString("name")+"'";
		System.out.println("sql语句为："+sql);
		/*String sql = String.format("SELECT * FROM %s WHERE account='%s'", 
		DBnames.Table_Account, 
		requestParam.getString("name"));
		System.out.println(sql);
		*/
		//***last
		CommonResponse res = new CommonResponse();
		try {
			ResultSet result = DatabaseUtil.query(sql); // 数据库查询操作
            //result.getRow();
			
			if (result.next()) {
				if (result.getString("password").equals(requestParam.getString("password"))) {
					res.setResult("1", "登陆成功");
					res.getProperty().put("custId", result.getString("_id"));
				} else {
					res.setResult("2", "登录失败，登录密码错误");
				}
			} else {
				res.setResult("3", "找不到该账号,该登陆账号未注册");
			}
		} catch (SQLException e) {
			res.setResult("300", "数据库查询错误");
			e.printStackTrace();
		}

		// 第三步：将结果封装成Json格式准备返回给客户端，但实际网络传输时还是传输json的字符串
		// 和我们之前的String例子一样，只是Json提供了特定的字符串拼接格式
		// 因为服务端JSon是用到经典的第三方JSon包，功能强大，不用像Android中那样自己手动转，直接可以从Bean转到JSon格式
		String resStr = JSONObject.fromObject(res).toString();
		
		System.out.println(resStr);
        //response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // 可以对字符串进行加密操作，相应的到了客户端就需要解密
		resp.getWriter().append(resStr).flush();
	}

	
}
