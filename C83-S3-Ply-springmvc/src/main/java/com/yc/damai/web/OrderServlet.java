package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.OrderItemDao;
import com.yc.damai.dao.OrdersDao;
import com.yc.damai.dao.UserDao;

import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
//import com.yc.damai.dao.CommentDao;
import com.yc.damai.po.Result;

@WebServlet("/OrderServlet.do")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDao odao = new OrdersDao();
	private OrderItemDao oidao = new OrderItemDao();
	private CartDao cdao = new CartDao();
	private UserDao udao=new UserDao();

	//private CommentDao CommentDao = new CommentDao();
	
	protected void update1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id"); // 获取用户ID
		int i = odao.update1(id);
		if (i > 0) {
			response.getWriter().append("{\"msg\":\"付款成功!\"}");
		} else {
			response.getWriter().append("{\"msg\":\"付款失败!\"}");
		}

	}
//已发货
	protected void update2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id"); // 获取用户ID
		int i = odao.update2(id);
		if (i > 0) {
			print(response, new Result(1, "成功发货!"));
		} else {
			print(response, new Result(0, "发货失败!"));
		}

	}
	
	//确定收获
		protected void update3(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String id = request.getParameter("id"); // 获取用户ID
			int i = odao.update3(id);
			if (i > 0) {
				print(response, new Result(1, "操作成功!"));
			} else {
				print(response, new Result(0, "操作失败!"));
			}

		}
	
	// 添加订单
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从会话中获取 用户map
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		// 判断用户对象是否存在, 表示是否登录
		if (user == null) {
			response.getWriter().append("{\"msg\":\"请先登录系统!\"}");
			// response.getWriter().print("请先登录系统");
			return;
		}

		String uid = "" + user.get("id"); // 获取用户ID
		odao.insert(uid);
		oidao.insert(uid);
		cdao.deleteByUid(uid);
		response.getWriter().append("{\"code\":\"1\"}");
	}

	// 添加订单 增加了事务处理
//	protected void adds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 从会话中获取 用户map
//		@SuppressWarnings("unchecked")
//		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
//
//		// 判断用户对象是否存在, 表示是否登录
//		if (user == null) {
//			response.getWriter().append("{\"msg\":\"请先登录系统!\"}");
//			// response.getWriter().print("请先登录系统");
//			return;
//		}
//
//		String uid = "" + user.get("id"); // 获取用户ID
//
//		try {
//			if (odao1.payOrders(uid) > 0) {
//				response.getWriter().append("{\"code\":\"1\"}");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	// 查询新增的订单
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从会话中获取 用户map
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		// 判断用户对象是否存在, 表示是否登录
		if (user == null) {
			response.getWriter().append("{\"msg\":\"请先登录系统!\"}");
			// response.getWriter().print("请先登录系统");
			return;
		}

		String uid = "" + user.get("id"); // 获取用户ID,
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> orders = odao.queryNewOrders(uid);
		ret.put("orders", orders);
		ret.put("orderitem", oidao.queryByOid("" + orders.get("id")));
		print(response, ret);
	}

	// 查询新增的订单
	protected void query2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从会话中获取 用户map
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		// 判断用户对象是否存在, 表示是否登录
		if (user == null) {
			response.getWriter().append("{\"msg\":\"请先登录系统!\"}");
			// response.getWriter().print("请先登录系统");
			return;
		}

		String uid = "" + user.get("id"); // 获取用户ID,
		String oid = request.getParameter("oid");
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> orders = odao.queryByoid(uid, oid);
		ret.put("orders", orders);
		ret.put("orderitem", oidao.queryByOid("" + orders.get("id")));
		print(response, ret);
	}
	// 查询订单的状态
//	protected void queryStates(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<?> list = odao1.queryStates();
//		print(response, list);
//	}
	// 查询的订单
//	protected void queryAll(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<?> list = odao1.queryAllOrders();
//		print(response, list);
//	}

	// 查询的订单
	protected void queryOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从会话中获取 用户map
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		// 判断用户对象是否存在, 表示是否登录
		if (user == null) {
			response.getWriter().append("{\"msg\":\"请先登录系统!\"}");
			// response.getWriter().print("请先登录系统");
			return;
		}

		String uid = "" + user.get("id"); // 获取用户ID,

		List<Map<String, Object>> orders = odao.queryOrders(uid);
//		
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("list", orders);
//		
		
		print(response, orders);
	}
	
	
	protected void queryename(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String loname = udao.queryename((Map<String, Object>)request.getSession().getAttribute("loginedUser")) + "";
		//String uid="2";
		
		//List<Map<String, Object>> orders = odao.queryByUid(uid);

		print( response, loname);

	}
	
	
	protected void query3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		DmOrders dos=new DmOrders();
		
		
		
		BeanUtils.populate(dos, request.getParameterMap());
		String sql="select * from dm_orders  ";
		List<?> list= odao.query1(dos, page, rows);
		int total =odao.count1(dos);
		HashMap<String,Object> data=new HashMap<>();
		//HashMap<String,Object> data=new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		print(response,data);
	}
	

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmOrders do1=new DmOrders();
		BeanUtils.populate(do1, request.getParameterMap());
//		if(dp.getPname()==null||dp.getPname().trim().isEmpty()) {
//			print(response,new Result(0,"商品名称不能为空！"));
//			return;
//			
//		}
//		if(dp.getShopPrice()==null||dp.getShopPrice()<=0) {
//			print(response,new Result(0,"商品商品价格必须大于0！"));
//			return;
//			
//		}
//		if(dp.getId() == null || dp.getId() == 0) {
//			pdao.insert(dp);
//		} else {
//			pdao.update(dp);
//		}
		odao.update(do1);
		print( response, new Result(1,"商品保存成功!"));
	}
	

}
