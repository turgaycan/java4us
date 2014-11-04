/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils;

import com.java4us.commons.utils.DateUtils;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author turgay
 */
@Embeddable
public class DateRange implements Serializable {

    private static final long serialVersionUID = -6002942260740039657L;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private Date startDate;
    private Date finishDate;

    public DateRange() {
    }

    public DateRange(Date sameDate) {
        this.startDate = sameDate;
        this.finishDate = sameDate;
    }

    public DateRange(Date startDate, Date finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public boolean isNotNullStartDate() {
        return startDate != null;
    }

    public boolean isNotNullFinishDate() {
        return finishDate != null;
    }

    public boolean isNotNullStartAndFinishDate() {
        return isNotNullFinishDate() && isNotNullStartDate();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void adjustStartAndFinishDates() {
        if (startDate != null) {
            startDate = DateUtils.toStartOfDay(startDate);
        }
        if (finishDate != null) {
            finishDate = DateUtils.toEndOfDay(finishDate);
        }
    }

    public boolean isNotNullStartOrFinishDate() {
        return isNotNullStartDate() || isNotNullFinishDate();
    }

    public boolean isDateBetween(Date date) {
        return DateUtils.isDateBetweenBySpecificDatesWithoutTime(date, startDate, finishDate);
    }

    public int getDateDiff() {
        return DateUtils.daysDifference(startDate, finishDate);
    }

    public boolean isDateNotBetween(Date date) {
        return !isDateBetween(date);
    }

    public String getDateRangeAsString() {
        return DateUtils.formatDate(getStartDate(), DATE_FORMAT) + " - " + DateUtils.formatDate(getFinishDate(), DATE_FORMAT);
    }
}
