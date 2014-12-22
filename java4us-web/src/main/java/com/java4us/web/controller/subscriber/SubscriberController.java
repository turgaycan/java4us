package com.java4us.web.controller.subscriber;

import com.java4us.commons.service.member.SubscriberService;
import com.java4us.commons.utils.Clock;
import com.java4us.domain.Subscriber;
import com.java4us.service.json.JsonWriterService;
import com.java4us.service.seo.SeoMetaDataService;
import com.java4us.web.controller.BaseController;
import com.java4us.web.controller.util.Java4UsUtils;
import com.java4us.web.controller.util.RegexUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SubscriberController extends BaseController {

    @Autowired
    private JsonWriterService jsonWriterService;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SeoMetaDataService seoMetaDataService;

    @RequestMapping(value = "/newsubscriber", method = RequestMethod.POST)
    public void addNewSubscriber(HttpServletRequest request,
                                 HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        String name = request.getParameter("firstname");
        if (StringUtils.isBlank(name)) {
            result.put("success", false);
            jsonWriterService.writeResponse(response, result);
            return;
        }

        String surname = request.getParameter("lastname");
        if (StringUtils.isBlank(surname)) {
            result.put("success", false);
            jsonWriterService.writeResponse(response, result);
            return;
        }

        String email = request.getParameter("email");
        if (StringUtils.isBlank(email)) {
            result.put("success", false);
            jsonWriterService.writeResponse(response, result);
            return;
        }
        email = Java4UsUtils.getNullCheckTrim(email);
        if (checkEmail(email)) {
            saveNewSubscriber(name, surname, email);
            result.put("success", true);
            jsonWriterService.writeResponse(response, result);
        } else {
            result.put("isAlreadyExists", true);
            jsonWriterService.writeResponse(response, result);
        }
    }

    @RequestMapping(value = "/subscriber-{subscriberId:\\s+$}", method = RequestMethod.GET)
    public ModelAndView updateSubscriber(@PathVariable("subscriberId") String subscriberId) {
        if (!NumberUtils.isNumber(subscriberId)) {
            return Java4UsUtils.toModelAndView("/error/301");
        }

        Long id = Long.valueOf(subscriberId);
        Subscriber subscriber = subscriberService.findById(id);
        if (subscriber == null) {
            return Java4UsUtils.toModelAndView("/error/301");
        }
        subscriber.setAllowtomail(false);
        subscriberService.update(subscriber);
        ModelAndView mav = super.handleRequestInternal("unsubscriber", seoMetaDataService.prepareUnSubscriber(id));
        mav.addObject("subscriberId", subscriberId);
        return mav;
    }

    @RequestMapping(value = "/unsubscriber-{subscriberId:\\s+$}", method = RequestMethod.POST)
    public void unSubscriber(@PathVariable("subscriberId") String subscriberId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        byte[] valueDecoded = Base64.decodeBase64(new String(subscriberId).getBytes());
        String subsId = new String(valueDecoded);
        if (!NumberUtils.isNumber(subsId)) {
            result.put("success", false);
            jsonWriterService.writeResponse(response, result);
            return;
        }

        Long id = Long.valueOf(subsId);
        Subscriber subscriber = subscriberService.findById(id);
        if (subscriber == null) {
            result.put("success", false);
            jsonWriterService.writeResponse(response, result);
            return;
        }
        subscriber.setAllowtomail(true);
        subscriberService.update(subscriber);
        result.put("success", true);
        jsonWriterService.writeResponse(response, result);
    }

    private void saveNewSubscriber(String name, String surname, String email) {
        Subscriber subscriber = new Subscriber();
        subscriber.setName(name);
        subscriber.setSurname(surname);
        subscriber.setEmail(email);
        subscriber.setCreateDate(Clock.getTime());
        subscriberService.save(subscriber);
    }

    private boolean checkEmail(String email) {
        return RegexUtil.checkEmail(email)
                && subscriberService.isProperEmail(email);
    }
}
