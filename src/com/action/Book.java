<<<<<<< HEAD
=======
package com.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.actionForm.BookForm;
import com.dao.BookDAO;cvxcvxxzv
bbbbbbbbbbbbb123
import java.io.IOException;
import java.util.Date;

public class Book extends HttpServlet {fsdfasdfsad
	private BookDAO bookDAO = null;

	public Book() {666666666666666666666688888888888899999258789
		this.bookDAO = new BookDAO();//fhhgfhgfbvcbcvvxvcxvxc zzw123
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("\nbook*********************action=" + action);
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "娣団剝浼呴柨娆掝嚖");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("bookAdd".equals(action)) {
			bookAdd(request, response);
		} else if ("bookQuery".equals(action)) {
			bookQuery(request, response);
		} else if ("bookModifyQuery".equals(action)) {
			bookModifyQuery(request, response);
		} else if ("bookModify".equals(action)) {
			bookModify(request, response);
		} else if ("bookDel".equals(action)) {
			bookDel(request, response);
		} else if ("bookDetail".equals(action)) {
			bookDetail(request, response);
		} else if ("bookifQuery".equals(action)) {
			bookifQuery(request, response);
		}
	}

	/*********************** 闁跨喐鏋婚幏鐑芥晸闁扮數銆嬮幏鐑芥晸閺傘倖瀚归柨鐔荤窛閿燂拷 **************************/
	private void bookAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setBarcode(request.getParameter("barcode"));
		bookForm.setBookName(request.getParameter("bookName"));
		bookForm.setTypeId(Integer.parseInt(request.getParameter("typeId")));
		bookForm.setAuthor(request.getParameter("author"));
		bookForm.setTranslator(request.getParameter("translator"));
		bookForm.setIsbn(request.getParameter("isbn"));
		bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		bookForm.setPage(request.getParameter("page")==""?0:Integer.parseInt(request.getParameter("page")));
		bookForm.setBookcaseid(Integer.parseInt(request
				.getParameter("bookcaseid")));
		// 闁跨喐鏋婚幏宄板絿缁崵绮洪柨鐔告灮閹风兘鏁撻弬銈嗗
		Date date1 = new Date();
		java.sql.Date date = new java.sql.Date(date1.getTime());
		bookForm.setInTime(date.toString());
		bookForm.setOperator(request.getParameter("operator"));
		int a = bookDAO.insert(bookForm);
		if (a == 1) {
			request.getRequestDispatcher("book_ok.jsp?para=1").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "闁跨喐鏋婚幏宄版禈闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归幁顖炴晸缁愭牗鎷濋幏鐑芥晸閺傘倖瀚瑰Ο閬嶆晸閿燂拷");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("error", "閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖闁跨喐鏋婚幏鐑芥晸缂佺倯宥嗗閸╀粙鏁撻敓锟�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/*********************** 闁跨喐鏋婚幏鐤嚄閸忋劑鏁撻弬銈嗗閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖 **************************/
	private void bookQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("book", bookDAO.query(str)); // 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚圭拠銏ゆ晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗婵炲绂塷ok闁跨喐鏋婚幏锟�
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}

	/*********************** 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风柉顕楅崶楣冩晸閺傘倖瀚归柨鐔告灮閹烽攱浼� **************************/
	private void bookifQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		if (request.getParameter("f") != null) {
			str = request.getParameter("f") + " like '%"
					+ request.getParameter("key") + "%";
		}
		request.setAttribute("ifbook", bookDAO.query(str));
		System.out.print("闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风柉顕楅崶楣冩晸閺傘倖瀚归柨鐔告灮閹烽攱浼呴弮鍫曟晸閺傘倖瀚箂tr:" + str);
		request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
	}

	/*********************** 闁跨喐鏋婚幏鐤嚄闁跨喓娼鹃棃鈺傚閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖 **************************/
	private void bookModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		System.out.println("闁跨喐鏋婚幏鐤嚄闁跨喓娼鹃棃鈺傚閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖闁跨喐鏋婚幏锟�" + request.getParameter("ID"));
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_Modify.jsp").forward(request,
				response);
	}

	/*********************** 闁跨喐鏋婚幏鐤嚄閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏椋庣矎闁跨喐鏋婚幏閿嬩紖 **************************/
	private void bookDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookDetail", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_detail.jsp").forward(request,
				response);
	}

	/*********************** 闁跨喓娼鹃棃鈺傚閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖 **************************/
	private void bookModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm(); // 鐎圭偤鏁撻弬銈嗗闁跨喐鏋婚幏绋okForm闁跨喐鏋婚幏锟�
		bookForm.setId(Integer.parseInt(request.getParameter("id")));
		bookForm.setBarcode(request.getParameter("barcode")); // 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
		bookForm.setBookName(request.getParameter("bookName"));
		bookForm.setTypeId(Integer.parseInt(request.getParameter("typeId")));
		bookForm.setAuthor(request.getParameter("author"));
		bookForm.setTranslator(request.getParameter("translator"));
		bookForm.setIsbn(request.getParameter("isbn"));
		bookForm.setPrice(Float.valueOf(request.getParameter("price")));
		bookForm.setPage(request.getParameter("page")==""?0:Integer.parseInt(request.getParameter("page")));
		bookForm.setBookcaseid(Integer.parseInt(request
				.getParameter("bookcaseid")));
		bookForm.setOperator(request.getParameter("operator"));
		int ret = bookDAO.update(bookForm); // 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔烘骄闂堚晜瀚归崶楣冩晸閺傘倖瀚归柨鐔告灮閹烽攱浼呴柨鐔惰寧閸戙倖瀚归柨鐔告灮閹风pdate()
		if (ret == 0) {
			request.setAttribute("error", "闁跨喓娼鹃棃鈺傚閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖婢堕亶鏁撻弶甯秶閹凤拷");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 鏉烆剟鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽銇氭い鐢告晸閺傘倖瀚�
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=2").forward(request,
					response); // 鏉烆剟鏁撻弬銈嗗闁跨喓娼鹃弨瑙勫灇閻у憡瀚规い鐢告晸閺傘倖瀚�
		}
	}

	/*********************** 閸掔娀鏁撻弬銈嗗閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖 **************************/
	private void bookDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		int ret = bookDAO.delete(bookForm);
		if (ret == 0) {
			request.setAttribute("error", "閸掔娀鏁撻弬銈嗗閸ラ箖鏁撻弬銈嗗闁跨喐鏋婚幏閿嬩紖婢堕亶鏁撻弶甯秶閹凤拷");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=3").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
>>>>>>> 39d10befb6fd5dc0839857daee01e7161f55efe8
