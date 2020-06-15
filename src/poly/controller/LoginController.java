package poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.entity.Users;

@Transactional
@Controller
public class LoginController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginform(ModelMap model){
		model.addAttribute("message","");
		model.addAttribute("color","white");
		return "login";
	}
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String checklogin(HttpServletRequest request, ModelMap model, HttpSession sessionA) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Session session = factory.getCurrentSession();
		Users user = (Users) session.get(Users.class, username);
		if( user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
			model.addAttribute("message","");
			model.addAttribute("color","white");
			model.addAttribute("staffIndex",staffIndex());
			sessionA.setAttribute("chkUsername", user.getUsername());
			return "index";
		}
		else {		
			model.addAttribute("message","Login fail");
			model.addAttribute("color","red");
			return "login";
		}		
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
