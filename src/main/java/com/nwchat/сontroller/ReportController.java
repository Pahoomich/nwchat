package com.nwchat.сontroller;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.ReportEntity;
import com.nwchat.repository.OrderRepository;
import com.nwchat.repository.ReportRepository;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        Integer roleId = userService.getAuthenticationUser().getRoleId();

        if (roleId==2) {
            Integer idUser = userService.getAuthenticationUser().getId();
            Iterable<ReportEntity> listReport = reportRepository.findAllByCreatorIdEquals(idUser);
            model.addObject("reportList", listReport);
            model.addObject("userRole", roleId);
            model.setViewName("report/index");
        }
        else if (roleId ==1){
            model.addObject("reportList", reportRepository.findAllByStateEquals(1));
            model.addObject("userRole", roleId);
            model.setViewName("report/index");
        }

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show (@PathVariable("id") Integer id){
        ModelAndView model = new ModelAndView();
        ReportEntity reportEntity = reportRepository.findById(id).get();
        Integer roleId = userService.getAuthenticationUser().getRoleId();
        OrderEntity orderEntity = orderRepository.findById(reportEntity.getOrderId()).get();
        model.addObject("report",reportEntity);
        model.addObject("order",orderEntity);
        model.addObject("userRole",roleId);
        model.setViewName("report/show");
        return model;


    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView create(@Valid ReportEntity reportEntity, BindingResult result) {
        ModelAndView model = new ModelAndView();
        reportEntity.setCreatorId(userService.getAuthenticationUser().getId());
        reportEntity.setOrderId(reportEntity.getOrdersByOrderId().getId());
        reportEntity.setState(0);
        if (result.hasErrors()) {
            model.addObject("report", reportEntity);

            model.setViewName("report/form");
            return model;
        }

        reportEntity = reportRepository.save(reportEntity);

        model.setViewName(String.format("redirect:%d", reportEntity.getId()));
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView();

        ReportEntity reportEntity = new ReportEntity();
        List<OrderEntity> orderEntityList = orderRepository.findAllByManagerIdEquals(userService.getAuthenticationUser().getId());
        model.addObject("orderList",orderEntityList);
        model.addObject("report", reportEntity);
        model.setViewName("report/form");
        return model;
    }
    @RequestMapping(value = "/{id}/send",method = RequestMethod.GET)
    public ModelAndView send(@PathVariable int id ){
        ModelAndView model = new ModelAndView();
        ReportEntity reportEntity = reportRepository.findById(id).get();
        Integer roleId = userService.getAuthenticationUser().getRoleId();
        if (roleId ==2) {
            reportEntity.setState(1);
            reportEntity = reportRepository.save(reportEntity);
            model.addObject("report",reportEntity);
            model.setViewName("redirect:/report/");
        }
        else {
            model.setViewName("redirect:/report/");
        }
        return model;
    }


    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        Optional<ReportEntity> optReport = reportRepository.findById(id);
        ReportEntity reportEntity =optReport.get();
        if (reportEntity.getState() == 0) {
            reportEntity.setCreatorId(userService.getAuthenticationUser().getId());
            List<OrderEntity> orderEntityList =  new ArrayList<>();
            orderEntityList.add(reportEntity.getOrdersByOrderId());
            reportEntity.setOrderId(orderEntityList.get(0).getId());
            if (optReport.isPresent()) {
                model.addObject("report", reportEntity);
                model.addObject("orderList",orderEntityList);
                model.setViewName("report/form");
            } else {
                model.setViewName("redirect:/report/");
            }
        }
        else {
            model.setViewName("redirect:/report/");
        }

        return model;
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        Integer roleId = userService.getAuthenticationUser().getRoleId();
        ReportEntity reportEntity = reportRepository.findById(id).get();
        Integer state = reportEntity.getState();
        if ((roleId ==2) && (state==0)) {
            reportRepository.deleteById(id);
            model.setViewName("redirect:/report/");
        } else {
            model.setViewName("redirect:/report/");
        }
        return model;
    }
}
