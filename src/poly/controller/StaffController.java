package poly.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import poly.entity.Departs;
import poly.entity.Staffs;

@Transactional
@Controller
public class StaffController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	public Staffs staffFull = null;
	public boolean checkEdit = false;

	@RequestMapping("staff")
	public String staff(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs";
		Query query = session.createQuery(hql);
		List<Staffs> list = query.list();
		model.addAttribute("tableStaff", list);
		model.addAttribute("staff", new Staffs());
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-insert")
	public String insert(ModelMap model, @Validated @ModelAttribute("staff") Staffs staff, BindingResult error)
			throws Exception {
		if (error.hasErrors()) {
//			 getStaff(model);
		} else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			List<Staffs> list = null;
			try {

				System.out.println(staff.getBirthday().getYear());
				session.save(staff);
				tran.commit();
				Session sessionFind = factory.getCurrentSession();
				String hql = "FROM Staffs s where s.name like '%" + staff.getName() + "%'";
				Query query = sessionFind.createQuery(hql);
				list = query.list();
				model.addAttribute("message", "Insert success");
				model.addAttribute("color", "green");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("messageAll", "Insert full info");
				model.addAttribute("staff", staff);
				tran.rollback();
				return "staff";
			} finally {
				session.close();

				model.addAttribute("tableStaff", list);
				model.addAttribute("staff", new Staffs());
				model.addAttribute("nameimage", null);
			}
		}
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-search")
	public String search(ModelMap model, HttpServletRequest request) {
		String name = request.getParameter("search");
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs s where s.name like :searchkey";
		Query query = session.createQuery(hql);
		query.setParameter("searchkey", "%" + name + "%");
		List<Staffs> list = query.list();
		model.addAttribute("tableStaff", list);
		model.addAttribute("staff", new Staffs());
		return "staff";
	}

	@ModelAttribute("departs")
	public List<Departs> getDepart() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		return list;
	}
	@ModelAttribute("tableStaff")
	public List<Staffs> getStaff(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs";
		Query query = session.createQuery(hql);
		List<Staffs> list = query.list();
		return list;
	}

	@RequestMapping(value = "formStaff", params = "btn-submit")
	public String upload(ModelMap model, @RequestParam("photo") CommonsMultipartFile image)
			throws InterruptedException {
		if (image.isEmpty()) {
			model.addAttribute("nameimage", null);
//			getStaff(model);
			model.addAttribute("staff", new Staffs());
			model.addAttribute("messageImage", "Please choose file");
		} else {
			try {
				String path = context.getRealPath("/assets/images/");
				byte[] bytes = image.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path + File.separator + image.getOriginalFilename())));
				stream.write(bytes);
				stream.flush();
				stream.close();
				model.addAttribute("nameimage", image.getOriginalFilename());
				System.out.println(checkEdit);
				if (checkEdit) {
					model.addAttribute("staff", staffFull);
					staffFull = null;
					checkEdit = false;
				} else {
					model.addAttribute("staff", new Staffs());
				}
			} catch (Exception e) {
				System.out.print(e);
				model.addAttribute("messageImage", "Error submit");
				return "staff";
			} finally {
//				getStaff(model);
			}
		}
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-delete")
	public String deleteForm(ModelMap model, @ModelAttribute("staff") Staffs staff) {
		model.addAttribute("displaySt", "click");
		model.addAttribute("staff", staff);
//		getStaff(model);
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-yes")
	public String btnYes(ModelMap model, @ModelAttribute("staff") Staffs staff) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		Session find = factory.getCurrentSession();
		Staffs findStaff = (Staffs) find.get(Staffs.class, staff.getId());
		if (findStaff == null) {
			model.addAttribute("message", "ID doesn't exist");
			model.addAttribute("color", "red");
		} else {
			try {
				Session deleteRecord = factory.openSession();
				Query query = deleteRecord.createQuery("Delete from Records r where r.staff.id = :keyStaff");
				query.setParameter("keyStaff", staff.getId());
				query.executeUpdate();
				session.delete(staff);
				tran.commit();
				model.addAttribute("staff", new Staffs());
//				getStaff(model);
				model.addAttribute("nameimage", null);
				model.addAttribute("message", "Delete success");
				model.addAttribute("color", "green");
			} catch (Exception e) {
				tran.rollback();

			} finally {
				session.close();

			}
		}
		model.addAttribute("staff", new Staffs());
//		getStaff(model);
		model.addAttribute("displaySt", null);
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-no")
	public String btnNo(ModelMap model) {
		model.addAttribute("staff", new Staffs());
//		getStaff(model);
		model.addAttribute("displaySt", null);
		return "staff";
	}

	@RequestMapping(value = "tableStaff", params = "btn-edit")
	public String edit(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id-info");
		Session session = factory.getCurrentSession();
		Staffs staff = (Staffs) session.get(Staffs.class, id);
		DecimalFormat df = new DecimalFormat("#,##0");
		staff.setSalary(Double.parseDouble(df.format(staff.getSalary()).replaceAll(",", "")));
		System.out.println(staff.getSalary());
		model.addAttribute("staff", staff);
//		getStaff(model);
		model.addAttribute("nameimage", staff.getPhoto());
		staffFull = staff;
		checkEdit = true;
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-update")
	public String update(ModelMap model, @Validated @ModelAttribute("staff") Staffs staff, BindingResult error) {
		Session find = factory.openSession();
		Staffs findStaff = (Staffs) find.get(Staffs.class, staff.getId());
		if (error.hasErrors()) {
//			getStaff(model);
			model.addAttribute("staff", staff);
			model.addAttribute("nameimage", staff.getPhoto());
		} else if (findStaff == null) {
//			getStaff(model);
			model.addAttribute("staff", staff);
			model.addAttribute("nameimage", staff.getPhoto());
			model.addAttribute("messageAll", "ID doesn't exist ");
		} else {
			Session session = factory.openSession();
			Transaction tran = session.beginTransaction();
			try {
				session.update(staff);
				tran.commit();
				model.addAttribute("staff", new Staffs());
				model.addAttribute("nameimage", null);
				model.addAttribute("message", "Update success");
				model.addAttribute("color", "green");
				checkEdit = false;
				staffFull = null;
//				getStaff(model);
			} catch (Exception e) {
				e.getStackTrace();
				tran.rollback();
//				getStaff(model);
				return "staff";
			} finally {
				session.close();
				find.close();
			}
		}
		return "staff";
	}

	@RequestMapping(value = "formStaff", params = "btn-clear")
	public String clear(ModelMap model) {
		model.addAttribute("staff", new Staffs());
//		getStaff(model);
		model.addAttribute("nameimage", null);
		return "staff";
	}

//	@InitBinder
//	private void dateBinder(WebDataBinder binder) {
//		// The date format to parse or output your dates
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		// Create a new CustomDateEditor
//		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//		// Register it as custom editor for the Date type
//		binder.registerCustomEditor(Date.class, editor);
//	}

}
