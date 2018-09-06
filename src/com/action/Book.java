package com.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.actionForm.BookForm;
import com.dao.BookDAO;

import java.io.IOException;
import java.util.Date;

public class Book extends HttpServlet {
	private BookDAO bookDAO = null;

	public Book() {666666666666666666666688888888888899999258
		this.bookDAO = new BookDAO();//fhhgfhgfbvcbcvvxvcxvxc zzw123
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("\nbook*********************action=" + action);
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "淇℃伅閿欒");
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

	/*********************** 閿熸枻鎷烽敓閰电》鎷烽敓鏂ゆ嫹閿熻緝锟� **************************/
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
		// 閿熸枻鎷峰彇绯荤粺閿熸枻鎷烽敓鏂ゆ嫹
		Date date1 = new Date();
		java.sql.Date date = new java.sql.Date(date1.getTime());
		bookForm.setInTime(date.toString());
		bookForm.setOperator(request.getParameter("operator"));
		int a = bookDAO.insert(bookForm);
		if (a == 1) {
			request.getRequestDispatcher("book_ok.jsp?para=1").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "閿熸枻鎷峰浘閿熸枻鎷烽敓鏂ゆ嫹鎭敓绐栨拝鎷烽敓鏂ゆ嫹妯遍敓锟�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("error", "鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅閿熸枻鎷烽敓缁炑嶆嫹鍩介敓锟�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/*********************** 閿熸枻鎷疯鍏ㄩ敓鏂ゆ嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅 **************************/
	private void bookQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("book", bookDAO.query(str)); // 閿熸枻鎷烽敓鏂ゆ嫹璇㈤敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹濞寸禉ook閿熸枻鎷�
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}

	/*********************** 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅 **************************/
	private void bookifQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		if (request.getParameter("f") != null) {
			str = request.getParameter("f") + " like '%"
					+ request.getParameter("key") + "%";
		}
		request.setAttribute("ifbook", bookDAO.query(str));
		System.out.print("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅鏃堕敓鏂ゆ嫹str:" + str);
		request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
	}

	/*********************** 閿熸枻鎷疯閿熺潾闈╂嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅 **************************/
	private void bookModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		System.out.println("閿熸枻鎷疯閿熺潾闈╂嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅閿熸枻鎷�" + request.getParameter("ID"));
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_Modify.jsp").forward(request,
				response);
	}

	/*********************** 閿熸枻鎷疯鍥鹃敓鏂ゆ嫹閿熸枻鎷风粏閿熸枻鎷锋伅 **************************/
	private void bookDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookDetail", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_detail.jsp").forward(request,
				response);
	}

	/*********************** 閿熺潾闈╂嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅 **************************/
	private void bookModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm(); // 瀹為敓鏂ゆ嫹閿熸枻鎷稡ookForm閿熸枻鎷�
		bookForm.setId(Integer.parseInt(request.getParameter("id")));
		bookForm.setBarcode(request.getParameter("barcode")); // 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
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
		int ret = bookDAO.update(bookForm); // 閿熸枻鎷烽敓鏂ゆ嫹閿熺潾闈╂嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅閿熶茎鍑ゆ嫹閿熸枻鎷穟pdate()
		if (ret == 0) {
			request.setAttribute("error", "閿熺潾闈╂嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅澶遍敓鏉帮綇鎷�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 杞敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷风ず椤甸敓鏂ゆ嫹
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=2").forward(request,
					response); // 杞敓鏂ゆ嫹閿熺潾鏀规垚鐧告嫹椤甸敓鏂ゆ嫹
		}
	}

	/*********************** 鍒犻敓鏂ゆ嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅 **************************/
	private void bookDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		int ret = bookDAO.delete(bookForm);
		if (ret == 0) {
			request.setAttribute("error", "鍒犻敓鏂ゆ嫹鍥鹃敓鏂ゆ嫹閿熸枻鎷锋伅澶遍敓鏉帮綇鎷�");
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
