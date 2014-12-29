package com.java4us.web.controller;


import com.java4us.domain.common.enums.Category;
import com.java4us.service.feedmessage.FeedMessageJ4Service;
import com.java4us.service.seo.SeoMetaDataService;
import com.java4us.service.statistics.StatisticsService;
import com.java4us.web.controller.util.Java4UsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class CategoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    protected static final String JAVAVIEWNAME = "/java";
    protected static final String ANDROIDVIEWNAME = "/android";
    protected static final int DEFAULTPAGENUM = 1;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private FeedMessageJ4Service feedMessageJ4Service;

    @Autowired
    private SeoMetaDataService seoMetaDataService;

    protected ModelAndView prepareJavaModelAndView(String viewName, int pageNum) {
        return prepareModel(viewName, pageNum);
    }

    private ModelAndView prepareModel(String viewName, int pageNum) {
        if (viewName.equals(JAVAVIEWNAME)) {
            return prepareJavaModel(viewName, pageNum);
        } else if (viewName.equals(ANDROIDVIEWNAME)) {
            return prepareAndroidModel(viewName, pageNum);
        }
        LOGGER.error("Error viewName {}", viewName);
        return Java4UsUtils.toModelAndView("/error/301");
    }

    private ModelAndView prepareAndroidModel(String viewName, int pageNum) {
        ModelAndView mav = super.handleRequestInternal(viewName, seoMetaDataService.prepareAndroidPage());
        mav.addObject("pagingMessages", feedMessageJ4Service.pagingFeedMessages(Category.ANDROID, pageNum));
        mav.addObject("androidStatistics", statisticsService.androidStatisticModelOf());
        mav.addObject("datastatics", Category.ANDROID.name());
        return mav;
    }

    private ModelAndView prepareJavaModel(String viewName, int pageNum) {
        ModelAndView mav = super.handleRequestInternal(viewName, seoMetaDataService.prepareJavaPage());
        mav.addObject("pagingMessages", feedMessageJ4Service.pagingFeedMessages(Category.JAVA, pageNum));
        mav.addObject("javaStatistics", statisticsService.javaStatisticalModelOf());
        mav.addObject("datastatics", Category.JAVA.name());
        return mav;
    }

    public static int getDefaultpagenum() {
        return DEFAULTPAGENUM;
    }

}
