package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
public class GeneralController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("general")
	public String general(ModelMap model) {
		 model.addAttribute("departRecord",departRecord());
		model.addAttribute("staffRecord", staffRecord());
		return "general";
	}
	public List<Object[]> staffRecord(){
		Session session = factory.getCurrentSession();
		String hql = "SELECT r.staff.id,  \r\n" + 
				     "SUM(case when r.type = 1 then 1 else 0 end) , \r\n" + 
				     "SUM(case when r.type = 0 then 1 else 0 end) \r\n" + 
				     "FROM Records r  GROUP BY r.staff.id";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	public List<Object[]> departRecord(){
		Session session = factory.getCurrentSession();
		String hql = "SELECT r.staff.depart.id,  \r\n" + 
			     "SUM(case when r.type = 1 then 1 else 0 end) , \r\n" + 
			     "SUM(case when r.type = 0 then 1 else 0 end) \r\n" + 
			     "FROM Records r  GROUP BY r.staff.depart.id";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	
}
