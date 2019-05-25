package com.nwchat.сontroller;

import com.nwchat.model.Doc;
import com.nwchat.repository.DocsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/doc")
public class DocController {

	@Autowired
	private DocsRepository docsRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();

		List<Doc> listDoc = docsRepository.findAll();

		model.addObject("docList", listDoc);
		model.setViewName("doc/index");
		return model;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value = "id") long id) {
		ModelAndView model = new ModelAndView();

		Doc doc = docsRepository.findById(id).get();

//		if (!doc.isPresent()) {
//			model.setViewName("errors/404");
//			return model;
//		}

		model.addObject("doc", doc);
		model.setViewName("doc/show");
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView create(@Valid Doc doc, BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (result.hasErrors()) {
			model.addObject("doc", doc);
			model.setViewName("doc/form");
			return model;
		}

		doc = docsRepository.save(doc);

		model.setViewName(String.format("redirect:%d", doc.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		model.addObject("doc", new Doc());
		model.setViewName("doc/form");
		return model;
	}



	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("doc", docsRepository.findById(id));
		model.setViewName("doc/form");
		return model;
	}

	@RequestMapping(value = "/{id}/delete")
	public ModelAndView delete(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		docsRepository.deleteById(id);
		model.setViewName("redirect:/doc/");
		return model;
	}
}
