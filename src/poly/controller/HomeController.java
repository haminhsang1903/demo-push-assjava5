package poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.entity.Departs;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		model.addAttribute("staffIndex",staffIndex());
		return "index";
	}


	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("chkUsername", null);
		return "login";
	}
	public List<Object[]> staffIndex(){
		Session session = factory.getCurrentSession();
		String hql = "Select  r.staff.photo, r.staff.name, r.staff.depart.name from Records r "+
				"GROUP BY r.staff.photo, r.staff.name, r.staff.depart.name "+
				"ORDER BY SUM(case when r.type = 1 then 1 else 0 end) DESC ";

		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Object[]> list = query.list();
		return list;
	}
}
