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

	public Book() {
		this.bookDAO = new BookDAO();//fhhgfhgfbvcbcvvxvcxvxc zzw123
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("\nbook*********************action=" + action);
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "���Ĳ�������");
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

	/*********************** ���ͼ����Ϣ **************************/
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
		// ��ȡϵͳ����
		Date date1 = new Date();
		java.sql.Date date = new java.sql.Date(date1.getTime());
		bookForm.setInTime(date.toString());
		bookForm.setOperator(request.getParameter("operator"));
		int a = bookDAO.insert(bookForm);
		if (a == 1) {
			request.getRequestDispatcher("book_ok.jsp?para=1").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "��ͼ����Ϣ�Ѿ���ӣ�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("error", "ͼ����Ϣ���ʧ�ܣ�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/*********************** ��ѯȫ��ͼ����Ϣ **************************/
	private void bookQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("book", bookDAO.query(str)); // ����ѯ������浽book��
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}

	/*********************** ������ѯͼ����Ϣ **************************/
	private void bookifQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		if (request.getParameter("f") != null) {
			str = request.getParameter("f") + " like '%"
					+ request.getParameter("key") + "%";
		}
		request.setAttribute("ifbook", bookDAO.query(str));
		System.out.print("������ѯͼ����Ϣʱ��str:" + str);
		request.getRequestDispatcher("bookQuery.jsp").forward(request, response);
	}

	/*********************** ��ѯ�޸�ͼ����Ϣ **************************/
	private void bookModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		System.out.println("��ѯ�޸�ͼ����Ϣ��" + request.getParameter("ID"));
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_Modify.jsp").forward(request,
				response);
	}

	/*********************** ��ѯͼ����ϸ��Ϣ **************************/
	private void bookDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		request.setAttribute("bookDetail", bookDAO.queryM(bookForm));
		request.getRequestDispatcher("book_detail.jsp").forward(request,
				response);
	}

	/*********************** �޸�ͼ����Ϣ **************************/
	private void bookModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm(); // ʵ����BookForm��
		bookForm.setId(Integer.parseInt(request.getParameter("id")));
		bookForm.setBarcode(request.getParameter("barcode")); // ��ȡ����������������
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
		int ret = bookDAO.update(bookForm); // �����޸�ͼ����Ϣ�ķ���update()
		if (ret == 0) {
			request.setAttribute("error", "�޸�ͼ����Ϣʧ�ܣ�");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // ת��������ʾҳ��
		} else {
			request.getRequestDispatcher("book_ok.jsp?para=2").forward(request,
					response); // ת���޸ĳɹ�ҳ��
		}
	}

	/*********************** ɾ��ͼ����Ϣ **************************/
	private void bookDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BookForm bookForm = new BookForm();
		bookForm.setId(Integer.valueOf(request.getParameter("ID")));
		int ret = bookDAO.delete(bookForm);
		if (ret == 0) {
			request.setAttribute("error", "ɾ��ͼ����Ϣʧ�ܣ�");
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
