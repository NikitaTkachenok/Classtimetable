package ua.com.foxminded.classtimetable.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.entities.Building;

@Controller
public class BuildingController {

	@Autowired
	private BuildingDao daoBuilding;

	@RequestMapping(value = "/building")
	public ModelAndView showBuildings(ModelAndView model) {

		List<Building> buildings = daoBuilding.getAll();
		model.addObject("buildings", buildings);
		model.setViewName("index");

		return model;
	}

}
