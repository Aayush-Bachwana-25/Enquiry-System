package enquirysystemwebapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.models.Batch;
import enquirysystemwebapp.models.Course;

@Controller
public class ScheduleController {

//    @Autowired
//    private CourseDAO courseDao;
//
//    @Autowired
//    private BatchDAO batchDao;
//
//    @Autowired
//    private ShiftDAO shiftDao;
//
//    @GetMapping("/schedule")
//    public ModelAndView schedule() {
//        List<Schedule> schedules = new ArrayList<Schedule>();
//        List<Course> courses = courseDao.getAllCourses();
//
//        for (Course course : courses) {
//            List<Batch> batches = batchDao.getBatchesByCourseId(course.getCourseId());
//            for (Batch batch : batches) {
//                List<Shift> shifts = shiftDao.getShiftsByBatchId(batch.getId());
//                for (Shift shift : shifts) {
//                    Schedule schedule = new Schedule(course.getCourseName(), batch.getStartDate(), batch.getEndDate(),
//                            shift.getStartTime(),shift.getEndTime());
//                    schedules.add(schedule);
//                }
//            }
//        }
//
//        // Sort schedules by course name
//        Collections.sort(schedules, new Comparator<Schedule>() {
//            public int compare(Schedule s1, Schedule s2) {
//                return s1.getCourseName().compareTo(s2.getCourseName());
//            }
//        });
//
//        ModelAndView modelAndView = new ModelAndView("schedule");
//        modelAndView.addObject("schedules", schedules);
//        return modelAndView;
//    }
//    
//    @GetMapping("addschedule")
//    public ModelAndView addSchedule() {
//    	return new ModelAndView("addSchedule","newschedule", new Schedule());
//    }
	
	
    
    
}
