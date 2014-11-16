package com.sbhachu.lollipop.bbcnewsreader.network;

import com.sbhachu.lollipop.bbcnewsreader.application.BBCNewsReaderApplicationConfig;
import com.sbhachu.lollipop.bbcnewsreader.data.model.RSS;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

/**
 * Created by sbhachu on 28/10/2014.
 */
@Rest(rootUrl = BBCNewsReaderApplicationConfig.BASE_URL, converters = {SimpleXmlHttpMessageConverter.class})
public interface BBCNewsRestTemplate {

    @Get("/rss.xml")
    public RSS getTopStories();

    @Get("/world/rss.xml")
    public RSS getWorldNewsStories();

    @Get("/uk/rss.xml")
    public RSS getUKNewsStories();

    @Get("/business/rss.xml")
    public RSS getBusinessNewsStories();

    @Get("/politics/rss.xml")
    public RSS getPoliticsNewsStories();

    @Get("/health/rss.xml")
    public RSS getHealthNewsStories();

    @Get("/education/rss.xml")
    public RSS getEducationNewsStories();

    @Get("/science_and_environment/rss.xml")
    public RSS getScienceAndEnvironmentNewsStories();

    @Get("/technology/rss.xml")
    public RSS getTechnologyNewsStories();

    @Get("/entertainment_and_arts/rss.xml")
    public RSS getEntertainmentAndArtsNewsStories();
}
