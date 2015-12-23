package cn.edu.shnu.fb.interfaces.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.shnu.fb.domain.major.Major;
import cn.edu.shnu.fb.domain.major.MajorRepository;
import cn.edu.shnu.fb.domain.major.MajorType;
import cn.edu.shnu.fb.domain.user.Teacher;
import cn.edu.shnu.fb.infrastructure.persistence.MajorDao;
import cn.edu.shnu.fb.interfaces.dto.GridEntityDTO;

/**
 * Created by bytenoob on 15/11/21.
 */

@RequestMapping("/api")
@RestController
public class MajorFacade {
    @Autowired
    MajorRepository majorRepository;
    @ResponseBody
    @RequestMapping(value="/m/init",method= RequestMethod.POST)
    public void initMajor (@RequestBody Major major){
        majorRepository.initMajor(major);
    }

    @RequestMapping(value="/m/modify",method= RequestMethod.POST)
    public void modifyMajor (@RequestBody Major major) {
        majorRepository.modifyMajor(major);
    }
    @ResponseBody
    @RequestMapping(value="/mt/init",method= RequestMethod.POST)
    public void initMajorType (@RequestBody MajorType majorType){
        majorRepository.updateMajorType(majorType);
    }

    @ResponseBody
    @RequestMapping(value="/m",method= RequestMethod.GET)
    public Iterable<Major> getAllMajors (){
        return majorRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value="/mt",method= RequestMethod.GET)
    public Iterable<MajorType> getAllMajorTypes (){
        return majorRepository.findAllMajorType();
    }


    @ResponseBody
    @RequestMapping(value="/m/{majorId}/delete",method= RequestMethod.GET)
    public void deleteMajor (@PathVariable Integer majorId){
        majorRepository.deleteByMajorId(majorId);
    }

    @ResponseBody
    @RequestMapping(value="/m/available",method= RequestMethod.GET)
    public Iterable<Major> findAvailable(){
        return majorRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value="/m/t/{respId}",method= RequestMethod.GET)
    public Iterable<Major> getAvailableMajors(@PathVariable Integer respId){
        return majorRepository.findByResponsableTeacherId(respId);
    }

    @ResponseBody
    @RequestMapping(value="/mt/t/{respId}",method= RequestMethod.GET)
    public Iterable<MajorType> getAvailableMajorTypes(@PathVariable Integer respId){
        return majorRepository.findMajorTypeByResponsableTeacherId(respId);
    }

    @ResponseBody
    @RequestMapping(value="/m/t/{respId}/update",method= RequestMethod.POST)
    public void changeRespRight (@PathVariable Integer respId , @RequestBody List<MajorType> majorTypes){
        majorRepository.changeRespRight(respId, majorTypes);
    }

}
