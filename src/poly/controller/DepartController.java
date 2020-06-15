package poly.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.bcel.internal.generic.NEW;

import poly.entity.Departs;
import poly.entity.Staffs;

@Transactional
@Controller
public class DepartController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("depart")
	public String depart(ModelMap model) {
		model.addAttribute("depart", new Departs());
		getDepart(model);
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-clear")
	public String clear(ModelMap model) {
		model.addAttribute("depart", new Departs());
		getDepart(model);
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-insert")
	public String insert(ModelMap model, @Validated @ModelAttribute("depart") Departs depart, BindingResult errors) {
		if (errors.hasErrors()) {
			getDepart(model);
		} else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			try {
				session.save(depart);
				tran.commit();
				getDepart(model);
				model.addAttribute("message", "Insert success !");
				model.addAttribute("color", "green");
				model.addAttribute("depart", new Departs());
			} catch (Exception e) {
				System.out.print(e.getMessage());
				model.addAttribute("depart", new Departs());
				getDepart(model);
				tran.rollback();
			} finally {
				session.close();
			}
		}
		return "depart";
	}

	@RequestMapping(value = "tableDepart", params = "btn-edit")
	public String edit(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id-info");
		Session session = factory.getCurrentSession();
		Departs depart = (Departs) session.get(Departs.class, id);
		model.addAttribute("depart", depart);
		getDepart(model);
		return "depart";
	}

	@RequestMapping(value = "tableDepart", params = "btn-delete")
	public String deleteTable(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id-info");
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		Departs depart = (Departs) session.get(Departs.class, id);
		try {
			session.delete(depart);
			tran.commit();
			model.addAttribute("depart", new Departs());
			getDepart(model);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			model.addAttribute("depart", new Departs());
			getDepart(model);
			tran.rollback();
		} finally {
			session.close();
		}
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-yes")
	public String btnYes(ModelMap model, @ModelAttribute("depart") Departs depart) {
		Session sessionF = factory.openSession();
		Departs find = (Departs) sessionF.get(Departs.class, depart.getId());
		if (find == null) {
			getDepart(model);
			model.addAttribute("message", "ID doesn't exist !");
			model.addAttribute("color", "red");
		} else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			Session deleteStaff = factory.openSession();
			Session deleteRecord = factory.openSession();
			Session selectStaff = factory.openSession();
			//Select staff.id with depart.id
			Query queryStaff = selectStaff.createQuery("Select s.id from Staffs s where s.depart.id = :departID");
			queryStaff.setParameter("departID", depart.getId());
			List<String> listIDStaff = queryStaff.list();
			// Remove staff.id in Record
			for(String i : listIDStaff) {
			Query queryS = deleteRecord.createQuery("Delete from Records r where r.staff.id = :staffID");
			queryS.setParameter("staffID", i);	
			queryS.executeUpdate();
			}
			//Remove Staff.id with depart.id
			Query queryR = deleteStaff.createQuery("Delete from Staffs s where s.depart.id = :departID");
			queryR.setParameter("departID", depart.getId());
			queryR.executeUpdate();
			try {
				session.delete(depart);
				tran.commit();
				model.addAttribute("depart", new Departs());
				model.addAttribute("tableStaff", getDepart(model));
				model.addAttribute("nameimage", null);
				model.addAttribute("message","Delete success");
				model.addAttribute("color","green");
			} catch (Exception e) {
				tran.rollback();

			} finally {
				session.close();
				deleteStaff.close();
				deleteRecord.close();
				selectStaff.close();
			}
		}
		model.addAttribute("depart", new Departs());
		getDepart(model);
		model.addAttribute("displayDe", null);
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-no")
	public String btnNo(ModelMap model) {
		model.addAttribute("depart", new Departs());
		getDepart(model);
		model.addAttribute("displayDe", null);
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-delete")
	public String deleteformDepart(ModelMap model, @ModelAttribute("depart") Departs depart, BindingResult error) {
		model.addAttribute("displayDe", "click");
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-update")
	public String update(ModelMap model, @Validated @ModelAttribute("depart") Departs depart, BindingResult error) {
		Session session = factory.openSession();
		Session sessionf = factory.openSession();
		Transaction tran = session.beginTransaction();
		Departs find = (Departs) sessionf.get(Departs.class, depart.getId());
		if (error.hasErrors()) {
			getDepart(model);
		} else if (find == null) {
			model.addAttribute("message", "Id doesn't exist !");
			model.addAttribute("color", "red");
			getDepart(model);
		} else {
			try {
				session.update(depart);
				tran.commit();
				model.addAttribute("message", "Update success!");
				model.addAttribute("color", "green");
				model.addAttribute("depart", new Departs());
				getDepart(model);
			} catch (Exception e) {
				model.addAttribute("message", "Update fail!");
				model.addAttribute("color", "red");
				model.addAttribute("depart", new Departs());
				getDepart(model);
				System.out.print(e);
				tran.rollback();
			} finally {
				session.close();
				sessionf.close();
			}
		}
		return "depart";
	}

	@RequestMapping(value = "formDepart", params = "btn-search")
	public String search(ModelMap model, HttpServletRequest request) {
		String name = request.getParameter("search");
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs d where d.name like :searchkey";
		Query query = session.createQuery(hql);
		query.setParameter("searchkey", "%" + name + "%");
		List<Departs> list = query.list();
		model.addAttribute("getDepart", list);
		model.addAttribute("depart", new Departs());
		return "depart";
	}

	public List<Departs> getDepart(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		model.addAttribute("getDepart", list);
		return list;
	}

}
