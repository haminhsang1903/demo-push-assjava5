package poly.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.xpath.internal.operations.Gte;

import poly.entity.Departs;
import poly.entity.Records;
import poly.entity.Staffs;

@Transactional
@Controller
public class RecordController {
	@Autowired
	SessionFactory factory;

	@Autowired
	JavaMailSender mailer;

	@RequestMapping("record")
	public String record(ModelMap model) {
		model.addAttribute("record", new Records());
		getRecord(model);
		return "record";
	}

	public final String from = "adventurer15999@gmail.com";

	@RequestMapping(value = "formRecord", params = "btn-insert")
	public String insert(ModelMap model, @ModelAttribute("record") Records record) {
		Session session = factory.openSession();
		Transaction tran = session.beginTransaction();
		Session find = factory.getCurrentSession();
		Staffs staff = (Staffs) session.get(Staffs.class, record.getStaff().getId());
		System.out.print(staff.getEmail());
		try {
			record.setDate(new Date());
			session.save(record);
			tran.commit();
			model.addAttribute("record", new Records());
			model.addAttribute("message", "Insert success and check Email");
			model.addAttribute("color", "green");
			getRecord(model);
		} catch (Exception e) {
			System.out.print(e);
			model.addAttribute("message", "Insert full info");
			model.addAttribute("color", "red");
			tran.rollback();
			return "record";
		}
		try {
			// Tao mail
			MimeMessage mail = mailer.createMimeMessage();
			// Su dung lop ho tro
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from);
			helper.setTo(staff.getEmail());
			helper.setReplyTo(from, from);
			String subject = "";
			String body = "";
			if (record.getType() == 1) {
				subject = "Mail khen thưởng";
				body = "Hy vọng bạn sẽ lập được thêm nhiều thành tích.";
			} else {
				subject = "Mail khiển trách";
				body = "Hy vọng bạn sẽ không vi phạm thêm lần nào nữa";
			}
			helper.setSubject(subject);
			helper.setText(body, true);
			// Gui mail
			mailer.send(mail);
		} catch (Exception e) {
			model.addAttribute("message", "Send fail");
			System.out.print(e);
		} finally {
			session.close();
		}
		return "record";
	}

	@RequestMapping(value = "tableRecord", params = "btn-edit")
	public String edit(ModelMap model, HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id-info"));
		Session session = factory.getCurrentSession();
		Records record = (Records) session.get(Records.class, id);
		model.addAttribute("record", record);
		getRecord(model);
		return "record";
	}

	@RequestMapping(value = "formRecord", params = "btn-clear")
	public String clear(ModelMap model) {
		model.addAttribute("record", new Records());
		getRecord(model);
		return "record";
	}

	public List<Records> getRecord(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Records";
		Query query = session.createQuery(hql);
		List<Records> list = query.list();
		model.addAttribute("getRecord", list);
		return list;
	}

	@RequestMapping(value = "formRecord", params = "btn-search")
	public String search(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String name = request.getParameter("search");
		Session session = factory.getCurrentSession();
		System.out.print(name);
		String hql = "FROM Records d where d.staff.name like :searchkey";
		Query query = session.createQuery(hql);
		query.setParameter("searchkey", "%"+name+"%");
		List<Records> list = query.list();
		model.addAttribute("getRecord", list);
		model.addAttribute("record", new Records());
		return "record";
	}

	@ModelAttribute("staffs")
	public List<Staffs> getStaffs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs";
		Query query = session.createQuery(hql);
		List<Staffs> list = query.list();
		return list;
	}

	@RequestMapping(value = "tableRecord", params = "btn-send")
	public String form(ModelMap model, HttpServletRequest request) {
		String idstaff = request.getParameter("id-staff");
		Session session = factory.getCurrentSession();
		Staffs staff = (Staffs) session.get(Staffs.class, idstaff);
		model.addAttribute("emailstaff", staff.getEmail());
		return "sendmail";
	}

}
