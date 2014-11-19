/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.BaseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author turgay
 */
@Component
public class RSSFeedParserService {

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final boolean COPYRIGHT = false;
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubdate";
    static final String GUID = "guid";

    private URL url;

    public Feed readFeed(String feedUrl) {
        Feed feed = null;
        try {
            url = new URL(feedUrl);
            try {
                boolean isFeedHeader = true;
                // Set header values intial to the empty string
                String description = "";
                String title = "";
                String link = "";
                String language = "";
                boolean copyright = false;
                String author = "";
                String pubdate = "";
                String guid = "";

                // First create a new XMLInputFactory
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                // Setup a new eventReader
                InputStream in = read();
                XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                // read the XML document
                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();
                    if (event.isStartElement()) {
                        String localPart = event.asStartElement().getName()
                                .getLocalPart();
                        switch (localPart) {
                            case ITEM:
                                if (isFeedHeader) {
                                    isFeedHeader = false;
                                    feed = new Feed(link, language, copyright, BaseStatus.Active, Clock.getTime(), Clock.getTime());
                                }
                                event = eventReader.nextEvent();
                                break;
                            case TITLE:
                                title = getCharacterData(event, eventReader);
                                break;
                            case DESCRIPTION:
                                description = getCharacterData(event, eventReader);
                                break;
                            case LINK:
                                link = getCharacterData(event, eventReader);
                                break;
                            case GUID:
                                guid = getCharacterData(event, eventReader);
                                break;
                            case LANGUAGE:
                                language = getCharacterData(event, eventReader);
                                if (StringUtils.isEmpty(language)) {
                                    language = "TR";
                                }
                                break;
                            case AUTHOR:
                                author = getCharacterData(event, eventReader);
                                break;
                            case PUB_DATE:
                                pubdate = getCharacterData(event, eventReader);
                                break;
                        }
                    } else if (event.isEndElement()) {
                        if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                            FeedMessage message = new FeedMessage();
                            message.setAuthor(author);
                            message.setDescription(description);
                            message.setGuid(guid);
                            message.setLink(link);
                            message.setTitle(title);
                            message.setStatus(BaseStatus.Active);
                            if (StringUtils.isEmpty(pubdate)) {
                                message.setPubdate(Clock.getTime());
                            } else {
                                message.setPubdate(DateUtils.toLongFormattedDate(pubdate));
                            }
                            message.setCreateDate(Clock.getTime());
                            feed.getEntries().add(message);
                            event = eventReader.nextEvent();
                        }
                    }
                }
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            return feed;
        } catch (MalformedURLException ex) {
            Logger.getLogger(RSSFeedParserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feed;
    }

    @SuppressWarnings("finally")
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) {
        String result = "";
        try {
            event = eventReader.nextEvent();
            if (event instanceof Characters) {
                result = event.asCharacters().getData();
            }
        } catch (XMLStreamException ex) {
            return result;
        } finally {
            return result;
        }
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
