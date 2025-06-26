package com.tech.prjm09.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.command.BCommand;
import com.tech.command.BContentCommand;
import com.tech.command.BDeleteCommand;
import com.tech.command.BListCommand;
import com.tech.command.BModifyCommand;
import com.tech.command.BModifyViewCommand;
import com.tech.command.BReplyCommand;
import com.tech.command.BReplyViewCommand;
import com.tech.command.BWriteCommand;
import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	BCommand command;
	
	private final IDao iDao;
	
	@Autowired
	public BController(IDao iDao) {
		this.iDao=iDao;
	}
	
	@RequestMapping("/list")
	private String list(Model model) {
		System.out.println("list()");
//		command=new BListCommand();
//		command.execute(model);
		ArrayList<BDto> list=iDao.list();
		model.addAttribute("list",list);
				
		return "list";

	}
	
	@RequestMapping("/write_view")
	private String write_view(Model model) {
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	private String write(HttpServletRequest request, Model model) {
//		model.addAttribute("request",request);
//		command=new BWriteCommand();
//		command.execute(model);
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		iDao.write(bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	private String content_view(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BContentCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");
		BDto dto=iDao.contentView(bid);
		model.addAttribute("content_view",dto);
		
		return "content_view";
	}
	
	@RequestMapping("/modify_view")
	private String modify_view(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BModifyViewCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");
		BDto dto=iDao.modifyView(bid);
		model.addAttribute("content_view",dto);
		
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	private String modify(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BModifyCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		iDao.modify(bid, bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	private String reply_view(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BReplyViewCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");
		BDto dto=iDao.reply_View(bid);
		model.addAttribute("reply_view",dto);
		
		return "reply_view";
	}

	@RequestMapping("/reply")
	private String reply(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BReplyCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		iDao.replyShape(bgroup, bstep);
		iDao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	private String delete(HttpServletRequest request,Model model) {
//		model.addAttribute("request", request);
//		command=new BDeleteCommand();
//		command.execute(model);
		String bid=request.getParameter("bid");		
		iDao.delete(bid);
		
		return "redirect:list";
	}
	
	
}
