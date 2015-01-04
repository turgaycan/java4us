package com.java4us.web.controller.search;

import com.java4us.commons.service.search.SearchService;
import com.java4us.commons.service.security.XSSSecurityService;
import com.java4us.documents.criteria.SearchCriteria;
import com.java4us.documents.domain.FeedMessageSearch;
import com.java4us.domain.common.enums.Category;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by turgaycan on 12/30/14.
 */
@Controller
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @Autowired
    private XSSSecurityService xssSecurityService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchFeedMessages(HttpServletRequest request, HttpServletResponse response){
        SearchCriteria searchCriteria = new SearchCriteria();
        if(StringUtils.isNotBlank(request.getParameter("q"))){
            String cleanedQuery = xssSecurityService.cleanTextForXSS(request.getParameter("q"));
            searchCriteria.setQuery(cleanedQuery);
        }
        String category = request.getParameter("category");
        if(StringUtils.isNotBlank(category)){
            if(Category.JAVA.name() == category && Category.ANDROID.name() == category){
                searchCriteria.setCategory(Category.valueOf(category));
            }
        }

        Page<FeedMessageSearch> feedMessageSearchResult = searchService.search(searchCriteria);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchResult", feedMessageSearchResult);
        return modelAndView;
    }
}
