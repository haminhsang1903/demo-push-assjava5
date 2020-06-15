package poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.org.apache.bcel.internal.generic.NEW;

import poly.entity.Departs;
import poly.entity.Users;

@Transactional
@Controller
public class AccountController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("account")
	public String account(ModelMap model) {
		model.addAttribute("getUser", getUser());
		model.addAttribute("user", new Users());
		return "account";
	}

	@RequestMapping(value = "formUser", params = "btn-insert")
	public String insert(ModelMap model, @Validated @ModelAttribute("user") Users user, BindingResult error) {
		if (error.hasErrors()) {
			model.addAttribute("getUser", getUser());
			model.addAttribute("user", user);
		} else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			try {
				session.save(user);
				tran.commit();
				model.addAttribute("message", "Insert success");
				model.addAttribute("color", "green");
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());
			} catch (Exception e) {
				System.out.print(e);
				tran.rollback();
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());
				model.addAttribute("message", "Insert fail");
				model.addAttribute("color", "red");
				return "account";
			}

			finally {
				session.close();
			}
		}
		return "account";
	}

	@RequestMapping(value = "formUser", params = "btn-delete")
	public String deleteForm(ModelMap model, @ModelAttribute("user") Users user) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		Session find = factory.getCurrentSession();
		Users findId = (Users) find.get(Users.class, user.getUsername());
		if (user.getUsername().isEmpty()) {
			model.addAttribute("getUser", getUser());
			model.addAttribute("user", new Users());
		} else if (findId == null) {
			model.addAttribute("message", "Username not exist");
			model.addAttribute("color", "red");
			model.addAttribute("getUser", getUser());
			model.addAttribute("user", new Users());
			return "account";
		} else {
			try {
				session.delete(user);
				tran.commit();
				model.addAttribute("message", "Delete success");
				model.addAttribute("color", "green");
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());
			} catch (Exception e) {
				System.out.print(e);
				tran.rollback();
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());
				model.addAttribute("message", "Delete fail");
				model.addAttribute("color", "red");
				return "account";
			} finally {
				session.close();
			}
		}
		return "account";
	}

	@RequestMapping(value = "tableUser", params = "btn-edit")
	public String edit(ModelMap model, HttpServletRequest request) {
		String username = request.getParameter("id-info");
		Session session = factory.getCurrentSession();
		Users user = (Users) session.get(Users.class, username);
		model.addAttribute("user", user);
		model.addAttribute("getUser", getUser());
		return "account";
	}

	@RequestMapping(value = "tableUser", params = "btn-delete")
	public String deleteTable(ModelMap model, HttpServletRequest request) {
		String username = request.getParameter("id-info");
		Session session = factory.getCurrentSession();
		Users user = (Users) session.get(Users.class, username);
		Session sessionD = factory.openSession();
		Transaction tran = sessionD.beginTransaction();
		try {
			sessionD.delete(user);
			tran.commit();
			model.addAttribute("user", new Users());
			model.addAttribute("getUser", getUser());
		} catch (Exception e) {
			tran.rollback();
			model.addAttribute("user", new Users());
			model.addAttribute("getUser", getUser());
			return "account";
		} finally {
			sessionD.close();
		}
		return "account";
	}

	@RequestMapping(value = "formUser", params = "btn-update")
	public String update(ModelMap model, @Validated @ModelAttribute("user") Users user, BindingResult error) {
		Session find = factory.openSession();
		if (error.hasErrors()) {
			model.addAttribute("getUser", getUser());
			model.addAttribute("user", user);
		}
		else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			try {
				session.update(user);
				tran.commit();
				model.addAttribute("message", "Update success");
				model.addAttribute("color", "green");
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());

			} catch (Exception e) {
				tran.rollback();
				model.addAttribute("getUser", getUser());
				model.addAttribute("user", new Users());
				model.addAttribute("message", "Update fail");
				model.addAttribute("color", "red");
				return "account";
			} finally {
				session.close();
				find.close();
			}

		}
		return "account";

	}

	@RequestMapping(value = "formUser", params = "btn-search")
	public String search(ModelMap model, HttpServletRequest request) {
		String name = request.getParameter("search");
		Session session = factory.getCurrentSession();
		String hql = "FROM Users d where d.fullname like '%" + name + "%'";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		model.addAttribute("getUser", list);
		model.addAttribute("user", new Users());
		return "account";
	}

	@RequestMapping(value = "formUser", params = "btn-clear")
	public String clear(ModelMap model) {
		model.addAttribute("user", new Users());
		model.addAttribute("getUser", getUser());
		return "account";
	}

	public List<Users> getUser() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Users";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		return list;
	}
}
