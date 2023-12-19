package com.example.globalpm.entities;

import jakarta.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.javadoc.doclet.Taglet;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class Location {

    String country;
    ZoneId zoneId;
    @JdbcTypeCode(SqlTypes.JSON)
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ssa");
    LocalDate localDate = LocalDate.now(zoneId);
    LocalTime localTime = LocalTime.now(zoneId);

    public Location(String country) {
        this.country = country;
    }

    //public String getCountry(){
//return country;
//}
//
//public void setCountry(String country){
//this.country=country;
//}
//
    public LocalDate getLocalDate() {
        return localDate;
    }

    //
//public void setLocalDate(LocalDate localDate){
//this.localDate=localDate;
//}
//
    @JsonIgnore
    public LocalTime getLocalTime() {
        return localTime;
    }
//
//public String getLocalTimeNewFormat(){
//return timeFormatter.format(getLocalTime());
//}
//
//public void setLocalTime(LocalTime localTime){
//this.localTime=localTime;
//}
//
//public ZoneId getZoneId(){
//return zoneId;
//}
//
//public void set ZoneId(ZoneId zoneId){
//this.zoneId=zoneId;
//}
}
